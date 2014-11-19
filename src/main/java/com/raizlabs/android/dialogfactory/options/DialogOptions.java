package com.raizlabs.android.dialogfactory.options;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ListAdapter;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description: Provides a handy simple way to build a dialog from dialog module,
 * minus the hassle of worrying about parameters
 */
public class DialogOptions<OPTIONS_TYPE extends DialogOptions> {

    String title;

    int title_res;

    String message;

    int message_res;

    String positive_label;

    int positive_label_res;

    String negative_label;

    int negative_label_res;

    String neutral_label;

    int neutral_label_res;

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
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

        String alertTitle = chooseLabel(context, title, title_res);
        if(!TextUtils.isEmpty(alertTitle)){
            builder.setTitle(alertTitle);
        }

        String alertMessage = chooseLabel(context, message, message_res);
        if(!TextUtils.isEmpty(alertMessage)) {
            builder.setMessage(alertMessage);
        }
        String positive = chooseLabel(context, positive_label, positive_label_res);
        if(!TextUtils.isEmpty(positive)) {
            builder.setPositiveButton(positive, mPositiveOnClick);
        }

        String negative = chooseLabel(context, negative_label, negative_label_res);
        if(!TextUtils.isEmpty(negative)) {
            builder.setNegativeButton(negative, mNegativeOnClick);
        }

        String neutral = chooseLabel(context, neutral_label, neutral_label_res);
        if(!TextUtils.isEmpty(neutral)) {
            builder.setNeutralButton(neutral, mNeutralOnClick);
        }

        if(Build.VERSION.SDK_INT >= 17){
                builder.setOnDismissListener(mOnDismissListener);
        }

        builder.setCancelable(cancelable);
        builder.setOnCancelListener(mOnCancelListener);
        if(mAdapter!=null){
            builder.setAdapter(mAdapter, mListClickListener);
        }
        return builder;
    }

    /**
     * Chooses which label to use. The first priority is the string label, and if this is empty, the int label will be used.
     * If the int label is 0, we will return null.
     * @param context
     * @param label
     * @param labelRes
     * @return
     */
    protected static String chooseLabel(Context context, String label, int labelRes) {
        String retString = label;

        if(TextUtils.isEmpty(retString) && labelRes != 0) {
            retString = context.getString(labelRes);
        }

        return retString;
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
    public OPTIONS_TYPE setTitle(String title){
        this.title = title;
        return castThis();
    }

    /**
     * The title for this dialog
     * @param titleRes
     * @return
     */
    public OPTIONS_TYPE setTitle(int titleRes){
        this.title_res = titleRes;
        return castThis();
    }

    /**
     * The message for this dialog
     * @param message
     * @return
     */
    public OPTIONS_TYPE setMessage(String message){
        this.message = message;
        return castThis();
    }

    /**
     * The message for this dialog
     * @param messageRes
     * @return
     */
    public OPTIONS_TYPE setMessage(int messageRes){
        this.message_res = messageRes;
        return castThis();
    }

    /**
     * The positive text label
     * @param positiveLabel
     * @return
     */
    public OPTIONS_TYPE setPositiveLabel(String positiveLabel){
        positive_label = positiveLabel;
        return castThis();
    }

    /**
     * The positive text label
     * @param positiveLabelRes
     * @return
     */
    public OPTIONS_TYPE setPositiveLabel(int positiveLabelRes){
        positive_label_res = positiveLabelRes;
        return castThis();
    }

    /**
     * The negative text label
     * @param negativeLabel
     * @return
     */
    public OPTIONS_TYPE setNegativeLabel(String negativeLabel){
        negative_label = negativeLabel;
        return castThis();
    }

    /**
     * The negative text label
     * @param negativeLabelRes
     * @return
     */
    public OPTIONS_TYPE setNegativeLabel(int negativeLabelRes){
        negative_label_res = negativeLabelRes;
        return castThis();
    }

    /**
     * The neutral/middle text label
     * @param neutralLabel
     * @return
     */
    public OPTIONS_TYPE setNeutralLabel(String neutralLabel){
        neutral_label = neutralLabel;
        return castThis();
    }

    /**
     * The neutral text label
     * @param neutralLabelRes
     * @return
     */
    public OPTIONS_TYPE setNeutralLabel(int neutralLabelRes){
        neutral_label_res = neutralLabelRes;
        return castThis();
    }

    public OPTIONS_TYPE setCancelable(boolean cancelable){
        this.cancelable = cancelable;
        return castThis();
    }

    /**
     * Called when the {@link android.app.Dialog#BUTTON_POSITIVE} is clicked. Default is to cancel the dialog.
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE setOnPositiveClick(DialogInterface.OnClickListener onClickListener){
        mPositiveOnClick = onClickListener;
        return castThis();
    }

    /**
     * Called when user taps on {@link android.app.Dialog#BUTTON_NEGATIVE}
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE setOnNegativeClick(DialogInterface.OnClickListener onClickListener){
        mNegativeOnClick = onClickListener;
        return castThis();
    }

    /**
     * Called when user taps on {@link android.app.Dialog#BUTTON_NEUTRAL}
     * @param onClickListener
     * @return
     */
    public OPTIONS_TYPE setOnNeutralClick(DialogInterface.OnClickListener onClickListener){
        mNeutralOnClick = onClickListener;
        return castThis();
    }

    /**
     * Sets an adapter for the dialog to use
     * @param listAdapter
     * @return
     */
    public OPTIONS_TYPE setListAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener listClickListener){
        mAdapter = listAdapter;
        mListClickListener = listClickListener;
        return castThis();
    }

    /**
     * Whether the dialog with dismiss on touch outside.
     * @param dismissOnTouchOutside
     * @return
     */
    public OPTIONS_TYPE setDismissOnTouchOutside(boolean dismissOnTouchOutside){
        this.dismissOnTouchOutside = dismissOnTouchOutside;
        return castThis();
    }

    /**
     * Called when the dialog is cancelled.
     * @param onCancelListener
     * @return
     */
    public OPTIONS_TYPE setOnCancelListener(DialogInterface.OnCancelListener onCancelListener){
        mOnCancelListener = onCancelListener;
        return castThis();
    }

    /**
     * Only used in API 17 or greater
     * @param onDismissListener
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public OPTIONS_TYPE setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
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
