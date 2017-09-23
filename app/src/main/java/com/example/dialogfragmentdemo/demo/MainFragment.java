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
import com.example.dialogfragmentdemo.dialog.LoginDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import onactivityresult.ActivityResult;
import onactivityresult.ExtraInt;
import onactivityresult.ExtraString;
import onactivityresult.OnActivityResult;
import timber.log.Timber;


public class MainFragment extends Fragment implements AbstractDialogFragment.Callback {
    static final String DIALOG_TAG = "dialog";
    static final int REQ_ID_ALERT_A = 201;
    static final int REQ_ID_ALERT_B = 202;
    static final int REQ_ID_ALERT_C = 203;
    static final int REQ_ID_DATE_PICKER = 204;
    static final int REQ_ID_LOGIN = 205;

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
                .setMessage("Hello, Dialog World! (Target Fragment).")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(REQ_ID_ALERT_A)
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
                .build(REQ_ID_ALERT_B)
                .showOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button3)
    void showDialogOnParent() {
        Timber.d("showDialogOnParent");
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello, Dialog World! (Child Fragment).")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(REQ_ID_ALERT_C)
                .showChildOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button4)
    void showDatePickerDialog() {
        Timber.d("showDatePickerDialog");
        new DatePickerFragment.Builder()
                .build(REQ_ID_DATE_PICKER)
                .showOn(this, DIALOG_TAG);
    }

    @OnClick(R.id.button_signin)
    void showSignInDialog() {
        Timber.d("showSignInDialog");
        new LoginDialogFragment.Builder()
                .setCancelable(false)
                .build(REQ_ID_LOGIN)
                .showOn(this, DIALOG_TAG);
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent data) {
        ToastUtils.show(getContext(), String.format("MainFragment > onDialogResult: request=%s, result=%s", requestCode, resultCode));
        Timber.d("onDialogResult: request=%s, result=%s", requestCode, resultCode);
        Timber.d("onDialogResult: data=%s, extra=%s", data, IntentUtils.getExtras(data));

        // onActivityResult()の実装をサポートするライブラリを使用して、
        // 個別ダイアログの結果を実装します.
        // このデモでは、以下URLのライブラリを使用しています.
        // https://github.com/vanniktech/OnActivityResult
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        ToastUtils.show(getContext(), String.format("MainFragment > onDialogCancelled: request=%s", requestCode));
        // onDialogResult(requestCode, DialogInterface.BUTTON_NEUTRAL, null);
    }


    @OnActivityResult(requestCode = REQ_ID_ALERT_A, resultCodes = DialogInterface.BUTTON_POSITIVE)
    void onAlertDialogResultA() {
        Timber.d("onAlertDialogResultA:");
    }

    @OnActivityResult(requestCode = REQ_ID_ALERT_B, resultCodes = DialogInterface.BUTTON_POSITIVE)
    void onAlertDialogResultB() {
        Timber.d("onAlertDialogResultB:");
    }

    @OnActivityResult(requestCode = REQ_ID_ALERT_C, resultCodes = DialogInterface.BUTTON_POSITIVE)
    void onAlertDialogResultC() {
        Timber.d("onAlertDialogResultC:");
    }

    @OnActivityResult(requestCode = REQ_ID_DATE_PICKER, resultCodes = DialogInterface.BUTTON_POSITIVE)
    void onDatePickerDialogResult(
            @ExtraInt(name = DatePickerFragment.EXTRA_YEAR) int year,
            @ExtraInt(name = DatePickerFragment.EXTRA_MONTH) int month,
            @ExtraInt(name = DatePickerFragment.EXTRA_DAY) int day) {
        Timber.d("onDatePickerDialogResult: year=%s, month=%s, day=%s", year, month, day);
    }

    @OnActivityResult(requestCode = REQ_ID_LOGIN, resultCodes = DialogInterface.BUTTON_POSITIVE)
    void onLoginDialogResult(
            @ExtraString(name = LoginDialogFragment.EXTRA_USERNAME) String username,
            @ExtraString(name = LoginDialogFragment.EXTRA_PASSWORD) String password) {
        Timber.d("onLoginDialogResult: username=%s, password=%s", username, password);
    }

}
