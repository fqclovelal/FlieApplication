package com.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fileappication.greendao.DaoMaster;
import com.fileappication.greendao.DaoSession;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class GreenDaoHelper {
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static GreenDaoHelper instance = new GreenDaoHelper();
    public static GreenDaoHelper getInstance(){
        return instance;
    }
    public static void initGreenDao(Context context){
        mHelper=new DaoMaster.DevOpenHelper(context,"filedb",null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return mDaoSession;
    }
    public static SQLiteDatabase getDb(){
        return db;
    }

}
