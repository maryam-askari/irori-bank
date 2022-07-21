package com.example.iroribank.mapper;

import com.example.iroribank.model.dto.CustomerRequest;
import com.example.iroribank.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer customerRequestToCustomer(CustomerRequest customerRequest);
  CustomerRequest customerToCustomerRequest(Customer customer);
}
