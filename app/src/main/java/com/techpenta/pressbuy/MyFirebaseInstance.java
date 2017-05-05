package com.techpenta.pressbuy;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Diku on 05-05-2017.
 */

public class MyFirebaseInstance extends FirebaseInstanceIdService {

    private static final String REG_TOK="REG_TOK";
    public void onTokenRefresh(){


        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOK,recent_token);
    }
}
