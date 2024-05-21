package com.pdi.hexago.web;

import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.exceptions.AccountNotFoundException;
import com.pdi.hexago.domains.customers.exceptions.CustomerNotFoundException;
import com.pdi.hexago.domains.customers.exceptions.ExistingCustomerException;
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

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Region Customer Exceptions

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ExistingCustomerException.class)
    public ResponseEntity<ErrorResponse> handleExistingCustomerException(ExistingCustomerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // End Region
}
