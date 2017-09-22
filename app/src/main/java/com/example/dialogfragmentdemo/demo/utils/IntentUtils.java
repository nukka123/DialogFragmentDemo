package com.example.dialogfragmentdemo.demo.utils;

import android.content.Intent;
import android.os.Bundle;


public class IntentUtils {
    public static Bundle getExtras(Intent intent) {
        if (intent != null) {
            return intent.getExtras();
        } else {
            return null;
        }
    }
}
