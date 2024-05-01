package com.pdi.hexago.web;

import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.IM_USED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.IM_USED).body(errorResponse);
    }
}
