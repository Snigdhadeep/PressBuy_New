package com.techpenta.pressbuy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Diku on 01-03-2017.
 */

public class CheckNetwork {

    //to check this connection you must give this permission to your AndroidMainfest file
    //    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    //<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

    private static final String TAG = "Snigdho";



    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," internet connection");
                return true;
            }

        }
    }
}
