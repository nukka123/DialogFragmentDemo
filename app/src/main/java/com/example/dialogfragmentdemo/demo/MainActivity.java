package com.example.dialogfragmentdemo.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dialogfragmentdemo.R;
import com.example.dialogfragmentdemo.demo.utils.IntentUtils;
import com.example.dialogfragmentdemo.demo.utils.ToastUtils;
import com.example.dialogfragmentdemo.dialog.AbstractDialogFragment;
import com.example.dialogfragmentdemo.dialog.AlertDialogFragment;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements AbstractDialogFragment.Callback {
    final String DIALOG_TAG = "dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            attachMainFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "show dialog on activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 10:
                showDialogOnActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showDialogOnActivity() {
        Timber.d("showDialogOnActivity");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello, Dialog World! (Activity).")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(100)
                .showOn(this, DIALOG_TAG);
    }


    @Override
    public void onDialogResult(int requestCode, int resultCode, @Nullable Intent data) {
        ToastUtils.show(this, String.format("MainActivity > onDialogResult: request=%s, result=%s", requestCode, resultCode));
        Timber.d("onDialogResult: request=%s, result=%s", requestCode, resultCode);
        Timber.d("onDialogResult: data=%s, extra=%s", data, IntentUtils.getExtras(data));
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        ToastUtils.show(this, String.format("MainActivity > onDialogCancelled: request=%s", requestCode));
        // onDialogResult(requestCode, DialogInterface.BUTTON_NEUTRAL, null);
    }


    void attachMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_view, new MainFragment())
                .commit();
    }
}
