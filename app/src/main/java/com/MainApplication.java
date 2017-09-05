package com;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.utils.CrashHandler;
import com.utils.GreenDaoHelper;
import com.utils.LogToFile;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class MainApplication extends Application {
    private static Toast toast;
    private static Context _context;
    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoHelper.initGreenDao(getApplicationContext());
        _context = getApplicationContext();
    }
    public static void showToast(int message) {
        if (toast == null) {
            toast = Toast.makeText(_context,
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showToast(String message){
        if (toast == null) {
            toast = Toast.makeText(_context,
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
