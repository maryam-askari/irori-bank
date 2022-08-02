package com.example.iroribank.api;

import com.example.iroribank.model.dto.AccountRequest;
import com.example.iroribank.model.entity.Account;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Tag(name = "account")
@RequestMapping("/account")
public interface AccountAPI {

  @Operation(summary = "Get a list of all accounts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Account hase been updated successfully",
          content = {@Content(mediaType = "application/json"
              , schema = @Schema(implementation = List.class))}),
      @ApiResponse(responseCode = "404", description = "Account not found")})
  @GetMapping("/all-accounts")
  ResponseEntity<List<Account>> getAllAccounts();

  @Operation(summary = "Update an existing account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Account hase been updated successfully",
          content = {@Content(mediaType = "application/json"
              , schema = @Schema(implementation = AccountRequest.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "405", description = "Validation exception")
  })
  @PutMapping("/update-account/{accountId}")
  ResponseEntity updateAccount(@Validated @ApiParam(value = "ID of account to update")
                               @PathVariable("accountId") Integer accountId,
                               @RequestBody AccountRequest accountRequest);

  @Operation(summary = "add a new account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "405", description = "Validation input")
  })
  @PostMapping("/add-account")
  ResponseEntity addAccount(@Validated @RequestBody AccountRequest accountRequest);

  @Operation(summary = "Find account by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/xml",
              schema = @Schema(implementation = AccountRequest.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Account not found")
  })
  @GetMapping("/find-account/{accountId}")
  ResponseEntity<AccountRequest> findAccount(@ApiParam(value = "ID of account to return", example = "64", required = true)
                                             @PathVariable("accountId") Integer accountId);

  @Operation(summary = "Delete an account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
      @ApiResponse(responseCode = "404", description = "Account not found")
  })
  @DeleteMapping("/delete-account/{accountID}")
  ResponseEntity deleteAccount(@ApiParam(value = "Account id to delete", example = "64", required = true)
                               @PathVariable("accountId") Integer accountId);
}
