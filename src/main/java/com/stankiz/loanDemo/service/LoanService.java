package com.stankiz.loanDemo.service;


import com.stankiz.loanDemo.domain.Loan;
import com.stankiz.loanDemo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final int MONTHS_IN_YEAR = 12;

    @Autowired
    public LoanRepository repository;

    public Loan compute(Loan record) {

        System.out.println(record);
        double emi = 0.0;
        //calculate
        double monthlyInterestRate = record.getYearlyInterestRate() / MONTHS_IN_YEAR;
        double rateResult = Math.pow(monthlyInterestRate + 1, record.getLoanTerm());
        double divider = record.getLoanValue() * monthlyInterestRate * rateResult;
        double divide = rateResult - 1;
        if(divide != 0.0)
            emi = divider / divide;

        record.setEmi(emi);
        System.out.println("loanResult = " + record);

        return repository.save(record);
    }




    public Loan getEmiById(Long id){
        return repository.findById(id).get();
    }
    public List<Loan> findAll(){
        return (List<Loan>) repository.findAll();
    }

}


