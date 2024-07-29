package com.services.ms.student.app.utils;

public enum ErrorCatalogEnum {

    STUDENT_NOT_FOUND("ERR_STUDENT_001", "Student not found."),
    INVALID_STUDENT("ERR_STUDENT_002", "Invalid student parameters."),
    GENERIC_ERROR("ERR_GENERIC_SERVER_001", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalogEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
