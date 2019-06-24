package com.simplecity.amp_library.utils;

import android.support.annotation.Nullable;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.simplecity.amp_library.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {

    private LogUtils() {
        //no instance
    }

    public static void logException(String tag, String message, @Nullable Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message + "\nThrowable: " + (throwable != null ? throwable.getMessage() : null));
            if (throwable != null) {
                throwable.printStackTrace();
            }
        } else {
            Crashlytics.log(Log.ERROR, tag, message + "\nThrowable: " + (throwable != null ? throwable.getMessage() : null));
            Crashlytics.logException(throwable);
        }
    }

    public static void logStack(String tag) {
        StringWriter trace = new StringWriter();
        new Exception("Stack trace").printStackTrace(new PrintWriter(trace));
        Log.i(tag, trace.toString());
    }
}