package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AdminService implements AdminInterface {

    @Autowired
    SavingsRepository savingsRepository;
    CheckingsRepository checkingsRepository;

    CreditCardRepository creditCardRepository;

    //------ ADMIN CREATE SAVING ACCOUNT---------
    public Savings createSavingsAccount(Admin id, Savings savings) {
        if(savingsRepository.findByBalanceAndPrimaryOwnerAndSecondaryOwnerAndCreationDate(
                savings.getBalance(), savings.getPrimaryOwner(), savings.getSecondaryOwner(), savings.getCreationDate()).isPresent()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return savingsRepository.save(savings);
    }

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    public Checking createCheckingAccount(Admin id, Checking checking) {
        if(checkingsRepository.findByBalanceAndPrimaryOwnerAndSecondaryOwnerAndCreationDate(
                checking.getBalance(), checking.getPrimaryOwner(), checking.getSecondaryOwner(), checking.getCreationDate()).isPresent()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return checkingsRepository.save(checking);
    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    public CreditCard createCreditCardAccount(Admin id, CreditCard creditCard) {
        if (creditCardRepository.findByBalanceAndPrimaryOwnerAndSecondaryOwnerAndCreationDate(
                creditCard.getBalance(), creditCard.getPrimaryOwner(), creditCard.getSecondaryOwner(), creditCard.getCreationDate()).isPresent()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return creditCardRepository.save(creditCard);
    }

    //------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    public Savings modifyBalanceSavingsAccount(Long id, Money balance) {
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
    }

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    public Savings getSavingAccountByBalance(Long id, Money balance) {
        return savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Savings Account with the given id does not exist"));
    }

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    public Checking getCheckingAccountByBalance(Long id, Money balance) {
        return checkingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard getCreditCardAccountByBalance(Long id, Money balance) {
        return creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Credit Card Account with the given id does not exist"));
    }








}
