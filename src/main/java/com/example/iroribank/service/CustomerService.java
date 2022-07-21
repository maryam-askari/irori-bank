package com.example.iroribank.service;

import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;

import java.util.List;

public interface CustomerService {

  List<Customer> allCustomers();

  void updateCustomer(Integer customerId, CustomerRequest customerRequest);

  void addCustomer(CustomerRequest customerRequest);

  CustomerRequest findCustomer(Integer customerId);

  void deleteCustomer(Integer customerId);
}
