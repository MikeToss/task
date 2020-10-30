package com.task.exeptions;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * @Extends of {@link RuntimeException} class.
 */

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable e) {
        super(message, e);
    }
}