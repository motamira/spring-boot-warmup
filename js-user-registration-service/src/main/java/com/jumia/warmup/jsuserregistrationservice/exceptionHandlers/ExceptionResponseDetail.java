package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExceptionResponseDetail {

    String fieldError;
    String defaultMessage;

    public ExceptionResponseDetail() {
    }

    public ExceptionResponseDetail(String x, String y) {
        this.fieldError = x;
        this.defaultMessage = y;
    }
}
