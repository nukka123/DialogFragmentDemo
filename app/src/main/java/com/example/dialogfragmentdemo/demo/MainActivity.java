package com.example.dialogfragmentdemo.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dialogfragmentdemo.R;
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
            navigateToMain(getSupportFragmentManager());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "show dialog from activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 10:
                showDialogFromActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showDialogFromActivity() {
        Timber.d("showDialogFromActivity");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Activity.")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(100)
                .showOn(this, DIALOG_TAG);
    }


    @Override
    public void onDialogResult(int requestCode, int resultCode, @Nullable Intent data) {
        Timber.d("onDialogResult: requestCode=%s", requestCode);
        Timber.d("onDialogResult: resultCode=%s", resultCode);
        Timber.d("onDialogResult: data=%s", data);
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        onDialogResult(requestCode, DialogInterface.BUTTON_NEUTRAL, null);
    }


    static void navigateToMain(FragmentManager manager) {
        Timber.d("navigateToMain");
        if (manager != null) {
            manager.beginTransaction()
                    .replace(R.id.content_view, new MainFragment())
                    .commit();
        }
    }
}
