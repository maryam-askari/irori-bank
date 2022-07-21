package com.example.iroribank.mapper;

import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;

public class CustomerMapperImpl implements CustomerMapper{
  @Override
  public Customer customerRequestToCustomer(CustomerRequest customerRequest) {
    if(customerRequest == null){
      return null;
    } else {
      Customer customer= new Customer();
      customer.setUserName(customerRequest.getUserName());
      customer.setFirstName(customerRequest.getFirstName());
      customer.setLastName(customerRequest.getLastName());
      customer.setEmail(customerRequest.getEmail());
      customer.setPhone(customer.getPhone());
      return customer;
    }
  }

  @Override
  public CustomerRequest customerToCustomerRequest(Customer customer) {
    if (customer == null){
      return null;
    }else {
      CustomerRequest customerRequest = new CustomerRequest();
      customerRequest.setUserName(customer.getUserName());
      customerRequest.setFirstName(customer.getFirstName());
      customerRequest.setLastName(customer.getLastName());
      customerRequest.setEmail(customer.getEmail());
      customerRequest.setPhone(customer.getPhone());
      return customerRequest;
    }
  }
}
