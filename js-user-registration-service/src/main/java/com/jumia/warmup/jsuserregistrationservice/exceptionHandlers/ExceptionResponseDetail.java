package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

public class ExceptionResponseDetail {

    String fieldError;
    String defaultMessage;

    public ExceptionResponseDetail() {
    }

    public ExceptionResponseDetail(String x, String y) {
        this.fieldError = x;
        this.defaultMessage = y;
    }

    public String getFieldError() {
        return fieldError;
    }

    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
        return "ExceptionResponseDetail{" +
            "fieldError='" + fieldError + '\'' +
            ", defaultMessage='" + defaultMessage + '\'' +
            '}';
    }
}
