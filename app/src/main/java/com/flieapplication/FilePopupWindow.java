package com.flieapplication;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.fileappication.greendao.FileBeanDao;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class FilePopupWindow extends PopupWindow implements View.OnClickListener {
    private final FileShowAdapter mFileShowAdapter;
    private RecyclerView mRecyclerView;
    private ImageView mClosed;
    private ImageView mRefresh;
    private Button mSearchSure;
    private EditText mEditText;

    public void updateData(List<File> mContractFiles) {
        mFileShowAdapter.updateData(mContractFiles);
    }

    interface RefreshData{
        void refreshData();
        void searchData(String searchCondition);
    }
    RefreshData refreshData;


    public FilePopupWindow(Context context,int width,int height,List<File> data) {
        View view = LayoutInflater.from(context).inflate(R.layout.file_layout, null);
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width/10*9);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(height/2);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable(0xFFFFFF));
        mSearchSure=(Button) view.findViewById(R.id.search_sure);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.file_list);
        mClosed=(ImageView) view.findViewById(R.id.close_window);
        mRefresh=(ImageView) view.findViewById(R.id.refresh_file_list);
        mEditText=(EditText) view.findViewById(R.id.search);
        LinearLayoutManager layout = new LinearLayoutManager(context);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layout);
        mFileShowAdapter = new FileShowAdapter(context,data);
        mRecyclerView.setAdapter(mFileShowAdapter);
        mClosed.setOnClickListener(this);
        mRefresh.setOnClickListener(this);
        mSearchSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.refresh_file_list:
                refreshData.refreshData();
                break;
            case R.id.close_window:
                dismiss();
                break;
            case R.id.search_sure:
                String trim = mEditText.getText().toString().trim();
                refreshData.searchData(trim);
                break;
        }
    }

    public void setItemClickListener(FileShowAdapter.ItemClickListener listener) {
        mFileShowAdapter.setItemClickListener(listener);
    }
    public void setRefreshListener(RefreshData refreshListener){
        refreshData=refreshListener;
    }

}
