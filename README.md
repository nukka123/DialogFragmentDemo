DialogFragmentDemo
===

## Overview

DialogFragmentの実装は、なかなか面倒です。
先人達のプラクティスは色々ありますが、それらを集約させるにも経験が必要です。
そのためなのか (少なくとも自分が出会う) ダイアログに関する実装は低品質になりがち。と感じます。

ここでは DialogFragmentの結果をホストに渡す機能を提供する抽象クラス(AbstractDialogFragment)を紹介します。
そして ニーズの高い「単純なメッセージ形式のAlertDialog」を表示させる実装クラス例なども紹介します。

各自のDialogFragmentを実装する際の出発点として利用いただければ。と思います。

## Sample

```java
public class MainFragment extends Fragment implements AbstractDialogFragment.Callback {
・・・
    void showAlertDialog() {
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setPositiveButton(R.string.alert_ok)
                .setNeutralButton(R.string.alert_cancel)
                .setNegativeButton(R.string.alert_ng)
                .build(REQ_ID_ALERT_DIALOG)
                .showOn(this, DIALOG_TAG);
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Intent data) {
        // ダイアログのボタン押下時の処理
    }

    @Override
    public void onDialogCancelled(int requestCode) {
        // ダイアログのキャンセル時の処理
    }
・・・
}
```


