package com.raizlabs.android.dialogfactory.options;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by andrewgrosner
 * Description: Allows certain options that the app will use globally or on a dialog specific basis.
 */
public class AlertThemeOptions {

    private int title_text_color_res;

    private int title_divider_color;

    private int button_text_color;

    private int message_text_color;

    private int message_text_gravity;

    /**
     * Applies the tint from android support tint manager to this dialog.
     * @return
     */
    public AlertThemeOptions applyTint(Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName()),
                typedValue, true);
        int color = typedValue.data;
        setTitleTextColorRes(color);
        setMessageTextColor(color);
        setButtonTextColorRes(color);
        return this;
    }

    /**
     * Sets the color of the Title
     * @param titleTextColorRes
     * @return
     */
    public AlertThemeOptions setTitleTextColorRes(int titleTextColorRes){
        title_text_color_res = titleTextColorRes;
        return this;
    }

    /**
     * Sets the color of the divider
     * @param titleDividerColor
     * @return
     */
    public AlertThemeOptions seTitleDividerColor(int titleDividerColor){
        title_divider_color = titleDividerColor;
        return this;
    }

    /**
     * Applies a text color to all inner buttons
     * @param buttonTextColor
     * @return
     */
    public AlertThemeOptions setButtonTextColorRes(int buttonTextColor){
        button_text_color = buttonTextColor;
        return this;
    }

    /**
     * Applies a message text color to the dialog
     * @param messageTextColor
     * @return
     */
    public AlertThemeOptions setMessageTextColor(int messageTextColor){
        message_text_color = messageTextColor;
        return this;
    }

    /**
     * Applies a gravity to the message text
     * @param gravity
     * @return
     */
    public AlertThemeOptions setMessageTextGravity(int gravity){
        message_text_gravity = gravity;
        return this;
    }

    int getTitleTextColorRes() {
        return title_text_color_res;
    }

    int getTitleDividerColor() {
        return title_divider_color;
    }

    int getButtonTextColorRes() {
        return button_text_color;
    }

    int getMessageTextColor(){
        return message_text_color;
    }

    int getMessageTextGravity(){
        return message_text_gravity;
    }
}
