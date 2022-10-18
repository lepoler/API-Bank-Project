package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccount;
import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.AdminControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AdminController implements AdminControllerInterface {


    @Autowired
    AdminInterface adminService;



    //------ ADMIN CREATE SAVING ACCOUNT---------
    @PostMapping("/admin-createSavingsAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@RequestBody @Valid AdminCreateAccount adminCreateAccount) {
        return adminService.createSavingsAccount(adminCreateAccount);
    }


    //------ ADMIN CREATE CHECKING ACCOUNT---------
    @PostMapping("/admin-createCheckingAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid AdminCreateAccount adminCreateAccount) {
       return adminService.createCheckingAccount(adminCreateAccount);

    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    @PostMapping("/admin-createCreditCardAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid AdminCreateAccount adminCreateAccount) {
        return adminService.createCreditCardAccount(adminCreateAccount);
    }

    //------ ADMIN MODIFY BALANCE ACCOUNT---------
    @PatchMapping("/admin/modifyBalanceAccounts/{id}")
    public Account modifyBalanceAccounts(@PathVariable Long accountId,@RequestBody Money balance) {
        return adminService.modifyBalanceAccounts(accountId, balance);
    }


    /*//------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    @PatchMapping("/admin/ModifyBalanceSavingsAccount/{id}")
    public Savings modifyBalanceSavingsAccount(@PathVariable Long accountId,@RequestBody Money balance) {
        return adminService.modifyBalanceSavingsAccount(accountId, balance);
    }
    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    @PatchMapping("/adminModifyBalanceCheckingAccount/{id}")
    public Checking modifyBalanceCheckingAccount(@PathVariable Long accountId, @RequestBody Money balance) {
        return adminService.modifyBalanceCheckingAccount(accountId, balance);
    }

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    @PatchMapping("/adminModifyBalanceCreditCardAccount/{id}")
    public CreditCard modifyBalanceCreditCardAccount(Long accountId, Money balance) {
        return  adminService.modifyBalanceCreditCardAccount(accountId, balance);
    }*/

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    @GetMapping("/adminAccessBalanceSavingsAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingAccountByBalance(@PathVariable Long accountId, Money balance) {
        return adminService.getSavingAccountByBalance(accountId, balance);
    }

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    @GetMapping("/adminAccessBalanceCheckingAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountByBalance(@PathVariable Long accountId, Money balance) {
        return adminService.getCheckingAccountByBalance(accountId, balance);
    }

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    @GetMapping("/adminAccessBalanceCreditCardAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountByBalance(@PathVariable Long accountId, Money balance) {
        return adminService.getCreditCardAccountByBalance(accountId, balance);
    }

    //------ ADMIN DELETE ACCOUNT---------
    @PostMapping("/adminDeleteAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account deleteAccount(@PathVariable Long accountId) {
        return adminService.deleteAccount(accountId);
    }


}
