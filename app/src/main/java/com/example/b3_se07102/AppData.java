package com.example.b3_se07102;

import android.content.Context;
import android.widget.Toast;

public class AppData {
    public static boolean loginState = false;
    public static String username = "";
    public static String fullname = "";

    public static void alertToast(Context context, String message, int toastLength){
        Toast.makeText(context, message, toastLength).show();
    }
}
