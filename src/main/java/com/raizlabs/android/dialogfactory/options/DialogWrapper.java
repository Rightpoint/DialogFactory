package com.raizlabs.android.dialogfactory.options;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dialogfactory.options.AlertThemeOptions;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description: Allows more customization than what android holo dialogs allow.
 */
public class DialogWrapper {

    private AlertDialog mAlert;

    public DialogWrapper(AlertDialog alertDialog){
        mAlert = alertDialog;
    }

    public void applyThemeOptions(AlertThemeOptions alertThemeOptions){

        if(alertThemeOptions != null) {

            if (alertThemeOptions.getTitleTextColorRes() != 0) {
                View title = mAlert.getWindow().findViewById(mAlert.getContext().getResources().getIdentifier("alertTitle", "id", "android"));
                if (title != null) {
                    ((TextView) title).setTextColor(getAlertColor(alertThemeOptions.getTitleTextColorRes()));
                }
            }

            if (alertThemeOptions.getTitleDividerColor() != 0) {
                View divider = mAlert.getWindow().findViewById(mAlert.getContext().getResources().getIdentifier("titleDivider", "id", "android"));
                if (divider != null) {
                    divider.setBackgroundColor(getAlertColor(alertThemeOptions.getTitleDividerColor()));
                }
            }

            if (alertThemeOptions.getButtonTextColorRes() != 0) {
                for (int i = -3; i < 0; i++) {
                    Button button = mAlert.getButton(i);
                    if (button != null) {
                        button.setTextColor(getAlertColor(alertThemeOptions.getButtonTextColorRes()));
                    }
                }
            }

            View message = mAlert.getWindow().findViewById(mAlert.getContext().getResources().getIdentifier("message", "id", "android"));
            if (message != null) {
                if (alertThemeOptions.getMessageTextColor() != 0) {
                    ((TextView) message).setTextColor(alertThemeOptions.getTitleTextColorRes());
                }

                if (alertThemeOptions.getMessageTextGravity() != 0) {
                    ((TextView) message).setGravity(alertThemeOptions.getMessageTextGravity());
                }
            }

        }
    }

    protected int getAlertColor(int resColor){
        return mAlert.getContext().getResources().getColor(resColor);
    }

    public void show() {
        mAlert.show();
    }

    public AlertDialog getDialog() {
        return mAlert;
    }
}
