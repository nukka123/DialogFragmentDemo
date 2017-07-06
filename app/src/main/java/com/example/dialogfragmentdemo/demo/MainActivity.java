package com.example.dialogfragmentdemo.demo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dialogfragmentdemo.R;
import com.example.dialogfragmentdemo.databinding.MainActivityBinding;
import com.example.dialogfragmentdemo.dialog.AbstractDialogFragment;
import com.example.dialogfragmentdemo.dialog.AlertDialogFragment;

public class MainActivity extends AppCompatActivity implements AbstractDialogFragment.Callback {
    static final String TAG = MainActivity.class.getSimpleName();
    MainActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

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
        Log.d(TAG, "onOptionsItemSelected: " + item);
        switch (item.getItemId()) {
            case 10:
                showDialogFromActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showDialogFromActivity() {
        Log.d(TAG, "showDialogFromActivity");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Activity.")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(this, 100)
                .show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void onDialogResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onDialogResult: "
                + "requestCode=" + requestCode + ", "
                + "resultCode=" + resultCode + ", "
                + "data=" + data);
    }

    static void navigateToMain(FragmentManager manager) {
        Log.d(TAG, "navigateToMain");
        if (manager != null) {
            manager.beginTransaction()
                    .replace(R.id.content_view, new MainFragment())
                    .commit();
        }
    }
}
