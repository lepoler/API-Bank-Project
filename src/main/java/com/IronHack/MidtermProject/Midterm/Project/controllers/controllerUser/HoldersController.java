package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
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
    @GetMapping("/holdersAccessBalanceSavingsAccount/{holderId}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getSavingAccountByBalance(@PathVariable Long holderId) {
        return holdersService.getSavingAccountByBalance(holderId);
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCheckingAccount/{holderId}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getCheckingAccountByBalance(@PathVariable Long holderId) {
        return holdersService.getCheckingAccountByBalance(holderId);
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    @GetMapping("/holdersAccessBalanceCreditCardAccount/{holderId}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getCreditCardAccountByBalance(@PathVariable Long holderId) {
        return holdersService.getCreditCardAccountByBalance(holderId);
    }

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    @PatchMapping("/holdersMakeTransfer/")
    public Account makeTransferToAccount(@RequestBody HolderTransferMoneyDTO holderTransferMoneyDTO) {
        return holdersService.makeTransferToAccount(holderTransferMoneyDTO);
    }

}
