package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccountDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.AdminControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController implements AdminControllerInterface {


    @Autowired
    AdminInterface adminService;



    //------ ADMIN CREATE SAVING ACCOUNT---------
    @PostMapping("/admin-createSavingsAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@RequestBody @Valid AdminCreateAccountDTO adminCreateAccountDTO) {
        return adminService.createSavingsAccount(adminCreateAccountDTO);
    }


    //------ ADMIN CREATE CHECKING ACCOUNT---------
    @PostMapping("/admin-createCheckingAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid AdminCreateAccountDTO adminCreateAccountDTO) {
       return adminService.createCheckingAccount(adminCreateAccountDTO);

    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    @PostMapping("/admin-createCreditCardAccount/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid AdminCreateAccountDTO adminCreateAccountDTO) {
        return adminService.createCreditCardAccount(adminCreateAccountDTO);
    }

    //------ ADMIN MODIFY BALANCE ACCOUNT---------
    @PatchMapping("/admin/modifyBalanceAccounts/{accountId}")
    public Account modifyBalanceAccounts(@PathVariable Long accountId,@RequestBody Money balance) {
        return adminService.modifyBalanceAccounts(accountId, balance);
    }

    //------ ADMIN ACCESS BALANCE ACCOUNT---------
    @GetMapping("/adminAccessBalanceAccount/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money getSavingAccountByBalance(@PathVariable Long accountId) {
        return adminService.getSavingAccountByBalance(accountId);
    }


    //------ ADMIN DELETE ACCOUNT---------
    @DeleteMapping("/adminDeleteAccount/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long accountId) {

         adminService.deleteAccount(accountId);
    }

    //------ ADMIN CREATE THIRD PARTY ---------
    @PostMapping("/admin-createThirdParty-DataBase/")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyDataBase(@RequestBody @Valid ThirdParty thirdParty) {
        return adminService.createThirdPartyDataBase(thirdParty);
    }



}
