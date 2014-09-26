package com.raizlabs.android.dialogfactory.options;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.ListAdapter;

import com.raizlabs.android.core.CompatibilityUtils;
import com.raizlabs.android.core.StringUtils;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description: Provides a handy simple way to build a dialog from dialog module,
 * minus the hassle of worrying about parameters
 */
public class DialogOptions<OPTIONS_TYPE extends DialogOptions> {

    String title;

    String message;

    String positive_label;

    String negative_label;

    String neutral_label;

    boolean cancelable = true;

    boolean dismissOnTouchOutside = false;

    ListAdapter mAdapter;

    DialogInterface.OnClickListener mListClickListener;

    DialogInterface.OnCancelListener mOnCancelListener;

    DialogInterface.OnDismissListener mOnDismissListener;

    protected static DialogInterface.OnClickListener DEFAULT_ONCLICK = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    DialogInterface.OnClickListener mPositiveOnClick = DEFAULT_ONCLICK;

    DialogInterface.OnClickListener mNegativeOnClick = mPositiveOnClick;

    DialogInterface.OnClickListener mNeutralOnClick = mPositiveOnClick;

    int dialog_theme;


    /**
     * Override this method to use a different subclass of {@link android.app.AlertDialog.Builder}
     * @param context
     * @return
     */
    protected AlertDialog.Builder getBuilder(Context context){
        if(CompatibilityUtils.isAboveGingerbread()) {
            return new AlertDialog.Builder(context, dialog_theme);
        } else{
            return new AlertDialog.Builder(context);
        }
    }

    /**
     * Constructs an{@link android.app.AlertDialog.Builder}
     * @return
     */
    public final AlertDialog.Builder build(Context context){
        AlertDialog.Builder builder = getBuilder(context);
        if(StringUtils.isNotNullOrEmpty(title)){
            builder.setTitle(title);
        }
        builder.setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positive_label, mPositiveOnClick)
                .setNegativeButton(negative_label, mNegativeOnClick)
                .setNeutralButton(neutral_label, mNeutralOnClick);
        if(CompatibilityUtils.isAboveOrEqualToApiLevel(17)){
                builder.setOnDismissListener(mOnDismissListener);
        }
        builder.setOnCancelListener(mOnCancelListener);
        if(mAdapter!=null){
            builder.setAdapter(mAdapter, mListClickListener);
        }
        return builder;
    }

    /**
     * Sets a theme you can apply to the dialog usiong styleble resources
     * @param theme
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public OPTIONS_TYPE theme(int theme){
        dialog_theme = theme;
        return castThis();
    }

    /**
     * The title for this dialog
     * @param title
     * @return
     */
    public OPTIONS_TYPE title(String title){
        this.title = title;
        return castThis();
    }

    /**
     * The message for this dialog
     * @param message
     * @return
     */
    public OPTIONS_TYPE message(String message){
        this.message = message;
        return castThis();
    }

    /**
     * The positive text label
     * @param positiveLabel
     * @return
     */
    public OPTIONS_TYPE positiveLabel(String positiveLabel){
        positive_label = positiveLabel;
        return castThis();
    }

    /**
     * The negative text label
     * @param negativeLabel
     * @return
     */
    public OPTIONS_TYPE negativeLabel(String negativeLabel){
        negative_label = negativeLabel;
        return castThis();
    }

    /**
     * The neutral/middle text label
     * @param neutralLabel
     * @return
     */
    public OPTIONS_TYPE neutralLabel(String neutralLabel){
        neutral_label = neutralLabel;
        return castThis();
    }

    public OPTIONS_TYPE cancelable(boolean cancelable){
        this.cancelable = cancelable;
        return castThis();
    }

    /**
     * Called when the {@link android.app.Dialog#BUTTON_POSITIVE} is clicked. Default is to cancel the dialog.
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE onPositiveClick(DialogInterface.OnClickListener onClickListener){
        mPositiveOnClick = onClickListener;
        return castThis();
    }

    /**
     * Called when user taps on {@link android.app.Dialog#BUTTON_NEGATIVE}
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE onNegativeClick(DialogInterface.OnClickListener onClickListener){
        mNegativeOnClick = onClickListener;
        return castThis();
    }

    /**
     * Called when user taps on {@link android.app.Dialog#BUTTON_NEUTRAL}
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE onNeutralClick(DialogInterface.OnClickListener onClickListener){
        mNeutralOnClick = onClickListener;
        return castThis();
    }

    /**
     * Sets an adapter for the dialog to use
     * @param listAdapter
     * @return
     */
    public OPTIONS_TYPE adapter(ListAdapter listAdapter, DialogInterface.OnClickListener listClickListener){
        mAdapter = listAdapter;
        mListClickListener = listClickListener;
        return castThis();
    }

    /**
     * Whether the dialog with dismiss on touch outside.
     * @param dismissOnTouchOutside
     * @return
     */
    public OPTIONS_TYPE dismissOnTouchOutside(boolean dismissOnTouchOutside){
        this.dismissOnTouchOutside = dismissOnTouchOutside;
        return castThis();
    }

    /**
     * Called when the dialog is cancelled.
     * @param onCancelListener
     * @return
     */
    public OPTIONS_TYPE onCancelListener(DialogInterface.OnCancelListener onCancelListener){
        mOnCancelListener = onCancelListener;
        return castThis();
    }

    /**
     * Only used in API 17 or greater
     * @param onDismissListener
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public OPTIONS_TYPE onDismissListener(DialogInterface.OnDismissListener onDismissListener){
        mOnDismissListener = onDismissListener;
        return castThis();
    }

    /**
     * Typical buttons we use in an app. Sets the positive label to "Ok"
     * @return
     */
    public OPTIONS_TYPE ok() {
        positive_label = "Ok";
        return castThis();
    }

    /**
     * Typical buttons we use in an app. Sets the negative label to "Cancel"
     * @return
     */
    public OPTIONS_TYPE cancel(){
        negative_label = "Cancel";
        return castThis();
    }


    /**
     * Typical buttons we use in an app. Sets the positive label to "Yes"
     * @return
     */
    public OPTIONS_TYPE yes(){
        positive_label = "Yes";
        return castThis();
    }


    /**
     * Typical buttons we use in an app. Sets the negative label to "No"
     * @return
     */
    public OPTIONS_TYPE no(){
        negative_label = "No";
        return castThis();
    }

    protected OPTIONS_TYPE castThis(){
        return (OPTIONS_TYPE)this;
    }

}
