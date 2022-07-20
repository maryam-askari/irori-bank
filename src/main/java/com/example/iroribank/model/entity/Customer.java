package com.example.iroribank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String userName;

  @Column(name = "FIRSTNAME")
  private String firstName;

  @Column(name = "LASTNAME")
  private String lastName;

  @Column(name = "EMAIL")
  private String email;

  public Customer(Integer id, String userName, String firstName, String lastName, String email, String phone){
    this.id = id;
    this.firstName=firstName;
    this.userName=userName;
    this.lastName=lastName;
    this.email=email;
    this.phone=phone;
  }

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // har balaie sare pedar omad sare bacheham biad
  private Set<Account> accountSet = new HashSet<>();

  @Column(name = "PHONENUMBER")
  private String phone;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class CustomerBuilder{}
}
