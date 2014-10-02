package com.raizlabs.android.dialogfactory.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * Created By: andrewgrosner
 * Date: 10/29/13
 * Contributors:
 * Description: wraps around the alertdialog builder to display a dialog with text input. Provides a couple handy interfaces to handle how the dialog responds to input.
 */
public class EditTextDialogBuilder extends AlertDialog.Builder {

    /**
     * Simple interface for validating the inputted text is valid.
     */
    public interface ValidityChecker {

        /**
         * Return true if the inputted text is valid text.
         *
         * @param text The text inputted by the user
         * @return true if its valid text, it will then call
         * {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityResponseListener#onSuccess(android.content.DialogInterface, String)}
         */
        public boolean isValid(String text);
    }

    /**
     * Default implementation states that any input is valid, or otherwise specified.
     */
    private ValidityChecker mChecker = new ValidityChecker() {
        @Override
        public boolean isValid(String text) {
            return true;
        }
    };

    /**
     * This will be called based on the results of the
     * {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityChecker#isValid(String)}
     */
    public interface ValidityResponseListener {

        /**
         * Signifies an unsuccessful input.
         * @param dialogInterface The dialog returned from {@link android.content.DialogInterface.OnClickListener}
         * @param text The text inputted by the user
         */
        public void onFailure(DialogInterface dialogInterface, String text);

        /**
         * Signifies a successful input. Do something here.
         * @param dialogInterface The dialog returned from {@link android.content.DialogInterface.OnClickListener}
         * @param text The text inputted by the user
         */
        public void onSuccess(DialogInterface dialogInterface, String text);
    }

    /**
     * The main text input for the user.
     */
    private EditText mTextBox;

    /**
     * The listener that will be called based on the result of the
     * {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityChecker}
     */
    private ValidityResponseListener mValidityResponseListener;

    /**
     * The non-override-able listener that calls the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityChecker}
     * and the {@link com.raizlabs.android.dialogfactory.views.EditTextDialogBuilder.ValidityResponseListener}
     */
    private DialogInterface.OnClickListener mOnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mChecker.isValid(getText())) {
                if (mValidityResponseListener != null) {
                    mValidityResponseListener.onSuccess(dialog, getText());
                }
            } else {
                if (mValidityResponseListener != null) {
                    mValidityResponseListener.onFailure(dialog, getText());
                }
            }
        }
    };

    public EditTextDialogBuilder(Context context, int theme, int inputType, int layoutRes, String hint) {
        super(context, theme);
        setupUI(context, inputType, layoutRes, hint);
    }

    public EditTextDialogBuilder(Context context, int inputType, int layoutRes, String hint) {
        super(context);
        setupUI(context, inputType, layoutRes, hint);
    }

    /**
     * Creates the interface elements
     *
     * @param context
     * @param inputType
     * @param layoutRes
     * @param hint
     */
    protected void setupUI(Context context, int inputType, int layoutRes, String hint) {
        View layout;
        if (layoutRes == 0) {
            layout = new FrameLayout(context);
            mTextBox = new EditText(context);
            mTextBox.setInputType(inputType == 0 ?
                    (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS) : inputType);
            mTextBox.setHint(hint);
            ((FrameLayout) layout).addView(mTextBox);
            int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                    context.getResources().getDisplayMetrics());
            layout.setPadding(dip / 2, dip, dip / 2, dip);
        } else {

            layout = LayoutInflater.from(context).inflate(layoutRes, null, false);
            if (layout instanceof ViewGroup) {
                ViewGroup container = ((ViewGroup) layout);
                for (int i = 0; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof EditText) {
                        mTextBox = (EditText) child;
                        break;
                    }
                }
            } else if (layout instanceof EditText) {
                mTextBox = (EditText) layout;
            }

            if (mTextBox == null) {
                throw new RuntimeException("The layout specified needs to be either an " +
                        "EditText or contain a single EditText within it.");
            }
        }

        setView(layout);
    }

    public String getText() {
        return mTextBox.getText().toString();
    }

    public EditTextDialogBuilder setHint(String hint) {
        mTextBox.setHint(hint);
        return this;
    }

    public EditTextDialogBuilder setText(String text) {
        mTextBox.setText(text);
        return this;
    }

    public EditTextDialogBuilder setFilters(InputFilter[] inputFilter) {
        mTextBox.setFilters(inputFilter);
        return this;
    }

    /**
     * Set this to perform validity checking of the edittext input to trigger InputCheckListener.onSuccess()
     *
     * @param checker
     */
    public EditTextDialogBuilder setValidityChecker(ValidityChecker checker) {
        mChecker = checker;
        return this;
    }

    /**
     * Set this to respond to onSucess() and onFailure() related to input of text within the edittext
     *
     * @param validityResponseListener
     */
    public EditTextDialogBuilder setValidityResponseListener(ValidityResponseListener validityResponseListener) {
        mValidityResponseListener = validityResponseListener;
        return this;
    }

    @Override
    public AlertDialog show() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        return super.show();
    }

    @Override
    public EditTextDialogBuilder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
        super.setPositiveButton(text, mOnClick);
        return this;
    }
}
