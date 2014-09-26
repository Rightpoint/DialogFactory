package com.raizlabs.android.dialogfactory.options;

/**
 * Created by andrewgrosner
 * Date: 6/11/14
 * Contributors:
 * Description: Allows certain options that the app will use globally.
 */
public class AlertThemeOptions {


    private int title_text_color_res;

    private int title_divider_color;

    private int button_text_color;

    private int message_text_color;

    private int message_text_gravity;

    public AlertThemeOptions titleTextColorRes(int titleTextColorRes){
        title_text_color_res = titleTextColorRes;
        return this;
    }

    public AlertThemeOptions titleDividerColor(int titleDividerColor){
        title_divider_color = titleDividerColor;
        return this;
    }

    public AlertThemeOptions buttonTextColorRes(int buttonTextColor){
        button_text_color = buttonTextColor;
        return this;
    }

    public AlertThemeOptions messageTextColor(int messageTextColor){
        message_text_color = messageTextColor;
        return this;
    }

    public AlertThemeOptions messageTextGravity(int gravity){
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
