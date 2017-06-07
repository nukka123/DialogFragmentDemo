package com.example.dialogfragmentdemo.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * コールバック通知を必要とするダイアログ・フラグメントのための抽象クラス.
 * <p>
 * このクラスを実装したダイアログから以下のコンポーネントへ、ダイアログの結果をコールバックする事ができます.
 * - アクティビティ, ターゲット・フラグメント, 親フラグメント
 * <p>
 * コールバックするコンポーネントを指定するため、ビルダにて3種類のビルド方法を提供します。
 */
public abstract class AbstractDialogFragment extends DialogFragment {
    /**
     * ダイアログの結果を通知するためのコールバック・インタフェース.
     */
    public interface Callback {
        /**
         * このメソッドは、ダイアログのボタンが押された時、あるいはダイアログがキャンセルされた時に呼び出されます。
         *
         * @param requestCode ダイアログのリクエストコード.
         * @param resultCode  ダイアログの結果コード. {@link DialogInterface} が定義する値に従います.
         * @param data        ダイアログの結果データ. このパラメータは補助的に使用されます.
         *                    結果データの具体的な仕様は、個々の具象クラスが規定してください.
         */
        void onDialogResult(int requestCode, int resultCode, Intent data);
    }

    private static final String ARG_PREFIX = AbstractDialogFragment.class.getName() + ".";
    private static final String ARG_REQUEST_CODE = ARG_PREFIX + "requestCode";
    private static final String ARG_CALLBACK_ACTIVITY = ARG_PREFIX + "callbackActivity";
    private static final String ARG_CALLBACK_TARGET = ARG_PREFIX + "callbackTarget";
    private static final String ARG_CALLBACK_PARENT = ARG_PREFIX + "callbackParent";

    private int mRequestCode;
    private boolean mShouldCallbackToParent;
    private boolean mShouldCallbackToTarget;
    private boolean mShouldCallbackToActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mRequestCode = args.getInt(ARG_REQUEST_CODE);
        mShouldCallbackToParent = args.getBoolean(ARG_CALLBACK_PARENT);
        mShouldCallbackToTarget = args.getBoolean(ARG_CALLBACK_TARGET);
        mShouldCallbackToActivity = args.getBoolean(ARG_CALLBACK_ACTIVITY);
    }

    /**
     * ダイアログの結果を通知する.
     * 具象クラスは、本メソッドを呼び出して、結果をアクティビティ又はフラグメントへ通知してください.
     *
     * @param resultCode 結果コード.
     * @param data       結果データ.
     */
    protected final void notifyDialogResult(int resultCode, Intent data) {
        Fragment parent = getParentFragment();
        if (mShouldCallbackToParent && parent instanceof Callback) {
            Callback callback = (Callback) parent;
            callback.onDialogResult(mRequestCode, resultCode, data);
        }

        Fragment target = getTargetFragment();
        if (mShouldCallbackToTarget && target instanceof Callback) {
            Callback callback = (Callback) target;
            callback.onDialogResult(mRequestCode, resultCode, data);
        }

        Activity activity = getActivity();
        if (mShouldCallbackToActivity && activity instanceof Callback) {
            Callback callback = (Callback) activity;
            callback.onDialogResult(mRequestCode, resultCode, data);
        }
    }

    /**
     * ダイアログがキャンセルした場合に呼び出される.
     * {@link AbstractDialogFragment}では、キャンセルイベントを捕捉して、
     * {@link Callback#onDialogResult(int, int, Intent)}をコールバックします.
     * 結果コードは {@link DialogInterface#BUTTON_NEUTRAL} を渡します.
     * <p>
     * 具象クラスにおいて、この動作が不要である場合は、このメソッドをオーバーライドする必要があります.
     *
     * @param dialog -
     */
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        notifyDialogResult(DialogInterface.BUTTON_NEUTRAL, null);
    }

    /**
     * ダイアログのビルダ.
     */
    public abstract static class Builder {

        private boolean mCancelable = true;

        /**
         * ダイアログのキャンセルが可能か否かを設定します.
         *
         * @param cancelable キャンセル可能か否か.
         * @return 自身のビルダ.
         */
        @NonNull
        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        /**
         * ダイアログ・フラグメントを生成する。
         * 具象クラスは、このメソッドをオーバーライドして、
         * 自身の具象ダイアログ・フラグメントを生成し、そのインスタンスを戻してください.
         * <p>
         * 同時に、{@link DialogFragment#setArguments(Bundle)}で設定する
         * パラメータのセットアップも、このメソッドの中で完了させてください.
         *
         * @return 生成した具象ダイアログ・フラグメント.
         */
        @NonNull
        protected abstract DialogFragment build();

        /**
         * ダイアログ・フラグメントを生成する.
         * このメソッドは、ビルド・メソッドの内部実装です.
         *
         * @param requestCode      リクエスト・コード.
         * @param callbackParent   親フラグメントへのコールバックが必要か否か.
         * @param callbackTarget   ターゲット・フラグメントへのコールバックが必要か否か.
         * @param callbackActivity アクティビティへのコールバックが必要か否か.
         * @return 生成したダイアログ.
         */
        @NonNull
        private DialogFragment build(int requestCode, boolean callbackParent, boolean callbackTarget, boolean callbackActivity) {
            DialogFragment dialog = build();
            Bundle args = dialog.getArguments();

            if (dialog.getArguments() == null) {
                args = new Bundle();
            }

            args.putInt(ARG_REQUEST_CODE, requestCode);
            args.putBoolean(ARG_CALLBACK_PARENT, callbackParent);
            args.putBoolean(ARG_CALLBACK_TARGET, callbackTarget);
            args.putBoolean(ARG_CALLBACK_ACTIVITY, callbackActivity);
            dialog.setArguments(args);
            dialog.setCancelable(mCancelable);

            return dialog;
        }

        /**
         * ダイアログをビルドします.
         * ダイアログの結果は、{@link Callback}を実装したアクティビティへ通知します.
         * ダイアログの表示に使用するフラグメント・マネージャは
         * {@link Activity#getFragmentManager()}
         * あるいは {@link AppCompatActivity#getSupportFragmentManager()}
         * を使用する必要があります.
         *
         * @param activity    現在のアクティビティ.
         * @param requestCode 結果コード.
         * @return 生成したダイアログ.
         * @see Fragment#getActivity()
         */
        @NonNull
        public final DialogFragment build(Activity activity, int requestCode) {
            return build(requestCode, false, false, true);
        }

        /**
         * ダイアログをビルドします.
         * ダイアログの結果は、{@link Callback}を実装したターゲット・フラグメントへ通知します.
         * ダイアログの表示に使用するフラグメント・マネージャは
         * {@link Fragment#getFragmentManager()}
         * を使用する必要があります.
         *
         * @param target      ターゲット・フラグメント.
         *                    ビルダの内部実装において、
         *                    {@link Fragment#setTargetFragment(Fragment, int)}
         *                    を使用して、ターゲットを設定します.
         * @param requestCode 結果コード.
         * @return 生成したダイアログ.
         * @see Fragment#getTargetFragment()
         */
        @NonNull
        public final DialogFragment build(Fragment target, int requestCode) {
            DialogFragment dialog = build(requestCode, false, true, false);
            dialog.setTargetFragment(target, requestCode);
            return dialog;
        }

        /**
         * ダイアログをビルドします.
         * ダイアログの結果は、{@link Callback}を実装した親フラグメントへ通知します.
         * ダイアログの表示に使用するフラグメント・マネージャは
         * {@link Fragment#getChildFragmentManager()}
         * を使用する必要があります.
         *
         * @param requestCode 結果コード.
         * @return 生成したダイアログ.
         * @see Fragment#getParentFragment()
         */
        @NonNull
        public final DialogFragment build(int requestCode) {
            return build(requestCode, true, false, false);
        }

    }

}
