package br.com.pioneto.controlecontabancaria.api.interfaces;

import br.com.pioneto.controlecontabancaria.api.ApiError;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.DuplicatedCustomerException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.InvalidCustomerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerApi {

    @RequestMapping(value = "/customer",
            method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid account",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409",
                    description = "Duplicate customer",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    ResponseEntity<CustomerDto> postCustomer(@Parameter(name = "body", required = true) @RequestBody CustomerCreationDto customer) throws InvalidCustomerException, DuplicatedCustomerException;

    @RequestMapping(value = "/customer",
            method = RequestMethod.PUT,
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<CustomerDto> putAccount(@Parameter(name = "body", required = true) @RequestBody CustomerDto customer) throws CustomerNotFoundException;

    @RequestMapping(value = "/customer",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<CustomerDto> getAccount(@RequestParam(required = false) Integer customerId, @RequestParam(required = false) String customerDocto) throws CustomerNotFoundException;

//    @RequestMapping(value = "/customer/{customerDocto}",
//            method = RequestMethod.GET,
//            produces = {"application/json"})
//    @Operation(responses = {
//            @ApiResponse(responseCode = "200",
//                    description = "Success",
//                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
//            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
//            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = Error.class))),
//    })
//    ResponseEntity<CustomerDto> getAccountByDocto(@Parameter(name = "customerDocto", required = true) @PathVariable("customerDocto") String customerDocto) throws CustomerNotFoundException;

    @RequestMapping(value = "/customer/{customerId}",
            method = RequestMethod.DELETE,
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity deleteAccount(Integer customerId) throws CustomerNotFoundException;

    @RequestMapping(value = "/customer/customers",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = CustomerDto.class)))),
    })
    ResponseEntity<List<CustomerDto>> getCustomers();

}

