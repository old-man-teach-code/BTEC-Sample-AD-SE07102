package com.example.b3_se07102;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "LoginPrefs";

    private static final String KEY_IS_LOGIN = "isLogin";

    private SharedPreferences prefs;

    private SharedPreferences.Editor editor;

    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(KEY_IS_LOGIN, Context.MODE_PRIVATE);
        editor = this.prefs.edit();
    }

    public void checkLogin(){
        if (!this.isLogin()){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        } // check if this context is LoginActivity
        else if (context instanceof LoginActivity) { // nếu đây là Login thì đẩy về main
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    public boolean isLogin() {
        return prefs.getBoolean(KEY_IS_LOGIN, false);
    }

    public void createLoginSession() {
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.commit();
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
