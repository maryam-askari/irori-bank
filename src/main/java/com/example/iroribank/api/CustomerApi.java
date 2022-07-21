package com.example.iroribank.api;


import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "customer")
@RequestMapping("/customer")
public interface CustomerApi {

  @Operation(summary = "Get all Customers")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = List.class))}),
      @ApiResponse(responseCode = "404", description = "Customer not found")})
  @GetMapping("/all-customers")
  ResponseEntity<List<Customer>> getAllCustomers();

  @Operation(summary = "Update an existing customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Customer not found"),
      @ApiResponse(responseCode = "405", description = "Validation exception")})
  @PutMapping("/update-customer/{customerId}")

  ResponseEntity updateCustomer(@Validated @ApiParam(value = "ID of customer to update")
                                @PathVariable("customerId") Integer customerId,
                                @Validated @ApiParam(required = true) CustomerRequest customerRequest);


  @Operation(summary = "Add a new customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "405", description = "Invalid input")})
  @PostMapping("/add-customer")
  ResponseEntity addCustomer(@Validated @ApiParam(required = true)
                             @RequestBody CustomerRequest customerRequest);

  @Operation(summary = "Find customer by Id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = CustomerRequest.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid ID"),
      @ApiResponse(responseCode = "404", description = "Customer not found")})
  @GetMapping("/find-customer/{customerId}")
  ResponseEntity<CustomerRequest> fidCustomer(@Validated @ApiParam(value = "customerId", example = "64", required = true)
                                              @PathVariable("customerId") Integer customerId);

  @Operation(summary = "Delete a customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "404", description = "Customer not found")
  })
  @DeleteMapping("/delete-customer/{customerId}")
  ResponseEntity deleteCustomer(@Validated @ApiParam(value = "customerId", example = "64", required = true)
                                @PathVariable("customerId") Integer customerId);
}
