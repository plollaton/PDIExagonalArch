package br.com.pioneto.controlecontabancaria.api.interfaces;


import br.com.pioneto.controlecontabancaria.api.model.AccountCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.AccountDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AccountApi {

    @RequestMapping(value = "/account",
                    method = RequestMethod.POST,
                    produces = {"application/json"},
                    consumes = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                         description = "Success",
                         content = @Content(schema = @Schema(implementation = AccountDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<AccountDto> postAccount(@Parameter(name = "body", required = true) @RequestBody AccountCreationDto accountCreationDto) throws CustomerNotFoundException;

    @RequestMapping(value = "/account",
            method = RequestMethod.PUT,
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = AccountDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<AccountDto> putAccount(@Parameter(name = "body", required = true) @RequestBody AccountDto accountDto);

    @RequestMapping(value = "/account/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(schema = @Schema(implementation = AccountDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<AccountDto> getAccountById(@Parameter(name = "id", required = true) @PathVariable("id") Integer id);

    @RequestMapping(value = "/account/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Invalid account", content = @Content(schema = @Schema(implementation = Error.class))),
    })
    ResponseEntity<AccountDto> removeAccountById(@Parameter(name = "id", required = true) @PathVariable("id") Integer id);

    @RequestMapping(value = "/account/accounts",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = AccountDto.class)))),
    })
    ResponseEntity<List<AccountDto>> getAccounts(@Parameter(name = "id", required = true) @PathVariable("id") Integer id);

    @RequestMapping(value = "/account/accounts/customer/{customerId}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @Operation(responses = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = AccountDto.class)))),
    })
    ResponseEntity<List<AccountDto>> getAccountsByCustomer(@Parameter(name = "customerId", required = true) @PathVariable("customerId") Integer customerId);
}
