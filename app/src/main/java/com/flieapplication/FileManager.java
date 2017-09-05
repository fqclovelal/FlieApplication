package com.flieapplication;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class FileManager {
    private volatile File path;
    private volatile List<File> mAllFile;
    private static FileManager mFileManager;

    private FileManager() {
        path = Environment.getExternalStorageDirectory();
        if (mAllFile == null) {
            mAllFile = new ArrayList<>();
        }
    }
    public static FileManager getInstance(){
        if (mFileManager==null) {
            synchronized (FileManager.class){
                if (mFileManager==null) {
                    mFileManager=new FileManager();
                }
            }
        }
        return mFileManager;
    }

    private void getFile(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            long length = files.length;
            for (int i = 0; i < length; i++) {
                File tempFile = files[i];
                if (tempFile.listFiles() == null) {
                    mAllFile.add(tempFile);
                } else
                    getFile(tempFile);
            }

        }
    }

    private List<File> getAllFile() {
        mAllFile.clear();
        getFile(path);
        return mAllFile;
    }

    public List<File> getContractFile() {
        List<File> contractFile = new ArrayList<>();
        List<File> allFile = getAllFile();
        int size = allFile.size();
        for (int i = 0; i < size; i++) {
            File file = allFile.get(i);
            if (file.getName().endsWith(".doc") || file.getName().endsWith(".docx") || file.getName().endsWith(".pdf")||file.getName().endsWith(".txt")) {
                contractFile.add(file);
            }
        }
        return contractFile;
    }
}
