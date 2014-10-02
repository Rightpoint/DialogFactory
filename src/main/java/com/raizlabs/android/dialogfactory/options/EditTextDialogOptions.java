package com.raizlabs.android.dialogfactory.options;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;

import com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description:
 */
public class EditTextDialogOptions extends DialogOptions<EditTextDialogOptions> {

    private String text;

    private String hint;

    private int inputType;

    private int layout;

    private InputFilter[] mInputFilters;

    private EditTextDialogBuilder.ValidityChecker mValidityChecker;

    private EditTextDialogBuilder.ValidityResponseListener mValidityResponseListener;

    @Override
    protected AlertDialog.Builder getBuilder(Context context) {
        EditTextDialogBuilder editTextDialogBuilder;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD) {
            editTextDialogBuilder = new EditTextDialogBuilder(context, dialog_theme, inputType, layout, hint);
        } else {
            editTextDialogBuilder = new EditTextDialogBuilder(context, inputType, layout, hint);
        }
        editTextDialogBuilder
                .setValidityChecker(mValidityChecker)
                .setValidityResponseListener(mValidityResponseListener);
        if (!TextUtils.isEmpty(text)) {
            editTextDialogBuilder.setText(text);
        }
        if (mInputFilters != null) {
            editTextDialogBuilder.setFilters(mInputFilters);
        }

        return editTextDialogBuilder;
    }

    /**
     * The text to prefill the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder} with
     *
     * @param text
     * @return
     */
    public EditTextDialogOptions editText(String text) {
        this.text = text;
        return this;
    }

    /**
     * The hint to prefill the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder} with
     *
     * @param hint
     * @return
     */
    public EditTextDialogOptions hint(String hint) {
        this.hint = hint;
        return this;
    }

    /**
     * The input type of the {@link android.widget.EditText}
     * @param inputType
     * @return
     */
    public EditTextDialogOptions inputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    /**
     * The layout to inflate the dialog in. Must contain only one {@link android.widget.EditText}
     * @param layout
     * @return
     */
    public EditTextDialogOptions layout(int layout) {
        this.layout = layout;
        return this;
    }

    /**
     * Sets the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder} input filters
     *
     * @param inputFilters
     * @return
     */
    public EditTextDialogOptions inputFilters(InputFilter...inputFilters) {
        mInputFilters = inputFilters;
        return this;
    }

    /**
     * Used for a {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder}. Ensures the text inputted is valid
     *
     * @param validityChecker
     * @return
     */
    public EditTextDialogOptions validityChecker(EditTextDialogBuilder.ValidityChecker validityChecker) {
        mValidityChecker = validityChecker;
        return this;
    }

    /**
     * Called when the input fails or succeeds in the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityChecker}
     *
     * @param validityResponseListener
     * @return
     */
    public EditTextDialogOptions validityResponseListener(EditTextDialogBuilder.ValidityResponseListener validityResponseListener) {
        mValidityResponseListener = validityResponseListener;
        return this;
    }

    @Override
    public EditTextDialogOptions onPositiveClick(DialogInterface.OnClickListener onClickListener) {
        throw new IllegalArgumentException("You cannot override the positive button in this dialog.");
    }
}
