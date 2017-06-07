package com.example.dialogfragmentdemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * アラート用途のダイアログ・フラグメント.
 * {@link Builder}を使用して、アラート・ダイアログを手短に構築する事ができます.
 */
public class AlertDialogFragment extends AbstractDialogFragment {

    static final String ARG_POSITIVE_LABEL = "positiveLabel";
    static final String ARG_NEGATIVE_LABEL = "negativeLabel";
    static final String ARG_NEUTRAL_LABEL = "neutralLabel";
    static final String ARG_TITLE = "title";
    static final String ARG_MESSAGE = "message";
    static final String ARG_POSITIVE_LABEL_ID = "positiveLabelId";
    static final String ARG_NEGATIVE_LABEL_ID = "negativeLabelId";
    static final String ARG_NEUTRAL_LABEL_ID = "neutralLabelId";
    static final String ARG_TITLE_ID = "titleId";
    static final String ARG_MESSAGE_ID = "messageId";
    static final String ARG_ICON_ID = "iconId";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        Bundle args = getArguments();
        String positiveLabel = args.getString(ARG_POSITIVE_LABEL);
        String negativeLabel = args.getString(ARG_NEGATIVE_LABEL);
        String neutralLabel = args.getString(ARG_NEUTRAL_LABEL);
        String title = args.getString(ARG_TITLE);
        String message = args.getString(ARG_MESSAGE);
        int positiveLabelId = args.getInt(ARG_POSITIVE_LABEL_ID);
        int negativeLabelId = args.getInt(ARG_NEGATIVE_LABEL_ID);
        int neutralLabelId = args.getInt(ARG_NEUTRAL_LABEL_ID);
        int titleId = args.getInt(ARG_TITLE_ID);
        int messageId = args.getInt(ARG_MESSAGE_ID);
        int iconId = args.getInt(ARG_ICON_ID);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyDialogResult(which, null);
            }
        };

        if (positiveLabel != null) {
            builder.setPositiveButton(positiveLabel, listener);
        }
        if (negativeLabel != null) {
            builder.setNegativeButton(negativeLabel, listener);
        }
        if (neutralLabel != null) {
            builder.setNeutralButton(neutralLabel, listener);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        if (positiveLabelId > 0) {
            builder.setPositiveButton(positiveLabelId, listener);
        }
        if (negativeLabelId > 0) {
            builder.setNegativeButton(negativeLabelId, listener);
        }
        if (neutralLabelId > 0) {
            builder.setNeutralButton(neutralLabelId, listener);
        }
        if (titleId > 0) {
            builder.setTitle(titleId);
        }
        if (messageId > 0) {
            builder.setMessage(messageId);
        }
        if (iconId > 0) {
            builder.setIcon(iconId);
        }

        return builder.create();
    }

    /**
     * {@link AlertDialogFragment} のビルダ.
     */
    public static class Builder extends AbstractDialogFragment.Builder {
        String mMessage;
        String mTitle;
        String mPositiveLabel;
        String mNegativeLabel;
        String mNeutralLabel;

        int mMessageId;
        int mTitleId;
        int mPositiveLabelId;
        int mNegativeLabelId;
        int mNeutralLabelId;
        int mIcon;

        public Builder setIcon(@DrawableRes int iconId) {
            mIcon = iconId;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setNegativeButton(String label) {
            mNegativeLabel = label;
            return this;
        }

        public Builder setNeutralButton(String label) {
            mNeutralLabel = label;
            return this;
        }

        public Builder setPositiveButton(String label) {
            mPositiveLabel = label;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            mMessageId = resId;
            return this;
        }

        public Builder setNegativeButton(@StringRes int resId) {
            mNegativeLabelId = resId;
            return this;
        }

        public Builder setNeutralButton(@StringRes int resId) {
            mNeutralLabelId = resId;
            return this;
        }

        public Builder setPositiveButton(@StringRes int resId) {
            mPositiveLabelId = resId;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            mTitleId = title;
            return this;
        }

        @NonNull
        @Override
        public Builder setCancelable(boolean cancelable) {
            super.setCancelable(cancelable);
            return this;
        }

        @NonNull
        @Override
        protected DialogFragment build() {
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, mMessage);
            args.putString(ARG_TITLE, mTitle);
            args.putString(ARG_NEGATIVE_LABEL, mNegativeLabel);
            args.putString(ARG_NEUTRAL_LABEL, mNeutralLabel);
            args.putString(ARG_POSITIVE_LABEL, mPositiveLabel);
            args.putInt(ARG_MESSAGE_ID, mMessageId);
            args.putInt(ARG_TITLE_ID, mTitleId);
            args.putInt(ARG_NEGATIVE_LABEL_ID, mNegativeLabelId);
            args.putInt(ARG_NEUTRAL_LABEL_ID, mNeutralLabelId);
            args.putInt(ARG_POSITIVE_LABEL_ID, mPositiveLabelId);
            args.putInt(ARG_ICON_ID, mIcon);

            DialogFragment dialog = new AlertDialogFragment();
            dialog.setArguments(args);
            return dialog;
        }
    }
}
