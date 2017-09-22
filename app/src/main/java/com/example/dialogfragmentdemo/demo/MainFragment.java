package com.example.dialogfragmentdemo.demo;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogfragmentdemo.R;
import com.example.dialogfragmentdemo.demo.utils.IntentUtils;
import com.example.dialogfragmentdemo.demo.utils.ToastUtils;
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
    void showDialogOnTarget() {
        Timber.d("showDialogOnTarget");
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
    void showRedIdDialogOnTarget() {
        Timber.d("showRedIdDialogOnTarget");
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
    void showDialogOnParent() {
        Timber.d("showDialogOnParent");
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
    void showDatePickerDialog() {
        Timber.d("showDatePickerDialog");
        new DatePickerFragment.Builder()
                .build(204)
                .showOn(this, DIALOG_TAG);
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent data) {
        ToastUtils.show(getContext(), String.format("MainFragment > onDialogResult: result=%s", resultCode));
        Timber.d("onDialogResult: request=%s, result=%s", requestCode, resultCode);
        Timber.d("onDialogResult: data=%s, extra=%s", data, IntentUtils.getExtras(data));
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        onDialogResult(requestCode, DialogInterface.BUTTON_NEUTRAL, null);
    }
}
