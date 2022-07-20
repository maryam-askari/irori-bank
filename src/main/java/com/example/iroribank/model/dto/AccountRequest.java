package com.example.iroribank.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonDeserialize(builder = AccountRequest.AccountRequestBuilder.class)
public class AccountRequest {

  @ApiModelProperty(value = "id", example = "64", required = true)
  private Integer id;

  @ApiModelProperty(value = "customerId", example = "64", required = true)
  private Integer customerId;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountRequestBuilder{}
}
