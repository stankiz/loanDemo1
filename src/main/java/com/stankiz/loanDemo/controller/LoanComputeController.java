package com.stankiz.loanDemo.controller;


import com.stankiz.loanDemo.domain.Loan;
import com.stankiz.loanDemo.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loancompute")
public class LoanComputeController extends ExceptionController{
    @Autowired
    public LoanService service;


    @PostMapping
    public String compute(@Valid  Loan loan, Errors error) {

        if(!error.hasErrors()) {
            service.compute(loan);
            return "redirect:loancompute";
        }
        return "loancompute";
    }

/*
Here is PostMapping code if I only design backend with LoanRecord and return back double emi as response
@PostMapping("/compute")
    public ResponseEntity<double> compute(@Valid @RequestBody LoanRecord loanRecord) {
        //double response = service.compute(loanRecord);
       // return ResponseEntity.ok(response);

        double response = service.computeEmi(loanRecord);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(response);

*/


    @ModelAttribute("loans")
    public Iterable<Loan> getLoans(){
        return service.findAll();
    }

    @ModelAttribute
    public Loan getLoan(){
        return new Loan();
    }

    @GetMapping
    public String showPeoplePage(Model model){ // model is the data
        return "loancompute";
    }


}