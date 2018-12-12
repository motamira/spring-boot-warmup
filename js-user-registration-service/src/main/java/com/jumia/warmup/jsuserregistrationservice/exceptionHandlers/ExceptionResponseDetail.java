package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExceptionResponseDetail {

    String fieldError;
    String defaultMessage;

    @Builder
    public ExceptionResponseDetail(String fieldError, String defaultMessage) {
        this.fieldError = fieldError;
        this.defaultMessage = defaultMessage;
    }
}