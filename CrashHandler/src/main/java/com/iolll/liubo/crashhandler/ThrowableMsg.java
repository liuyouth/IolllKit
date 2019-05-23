package com.iolll.liubo.crashhandler;

/**
 * 异常信息
 * Created by LiuBo on 2019/4/16.
 */
public class ThrowableMsg {
    private String errorMsg;
    private String errorClassName;
    private long errorClassLineNumber;
    private String errorMethodName;

    public ThrowableMsg(Throwable stackTraceElement) {
        errorMsg = stackTraceElement.getMessage();
        errorClassName = stackTraceElement.getStackTrace()[0].getClassName();
        errorClassLineNumber = stackTraceElement.getStackTrace()[0].getLineNumber();
        errorMethodName = stackTraceElement.getStackTrace()[0].getMethodName();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorClassName() {
        return errorClassName;
    }

    public void setErrorClassName(String errorClassName) {
        this.errorClassName = errorClassName;
    }

    public long getErrorClassLineNumber() {
        return errorClassLineNumber;
    }

    public void setErrorClassLineNumber(long errorClassLineNumber) {
        this.errorClassLineNumber = errorClassLineNumber;
    }

    public String getErrorMethodName() {
        return errorMethodName;
    }

    public void setErrorMethodName(String errorMethodName) {
        this.errorMethodName = errorMethodName;
    }

    @Override
    public String toString() {
        return "ThrowableMsg{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorClassName='" + errorClassName + '\'' +
                ", errorClassLineNumber=" + errorClassLineNumber +
                ", errorMethodName='" + errorMethodName + '\'' +
                '}';
    }
}
