package com.example.iroribank.model.dto;


import com.example.iroribank.model.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonDeserialize(builder = AccountRequest.AccountRequestBuilder.class)
public class AccountRequest {

  @ApiModelProperty(value = "id", example = "64", required = true)
  private Integer id;

  @ApiModelProperty(value = "customerId", example = "64", required = true)
  private Integer customerId;

  @ApiModelProperty(value = "balance", example = "10000.0")
  private Double balance;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountRequestBuilder{}
}
