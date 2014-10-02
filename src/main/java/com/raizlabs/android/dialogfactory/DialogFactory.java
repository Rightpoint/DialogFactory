package com.raizlabs.android.dialogfactory;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import com.raizlabs.android.dialogfactory.options.AlertThemeOptions;
import com.raizlabs.android.dialogfactory.options.DialogOptions;
import com.raizlabs.android.dialogfactory.options.DialogWrapper;
import com.raizlabs.android.dialogfactory.options.ProgressDialogOptions;

public class DialogFactory {

    private static AlertThemeOptions DEFAULT_THEME_OPTIONS;

    /**
     * Set a global dialog theme across the application
     *
     * @param alertThemeOptions
     */
    public static void setGlobalThemeOptions(AlertThemeOptions alertThemeOptions) {
        DEFAULT_THEME_OPTIONS = alertThemeOptions;
    }

    /**
     * Creates a dialog based on the {@link com.raizlabs.android.dialogfactory.options.DialogOptions} passed
     *
     * @param context
     * @param dialogOptions
     * @return
     */
    public static DialogWrapper makeDialog(Context context, DialogOptions dialogOptions) {
        return new DialogWrapper(dialogOptions.build(context).create());
    }

    /**
     * Makes a progress dialog based on the {@link com.raizlabs.android.dialogfactory.options.ProgressDialogOptions} passed.
     * It will show the dialog.
     *
     * @param context
     * @param progressDialogOptions
     * @return
     */
    public static DialogWrapper makeProgressDialog(Context context, ProgressDialogOptions progressDialogOptions) {
        return new DialogWrapper(progressDialogOptions.showProgress(context));
    }

    /**
     * Shows a dialog using {@link com.raizlabs.android.dialogfactory.options.DialogOptions} and {@link com.raizlabs.android.dialogfactory.options.AlertThemeOptions}
     *
     * @param context
     * @param dialogOptions
     * @param alertThemeOptions
     */
    public static void showDialog(Context context, DialogOptions dialogOptions, AlertThemeOptions alertThemeOptions) {
        if(context instanceof Application) {
            throw new IllegalArgumentException("You cannot use an application context for a dialog. Please use an Activity or Service");
        }
        DialogWrapper dialogWrapper = makeDialog(context, dialogOptions);
        dialogWrapper.show();
        dialogWrapper.applyThemeOptions(alertThemeOptions);
    }

    /**
     * Shows a dialog using {@link com.raizlabs.android.dialogfactory.options.DialogOptions}
     *
     * @param context
     * @param dialogOptions
     */
    public static void showDialog(Context context, DialogOptions dialogOptions) {
        showDialog(context, dialogOptions, DEFAULT_THEME_OPTIONS);
    }

    /**
     * Shows a progress dialog, which is different than a regular dialog.
     *
     * @param context
     * @param progressDialogOptions
     * @return dialog - a handle to the dialog in order to notify it of data changes, or to cancel it.
     */
    public static ProgressDialog showProgressDialog(Context context, ProgressDialogOptions progressDialogOptions) {
        return showProgressDialog(context, progressDialogOptions, DEFAULT_THEME_OPTIONS);
    }

    /**
     * Shows a progress dialog, which is different than a regular dialog.
     *
     * @param context
     * @param progressDialogOptions
     * @param alertThemeOptions
     * @return dialog - a handle to the dialog in order to notify it of data changes, or to cancel it.
     */
    public static ProgressDialog showProgressDialog(Context context, ProgressDialogOptions progressDialogOptions, AlertThemeOptions alertThemeOptions) {
        DialogWrapper dialogWrapper = makeProgressDialog(context, progressDialogOptions);
        dialogWrapper.applyThemeOptions(alertThemeOptions);
        return (ProgressDialog) dialogWrapper.getDialog();
    }
}
