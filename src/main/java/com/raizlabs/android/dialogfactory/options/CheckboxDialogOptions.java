package com.raizlabs.android.dialogfactory.options;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

import com.raizlabs.android.dialogfactory.views.CheckBoxDialogBuilder;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description:
 */
public class CheckboxDialogOptions extends DialogOptions {

    private String checkbox_text;

    private int checkbox_background_res;

    private Drawable checkbox_drawable;

    private int layout;

    private CompoundButton.OnCheckedChangeListener mOnCheckChangeListener;


    /**
     * Used for a {@link com.raizlabs.android.dialogfactory.views.CheckBoxDialogBuilder}, provides a callback for changes.
     *
     * @param onCheckedChangeListener
     * @return
     */
    public CheckboxDialogOptions setOnCheckChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckChangeListener = onCheckedChangeListener;
        return this;
    }

    @Override
    protected AlertDialog.Builder getBuilder(Context context) {
        CheckBoxDialogBuilder checkBoxDialogBuilder = new CheckBoxDialogBuilder(context, layout);
        checkBoxDialogBuilder.setCheckBoxText(checkbox_text);
        if (checkbox_background_res != 0) {
            checkBoxDialogBuilder.setCheckBoxBackgroundResource(checkbox_background_res);
        } else if (checkbox_drawable != null) {
            checkBoxDialogBuilder.setCheckBoxBackground(checkbox_drawable);
        }
        checkBoxDialogBuilder.setOnCheckedChangeListener(mOnCheckChangeListener);
        return checkBoxDialogBuilder;
    }

    /**
     * The layout to inflate in the dialog. Must only contain one {@link android.widget.CheckBox}
     * @param layout
     * @return
     */
    public CheckboxDialogOptions setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    /**
     * The text of the checkbox in this dialog
     *
     * @param text
     * @return
     */
    public CheckboxDialogOptions setCheckBoxText(String text) {
        checkbox_text = text;
        return this;
    }

    /**
     * Sets the background resource of the checkbox
     * @param resid
     * @return
     */
    public CheckboxDialogOptions setCheckboxBackgroundResource(int resid) {
        checkbox_background_res = resid;
        return this;
    }

    /**
     * Sets the custom drawable on the checkbox item
     * @param drawable
     * @return
     */
    public CheckboxDialogOptions setCheckboxDrawable(Drawable drawable) {
        checkbox_drawable = drawable;
        return this;
    }
}
