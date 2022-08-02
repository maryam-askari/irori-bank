package com.example.iroribank.api;

import com.example.iroribank.model.dto.Balance;
import com.example.iroribank.model.dto.Deposit;
import com.example.iroribank.model.dto.Withdrawal;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "bank")
@RequestMapping("/bank")
public interface BankApi {

  @Operation(summary = "Return account balance in SEK")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Balance.class))})})
  @GetMapping("/{accountId}/balance")
  ResponseEntity<Double> getBalance(@ApiParam(value = "accountId", example = "64", required = true)
                                    @PathVariable("accountId") Integer accountId);

  @Operation(summary = "Deposit to account in SEK")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Deposit.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid deposit")})
  @PostMapping("/{accountId}/deposit")
  ResponseEntity<Double> depositToAccount(@ApiParam(value = "accountId", example = "64", required = true)
                                          @PathVariable("accountId") Integer accountId,
                                          @Validated @ApiParam(required = true)
                                          @RequestBody Deposit deposit);

  @Operation(summary = "Withdrawal amount from account in SEK")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Deposit.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid deposit")})
  @PostMapping("/{accountId}/withdrawal")
  ResponseEntity<Double> withdrawalFromAccount(@ApiParam(value = "accountId", example = "64", required = true)
                                               @PathVariable("accountId") Integer accountId,
                                               @Validated @ApiParam(required = true)
                                               @RequestBody Withdrawal withdrawal);

}
