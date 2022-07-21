package com.example.iroribank.controller;

import com.example.iroribank.api.CustomerApi;
import com.example.iroribank.exception.CustomerNotFoundException;
import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;
import com.example.iroribank.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CustomerController implements CustomerApi {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public ResponseEntity<List<Customer>> getAllCustomers() {
    log.info("getting all customers");
    List<Customer> customers = customerService.allCustomers();
    return new ResponseEntity<>(customers,HttpStatus.OK);
  }

  @Override
  public ResponseEntity updateCustomer(Integer customerId, CustomerRequest customerRequest) {
    log.info("Updating customer by id {}", customerId);
    try {
      customerService.updateCustomer(customerId, customerRequest);
      return new ResponseEntity(HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      log.warn(e.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity addCustomer(CustomerRequest customerRequest) {
    log.info("Adding new customer");
    customerService.addCustomer(customerRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerRequest> fidCustomer(Integer customerId) {
    log.info("Finding customer by id {}", customerId);
    try {
      customerService.findCustomer(customerId);
      return new ResponseEntity(HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      log.warn(e.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity deleteCustomer(Integer customerId) {
    log.info("Deleting customer by id{}", customerId);
    customerService.deleteCustomer(customerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
