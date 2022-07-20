package com.example.iroribank.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@JsonDeserialize(builder = Withdrawal.WithdrawalBuilder.class)
public class Withdrawal {

  @ApiModelProperty(value = "amount", example = "1000.0")
  private Double amount;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class WithdrawalBuilder{}
}
