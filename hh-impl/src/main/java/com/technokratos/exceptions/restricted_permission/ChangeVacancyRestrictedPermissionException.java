package com.technokratos.exceptions.restricted_permission;

public class ChangeVacancyRestrictedPermissionException extends RestrictedPermissionHhException{
    public ChangeVacancyRestrictedPermissionException() {
        super("Current user does not have permission to change the vacancy");
    }
}
