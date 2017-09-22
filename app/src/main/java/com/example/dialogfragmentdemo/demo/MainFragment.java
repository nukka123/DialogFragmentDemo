package com.example.dialogfragmentdemo.demo;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogfragmentdemo.R;
import com.example.dialogfragmentdemo.dialog.AbstractDialogFragment;
import com.example.dialogfragmentdemo.dialog.AlertDialogFragment;
import com.example.dialogfragmentdemo.dialog.DatePickerFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class MainFragment extends Fragment implements AbstractDialogFragment.Callback {
    final String DIALOG_TAG = "dialog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.d("onCreateView");
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.button1)
    void showDialogFromTarget() {
        Timber.d("showDialogFromTarget");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Target Fragment")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(201)
                .showOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button2)
    void showDialogFromTargetWithResId() {
        Timber.d("showDialogFromTargetWithResId");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setPositiveButton(R.string.alert_ok)
                .setNeutralButton(R.string.alert_cancel)
                .setNegativeButton(R.string.alert_ng)
                .setCancelable(false)
                .build(202)
                .showOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button3)
    void showDialogFromParent() {
        Timber.d("showDialogFromParent");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World! From Parent Fragment")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(203)
                .showChildOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button4)
    void showDatePickerFragment() {
        Timber.d("showDatePickerFragment");
        new DatePickerFragment.Builder()
                .build(204)
                .showOn(this, DIALOG_TAG);
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent data) {
        Timber.d("onDialogResult: requestCode=%s", requestCode);
        Timber.d("onDialogResult: resultCode=%s", resultCode);
        Timber.d("onDialogResult: data=%s", data);
        Timber.d("onDialogResult: extra=%s", (data != null ? data.getExtras() : "null"));
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        onDialogResult(requestCode, DialogInterface.BUTTON_NEUTRAL, null);
    }
}
