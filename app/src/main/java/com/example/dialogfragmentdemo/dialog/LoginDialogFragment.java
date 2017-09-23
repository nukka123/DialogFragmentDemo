package com.example.dialogfragmentdemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.dialogfragmentdemo.R;

/**
 * カスタムレイアウトのダイアログ例
 * https://developer.android.com/guide/topics/ui/dialogs.html#CustomLayout
 */
public class LoginDialogFragment extends AbstractDialogFragment {

    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_PASSWORD = "password";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View customView = inflater.inflate(R.layout.dialog_login, null);

        final EditText editUserName = customView.findViewById(R.id.username);
        final EditText editPassword = customView.findViewById(R.id.password);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(customView);
        builder.setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent data = new Intent();
                data.putExtra(EXTRA_USERNAME, editUserName.getText().toString());
                data.putExtra(EXTRA_PASSWORD, editPassword.getText().toString());
                notifyDialogResult(id, data);
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // notifyDialogResult(id, new Intent());
                LoginDialogFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    public static class Builder extends AbstractDialogFragment.Builder {
        @NonNull
        @Override
        protected AbstractDialogFragment build() {
            return new LoginDialogFragment();
        }
    }
}
