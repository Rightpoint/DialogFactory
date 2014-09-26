package com.raizlabs.android.dialogfactory.options;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Author: andrewgrosner
 * Date: 6/24/14
 * Contributors: { }
 * Description: Wraps around {@link DialogOptions} only allowing using options
 * pertaining to {@link android.app.ProgressDialog#show(android.content.Context, CharSequence, CharSequence,
 * boolean, boolean, android.content.DialogInterface.OnCancelListener)}
 */
public class ProgressDialogOptions extends DialogOptions<ProgressDialogOptions> {

    boolean inDeterminate = false;

    public final ProgressDialog showProgress(Context context){
        return ProgressDialog.show(context, title, message, inDeterminate, cancelable, mOnCancelListener);
    }


    public ProgressDialogOptions inDeterminate(boolean inDeterminate){
        this.inDeterminate = inDeterminate;
        return this;
    }
}
