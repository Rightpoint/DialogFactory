package com.raizlabs.android.dialogfactory;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment newInstance(String title, String message,boolean cancelable) {
    	ProgressDialogFragment frag = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putBoolean("cancelable", cancelable);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        boolean cancelable = getArguments().getBoolean("cancelable");
        
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle(title);
        setCancelable(cancelable);
        dialog.setIndeterminate(true);
        dialog.setMessage(message);
        return dialog;
    }
}