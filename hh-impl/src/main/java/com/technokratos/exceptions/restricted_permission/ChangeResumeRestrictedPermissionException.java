package com.technokratos.exceptions.restricted_permission;

public class ChangeResumeRestrictedPermissionException extends RestrictedPermissionHhException {
    public ChangeResumeRestrictedPermissionException() {
        super("Current user does not have permission to change the resume");
    }
}