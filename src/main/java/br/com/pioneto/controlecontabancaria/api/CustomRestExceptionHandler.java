package br.com.pioneto.controlecontabancaria.api;

import br.com.pioneto.controlecontabancaria.api.controllers.CustomerController;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.DuplicatedCustomerException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.InvalidCustomerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private final CustomerController api;

    public CustomRestExceptionHandler(CustomerController api) {
        this.api = api;
    }

    @ExceptionHandler({InvalidCustomerException.class})
    public ResponseEntity<Object> handleInvalidCustomerException(InvalidCustomerException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid CUstomer", error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DuplicatedCustomerException.class})
    public ResponseEntity<Object> handleDuplicatedCustomerException(DuplicatedCustomerException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Duplicated customer", error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Customer not found", error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
