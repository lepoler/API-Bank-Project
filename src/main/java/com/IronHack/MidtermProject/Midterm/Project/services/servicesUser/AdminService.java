package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;



import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccount;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AdminService implements AdminInterface {

    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CheckingsRepository checkingsRepository;
    @Autowired
    HoldersRepository holdersRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    //------ ADMIN CREATE SAVING ACCOUNT---------
    public Savings createSavingsAccount(AdminCreateAccount adminCreateAccount) {
        if(savingsRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccount.getBalance()), adminCreateAccount.getPrimaryOwner(),
                adminCreateAccount.getSecondaryOwner(), adminCreateAccount.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccount.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccount.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccount.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        return savingsRepository.save(new Savings(new Money(adminCreateAccount.getBalance()), primaryOwner,
                secondaryOwner, adminCreateAccount.getCreationDate(), new Money(adminCreateAccount.getMinimBalance()),
                new Money(adminCreateAccount.getInterestRate())));


    }

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    public Account createCheckingAccount(AdminCreateAccount adminCreateAccount) {
        if(accountRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccount.getBalance()), adminCreateAccount.getPrimaryOwner(),
                adminCreateAccount.getSecondaryOwner(), adminCreateAccount.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccount.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccount.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccount.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        if(primaryOwner.getDateOfBirth().isBefore(LocalDate.of(1998, 01, 01))){
            return checkingsRepository.save(new Checking(new Money(adminCreateAccount.getBalance()), primaryOwner,
                    secondaryOwner, adminCreateAccount.getCreationDate()));
        } else {
            return studentCheckingRepository.save(new StudentChecking(new Money(adminCreateAccount.getBalance()), primaryOwner,
                    secondaryOwner, adminCreateAccount.getCreationDate()));
        }


    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    public CreditCard createCreditCardAccount(AdminCreateAccount adminCreateAccount) {
        if (creditCardRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccount.getBalance()), adminCreateAccount.getPrimaryOwner(),
                adminCreateAccount.getSecondaryOwner(), adminCreateAccount.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccount.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccount.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccount.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
            return creditCardRepository.save(new CreditCard(new Money(adminCreateAccount.getBalance()), primaryOwner,
                    secondaryOwner, adminCreateAccount.getCreationDate(), new Money(adminCreateAccount.getMinimBalance()),
                    new Money(adminCreateAccount.getInterestRate())));
    }



    //------ ADMIN MODIFY BALANCE ACCOUNTS---------

    public Account modifyBalanceAccounts(Long accountId, Money balance) {
        Account account1 = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this account doesn't exist"));
        account1.setBalance(balance);
        return accountRepository.save(account1);
    }


    //------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    /*public Savings modifyBalanceSavingsAccount(Long id, Money balance) {
        Savings savings1 = savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this account doesn't exist"));
        savings1.setBalance(balance);
        return savingsRepository.save(savings1);
    }

    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    public Checking modifyBalanceCheckingAccount(Long id, Money balance) {
        Checking checking1 = checkingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        checking1.setBalance(balance);
        return checkingsRepository.save(checking1);
    }

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard modifyBalanceCreditCardAccount(Long id, Money balance) {
        CreditCard creditCard1 = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        creditCard1.setBalance(balance);
        return creditCardRepository.save(creditCard1);
    }*/

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    public Savings getSavingAccountByBalance(Long accountId, Money balance) {
        return savingsRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Savings Account with the given id does not exist"));
    }

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    public Checking getCheckingAccountByBalance(Long accountId, Money balance) {
        return checkingsRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard getCreditCardAccountByBalance(Long accountId, Money balance) {
        return creditCardRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Credit Card Account with the given id does not exist"));
    }

    //------ ADMIN DELETE ACCOUNT---------
    public Account deleteAccount(Long accountId) {
        Account deleteAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this account doesn't exist"));
        accountRepository.delete(deleteAccount);
        return deleteAccount;

    }

}
