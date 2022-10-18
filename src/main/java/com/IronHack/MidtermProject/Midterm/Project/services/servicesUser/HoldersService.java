package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoney;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.HoldersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HoldersService implements HoldersInterface {

    @Autowired
    SavingsRepository savingsRepository;
    CheckingsRepository checkingsRepository;

    AccountRepository accountRepository;
    CreditCardRepository creditCardRepository;

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    public Savings getSavingAccountByBalance(Long holderId, Money balance) {
        return savingsRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Savings Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    public Checking getCheckingAccountByBalance(Long holderId, Money balance) {
        return checkingsRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard getCreditCardAccountByBalance(Long holderId, Money balance) {
        return creditCardRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Credit Card Account with the given id does not exist"));
    }

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    public Account makeTransferToAccount(Long accountHolderId, HolderTransferMoney holderTransferMoney) {
        Account makerAccount = accountRepository.findById(accountHolderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this account doesn't exist"));
        if (makerAccount.getBalance().getAmount().compareTo(holderTransferMoney.getTransferAmount()) == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The amount you wan to transfer is higher than you balance");
        } else if (makerAccount.getBalance().getAmount().compareTo(holderTransferMoney.getTransferAmount()) == 0 ||
                makerAccount.getBalance().getAmount().compareTo(holderTransferMoney.getTransferAmount()) == 1) {
            return accountRepository.save(makerAccount);
        }
        return makerAccount;
    }

}


