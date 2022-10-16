package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.AdminControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AdminController implements AdminControllerInterface {


    @Autowired
    AdminInterface adminService;



    //------ ADMIN CREATE SAVING ACCOUNT---------
    @PostMapping("/admin-createSavingsAccount/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@PathVariable Admin id, @RequestBody Savings savings) {
        return adminService.createSavingsAccount(id, savings);
    }

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    @PostMapping("/admin-createCheckingAccount/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createCheckingAccount(@PathVariable Admin id, @RequestBody Checking checking) {
        return adminService.createCheckingAccount(id, checking);
    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    @PostMapping("/admin-createCreditCardAccount/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@PathVariable Admin id, @RequestBody CreditCard creditCard) {
        return adminService.createCreditCardAccount(id, creditCard);
    }

    //------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    @PatchMapping("/adminModifyBalanceSavingsAccount/{id}")
    public Savings modifyBalanceSavingsAccount(@PathVariable Long id,@RequestBody Money balance) {
        return null;
        // return adminService.modifyBalanceSavingsAccount(id, new Money(balance));
    }
    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    @PatchMapping("/adminModifyBalanceCheckingAccount/{id}")
    public Checking modifyBalanceCheckingAccount(@PathVariable Long id, @RequestBody Money balance) {
        return null;
        //return adminService.modifyBalanceCheckingAccount(id, new Money(balance));
    }

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    @PatchMapping("/adminModifyBalanceCreditCardAccount/{id}")
    public CreditCard modifyBalanceCreditCardAccount(Long id, Money balance) {
        return null;
        //return  adminService.modifyBalanceCreditCardAccount(id, new Money(balance));
    }

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    @GetMapping("/adminAccessBalanceSavingsAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingAccountByBalance(@PathVariable Long id, Money balance) {
        return adminService.getSavingAccountByBalance(id, balance);
    }

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    @GetMapping("/adminAccessBalanceCheckingAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountByBalance(@PathVariable Long id, Money balance) {
        return adminService.getCheckingAccountByBalance(id, balance);
    }

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    @GetMapping("/adminAccessBalanceCreditCardAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountByBalance(@PathVariable Long id, Money balance) {
        return adminService.getCreditCardAccountByBalance(id, balance);
    }








}
