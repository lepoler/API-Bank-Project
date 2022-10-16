package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.HoldersControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.HoldersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoldersController implements HoldersControllerInterface {

    @Autowired
    HoldersInterface holdersService;

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    @GetMapping("/holdersAccessBalanceSavingsAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingAccountByBalance(@PathVariable Long id, Money balance) {
        return holdersService.getSavingAccountByBalance(id, balance);
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCheckingAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountByBalance(Long id, Money balance) {
        return holdersService.getCheckingAccountByBalance(id, balance);
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCreditCardAccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountByBalance(Long id, Money balance) {
        return holdersService.getCreditCardAccountByBalance(id, balance);
    }

}
