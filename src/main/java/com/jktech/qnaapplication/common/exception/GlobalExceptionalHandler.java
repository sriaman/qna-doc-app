package com.jktech.qnaapplication.common.exception;

import com.jktech.qnaapplication.common.util.AppUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String timestamp = AppUtils.getCurrentTimestamp();
        String errorMessage = "Timestamp: " + timestamp + " | Error: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + errorMessage);
    }
}
