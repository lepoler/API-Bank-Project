package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
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

    CreditCardRepository creditCardRepository;

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    public Savings getSavingAccountByBalance(Long id, Money balance) {
        return savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Savings Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    public Checking getCheckingAccountByBalance(Long id, Money balance) {
        return checkingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard getCreditCardAccountByBalance(Long id, Money balance) {
        return creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Credit Card Account with the given id does not exist"));
    }
}
