package com.raizlabs.android.dialogfactory.permissions;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import com.raizlabs.android.dialogfactory.DialogFactory;
import com.raizlabs.android.dialogfactory.options.DialogOptions;

/**
 * Author: andrewgrosner
 * Contributors: { }
 * Description: This class is responsible for building the front end prompts for permissions.
 */
public class PermissionManager {

    public static void requestPermission(Context context, final String permission, final PermissionListener permissionListener) {
        requestPermission(context, permission, "Permission Request",
                context.getApplicationInfo().name + " is requesting: " + permission + ". Grant Access?",  permissionListener);
    }

    public static void requestPermission(Context context, final String permission, String alertTitle,
                                         String alertMessage, final PermissionListener permissionListener) {
        boolean hasPermission = context.getPackageManager().checkPermission(permission,
                context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if(!hasPermission) {
            if(permissionListener != null) {
                permissionListener.onPermissionNotAvailable();
            }
        } else {
            // TODO: move strings to resources, make dialog module, or make configurable.
            DialogFactory.showDialog(context, new DialogOptions().title(alertTitle).message(alertMessage)
                    .yes().no().onPositiveClick(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (permissionListener != null) {
                                permissionListener.onPermissionAccepted();
                            }
                        }
                    })
                    .onNegativeClick(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(permissionListener != null) {
                                permissionListener.onPermissionDenied();
                            }
                        }
                    }));
        }
    }
}
