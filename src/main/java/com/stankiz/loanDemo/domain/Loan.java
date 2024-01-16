package com.stankiz.loanDemo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email(message = "enter valid email")
    @NotEmpty(message = "email can not be empty")
    private String email;
    @Positive
    private int loanValue;
    @PositiveOrZero @Max(100)
    private int yearlyInterestRate;
    @Positive @Max(30)
    private int loanTerm;

    private double emi;


}

