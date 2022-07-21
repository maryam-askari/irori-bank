package com.example.iroribank.service;

import com.example.iroribank.exception.CustomerNotFoundException;
import com.example.iroribank.mapper.CustomerMapper;
import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;
import com.example.iroribank.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private CustomerMapper mapper;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> allCustomers() {
    log.info("getting all customers");
    return customerRepository.findAll();
  }

  @Override
  public void updateCustomer(Integer customerId, CustomerRequest customerRequest) {
    log.info("Updating customer by id {}", customerId);
    Optional<Customer> customerData = customerRepository.findById(customerId);
    if (customerData.isPresent()) {
      Customer customer = customerData.get();
      customer = mapper.customerRequestToCustomer(customerRequest);
      customerRepository.save(customer);
    } else {
      log.warn("Customer can not found with id {}", customerId);
      throw new CustomerNotFoundException("Customer does not exist with id: " + customerId);
    }
  }

  @Override
  public void addCustomer(CustomerRequest customerRequest) {
    log.info("Adding new Customer");
    Customer customer = mapper.customerRequestToCustomer(customerRequest);
    customerRepository.save(customer);
  }

  @Override
  public CustomerRequest findCustomer(Integer customerId) {
    log.info("Finding customer by id {}", customerId);
    Optional<Customer> customerData = customerRepository.findById(customerId);
    if (customerData.isPresent()) {
      CustomerRequest customerResponse = mapper.customerToCustomerRequest(customerData.get());
      return customerResponse;
    } else {
      log.warn("Customer can not found with id {}", customerId);
      throw new CustomerNotFoundException("Customer does not exist with id: " + customerId);
    }
  }

  @Override
  public void deleteCustomer(Integer customerId) {
    log.info("Finding customer by id {}", customerId);
    customerRepository.deleteById(customerId);
  }
}
