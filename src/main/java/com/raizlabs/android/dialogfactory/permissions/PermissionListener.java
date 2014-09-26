package com.raizlabs.android.dialogfactory.permissions;

/**
 * Author: andrewgrosner
 * Contributors: { }
 * Description:
 */
public abstract class PermissionListener {

    /**
     * Called when the user has accepted the permission.
     */
    public abstract void onPermissionAccepted();

    /**
     * Called when the user has denied access to this permission
     */
    public abstract void onPermissionDenied();

    /**
     * Called when the permission is not available (user has disabled it, or app does not have it in the manifest)
     */
    public void onPermissionNotAvailable() {

    }
}
