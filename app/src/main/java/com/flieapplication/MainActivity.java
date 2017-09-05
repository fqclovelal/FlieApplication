package com.flieapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.MainApplication;
import com.fileappication.greendao.FileBeanDao;
import com.utils.GreenDaoHelper;
import com.utils.StringUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements FileShowAdapter.ItemClickListener, FilePopupWindow.RefreshData {
    private static final String TAG = MainActivity.class.getSimpleName();
    private FileManager mFileManager;
    private List<File> mContractFiles;
    private PopupWindow popupWindow;
    private FileBean fileBean;
    private FileBeanDao fileBeanDao;
    private FilePopupWindow mFilePopupWindow;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                popupWindowShow();
                fileBeanDao.deleteAll();
                int size = mContractFiles.size();
                for (int i = 0; i < size; i++) {
                    File file = mContractFiles.get(i);
                    fileBean = new FileBean(null, file.getName(), file.getAbsolutePath());
                    fileBeanDao.insert(fileBean);
                }
            }
        }
    };

    private void popupWindowShow() {
        popupWindow.dismiss();
        mFilePopupWindow.updateData(mContractFiles);
        mFilePopupWindow.showAtLocation(findViewById(R.id.activity_main), Gravity.CENTER | Gravity.BOTTOM, 0, 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileBeanDao = GreenDaoHelper.getDaoSession().getFileBeanDao();
        mContractFiles = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        mFilePopupWindow = new FilePopupWindow(MainActivity.this, width, height, mContractFiles);
        mFilePopupWindow.setRefreshListener(this);
        mFilePopupWindow.setItemClickListener(MainActivity.this);
    }

    private void showFiles() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.check_file, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
        popupWindow.setTouchable(false);
        popupWindow.showAtLocation(findViewById(R.id.activity_main), Gravity.CENTER, 0, 0);
        List<FileBean> list = fileBeanDao.queryBuilder().build().list();
        int size = list.size();
        mContractFiles.clear();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mContractFiles.add(new File(list.get(i).getFileUrl()));
            }
            popupWindowShow();
        } else {
            new FileThread().start();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            showFiles();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void click(View view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            showFiles();
        }
    }

    @Override
    public void chooseFile(File file) {
        if (file.exists()) {
            openfile(file);
        }else {
            Toast.makeText(this,"文件不存在，正在刷新列表，请勿操作",Toast.LENGTH_SHORT).show();
            new FileThread().start();
        }

    }

    @Override
    public void refreshData() {
        new FileThread().start();
    }

    public class FileThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                mFileManager = FileManager.getInstance();
                mContractFiles.clear();
                mContractFiles = mFileManager.getContractFile();
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void openfile(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.tuofu.tuofu_android" + ".fileProvider", file);
            String type = getMIMEType(file);
            intent.setDataAndType(contentUri, type);
        } else {
            String type = getMIMEType(file);
            intent.setDataAndType(Uri.fromFile(file), type);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            startActivity(intent);   // 可能无应用可打开, 避免程序崩溃
        } catch (Exception e) {
            e.printStackTrace();
            MainApplication.showToast("无应用可打开此文件");
            return;
        }
    }

    private String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase(Locale.CHINA);
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < StringUtils.MIME_MapTable.length; i++) {
            if (end.equals(StringUtils.MIME_MapTable[i][0]))
                type = StringUtils.MIME_MapTable[i][1];
        }
        return type;
    }


}
