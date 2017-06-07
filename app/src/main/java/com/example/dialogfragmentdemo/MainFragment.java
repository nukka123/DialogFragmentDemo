package com.example.dialogfragmentdemo;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogfragmentdemo.databinding.MainFragmentBinding;
import com.example.dialogfragmentdemo.dialog.AbstractDialogFragment;
import com.example.dialogfragmentdemo.dialog.AlertDialogFragment;
import com.example.dialogfragmentdemo.dialog.DatePickerFragment;


public class MainFragment extends Fragment implements AbstractDialogFragment.Callback {
    static final String TAG = MainFragment.class.getSimpleName();
    MainFragmentBinding mBiding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBiding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        mBiding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFromTarget();
            }
        });
        mBiding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFromTargetWithResId();
            }
        });
        mBiding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFromParent();
            }
        });
        mBiding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerFragment();
            }
        });

        return mBiding.getRoot();
    }

    void showDialogFromTarget() {
        Log.d(TAG, "showDialogFromTarget");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Target Fragment")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(this, 201)
                .show(getFragmentManager(), "dialog");
    }

    void showDialogFromTargetWithResId() {
        Log.d(TAG, "showDialogFromTargetWithResId");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setPositiveButton(R.string.alert_ok)
                .setNeutralButton(R.string.alert_cancel)
                .setNegativeButton(R.string.alert_ng)
                .setCancelable(false)
                .build(this, 202)
                .show(getFragmentManager(), "dialog");
    }

    void showDialogFromParent() {
        Log.d(TAG, "showDialogFromParent");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Parent Fragment")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(203)
                .show(getChildFragmentManager(), "dialog");
    }

    void showDatePickerFragment() {
        Log.d(TAG, "showDialog203");
        new DatePickerFragment.Builder()
                .build(this, 204)
                .show(getFragmentManager(), "dialog");
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onDialogResult: "
                + "requestCode=" + requestCode + ", "
                + "resultCode=" + resultCode + ", "
                + "data=" + data + ", "
                + "extras=" + (data != null ? data.getExtras() : "null"));
    }
}
