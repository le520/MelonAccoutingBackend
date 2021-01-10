package com.nwpu.melonbookkeeping.common;

public enum ErrorCodeEnum {
    USER_LOGIN_ERROR_UNAUTHORIZED("20001", "LoginName or password error."),
    USER_LOGIN_ERROR_LOCKED("20002", "User has been locked."),
    USER_TOKEN_INVALID("20003", "Invalid token information."),
    BOOKKEEPING_ADD_ERROR("20004", "Illegal parameter."),
    BOOKKEEPING_DELETE_ERROR("20005","Illegal parameter."),
    USER_REGISTER_ERROR("20006","Illegal parameter."),
    USER_MODIFY_ERROR("20007","Illegal parameter.");

    private final String[] error = new String[2];

    ErrorCodeEnum(String errorCode, String errorMessage) {
        error[0] = errorCode;
        error[1] = errorMessage;
    }

    public String[] getError() {
        return this.error;
    }

    public String getErrorCode() {
        return error[0];
    }

    public String getErrorMessage() {
        return error[1];
    }

    public void setErrorCode(String errorCode) {
        this.error[0] = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.error[1] = errorMessage;
    }
}
