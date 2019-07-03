package com.lanfairy.elly.androidsummary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.lanfairy.elly.androidsummary.SummaryApplication;

public class SecurityCheckManager {
    private static SecurityCheckManager mInstance;

    private SecurityCheckManager() {
    }

    public static SecurityCheckManager getInstance() {
        if (mInstance == null) {
            synchronized (SecurityCheckManager.class) {
                if (mInstance == null) {
                    mInstance = new SecurityCheckManager();
                }

            }
        }

        return mInstance;
    }

    public boolean checkPermission(Activity context, String neededPermission) {
        boolean result = false;
        if (ContextCompat.checkSelfPermission(context, neededPermission) == PackageManager.PERMISSION_GRANTED) {
            result = true;
        }else {
            ActivityCompat.requestPermissions(context, new String[]{neededPermission}, 1);
        }
        return result;
    }
}
