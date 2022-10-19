package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoney;
import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.HoldersControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.HoldersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HoldersController implements HoldersControllerInterface {

    @Autowired
    HoldersInterface holdersService;

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    @GetMapping("/holdersAccessBalanceSavingsAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingAccountByBalance(@PathVariable Long holderId, Money balance) {
        return holdersService.getSavingAccountByBalance(holderId, balance);
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCheckingAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountByBalance(Long holderId, Money balance) {
        return holdersService.getCheckingAccountByBalance(holderId, balance);
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCreditCardAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountByBalance(Long holderId, Money balance) {
        return holdersService.getCreditCardAccountByBalance(holderId, balance);
    }

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    @PatchMapping("/holdersMakeTransfer/")
    public Account makeTransferToAccount(@RequestBody HolderTransferMoney holderTransferMoney) {
        return holdersService.makeTransferToAccount(holderTransferMoney);
    }

}
