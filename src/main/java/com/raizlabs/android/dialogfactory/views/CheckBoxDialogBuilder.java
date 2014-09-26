package com.raizlabs.android.dialogfactory.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.raizlabs.android.core.CompatibilityUtils;

/**
 * This class constructs a dialog with a checkbox easily
 */
public class CheckBoxDialogBuilder extends AlertDialog.Builder implements CompoundButton.OnCheckedChangeListener {

    /**
     * The Checkbox of the
     */
    private CheckBox mCheck;

    /**
     * Called when the Check state changes
     */
    private CompoundButton.OnCheckedChangeListener mOnCheckChangeListener;

    public CheckBoxDialogBuilder(Context context, int layoutRes) {
        super(context);
        setupUI(context, layoutRes);
    }

    public CheckBoxDialogBuilder(Context context, int theme, int layoutRes) {
        super(context, theme);
        setupUI(context, 0);
    }

    /**
     * Creates the interface elements
     *
     * @param context
     * @param layoutRes
     */
    protected void setupUI(Context context, int layoutRes) {
        View layout;
        if (layoutRes == 0) {
            layout = new FrameLayout(context);
            mCheck = new CheckBox(context);
            mCheck.setOnCheckedChangeListener(this);
            mCheck.setGravity(Gravity.CENTER);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            ((FrameLayout) layout).addView(mCheck, params);

        } else {
            layout = LayoutInflater.from(context).inflate(layoutRes, null, false);
            if (layout instanceof ViewGroup) {
                ViewGroup container = ((ViewGroup) layout);
                for (int i = 0; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof CheckBox) {
                        mCheck = (CheckBox) child;
                        break;
                    }
                }
            } else if (layout instanceof CheckBox) {
                mCheck = (CheckBox) layout;
            }

            if (mCheck == null) {
                throw new RuntimeException("The layout specified needs to be either a " +
                        "checkbox or contain a single checkbox within it.");
            }
        }

        setView(layout);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mOnCheckChangeListener != null) {
            mOnCheckChangeListener.onCheckedChanged(buttonView, isChecked);
        }
    }

    /**
     * Sets text on the internal checkbox
     * @param text
     * @return
     */
    public CheckBoxDialogBuilder setCheckBoxText(String text) {
        mCheck.setText(text);
        return this;
    }

    /**
     * Sets the background resource of the Checkbox
     * @param resId
     * @return
     */
    public CheckBoxDialogBuilder setCheckBoxBackgroundResource(int resId) {
        mCheck.setBackgroundResource(resId);
        return this;
    }

    /**
     * Sets the background drawable of the checkbox
     * @param background
     * @return
     */
    public CheckBoxDialogBuilder setCheckBoxBackground(Drawable background) {
        if (CompatibilityUtils.isAboveOrEqualToApiLevel(16)) {
            mCheck.setBackground(background);
        } else {
            mCheck.setBackgroundDrawable(background);
        }
        return this;
    }

    public CheckBoxDialogBuilder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckChangeListener = onCheckedChangeListener;
        return this;
    }

    /**
     * Returns the checkbox within this dialog
     * @return
     */
    public CheckBox getCheckBox() {
        return mCheck;
    }
}
