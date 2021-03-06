package com.hackaton.response;


public enum OperationStatus {
    SUCCESS(0),
    PARSE_EXCEPTION(1),
    DATA_NOT_FOUND(2),
    INTERNAL_ERROR(99);

    public final int code;

    OperationStatus(int code) {
        this.code = code;
    }

    public boolean isSuccessful() {
        return this == SUCCESS;
    }
}
