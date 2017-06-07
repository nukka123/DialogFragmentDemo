DialogFragmentDemo
===

## Overview

We present a better practice on DialogFragment.

This demo introduces a mechanism to call back the result to the host as an abstract class. I created an alert dialog and a picker dialog as an example of a concrete class.


## Sample

```java
public class MainFragment extends Fragment implements AbstractDialogFragment.Callback {
    void showDialog() {
        new AlertDialogFragment.Builder()
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Alert Title")
                .setMessage("Hello Alert Dialog World!")
                .setPositiveButton("Agree")
                .setNeutralButton("Cancel")
                .setNegativeButton("Disagree")
                .setCancelable(true)
                .build(this, 123)
                .show(getFragmentManager(), "dialog");
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onDialogResult: "
                + "requestCode=" + requestCode + ", "
                + "resultCode=" + resultCode + ", "
                + "data=" + data + ", "
                + "extras=" + (data != null ? data.getExtras() : "null"));
    }
}
```
