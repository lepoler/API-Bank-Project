package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;



import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccountDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.ThirdPartyRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //------ ADMIN CREATE SAVING ACCOUNT---------
    public Savings createSavingsAccount(AdminCreateAccountDTO adminCreateAccountDTO) {
        if(accountRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccountDTO.getBalance()), adminCreateAccountDTO.getPrimaryOwner(),
                adminCreateAccountDTO.getSecondaryOwner(), adminCreateAccountDTO.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccountDTO.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccountDTO.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccountDTO.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        return savingsRepository.save(new Savings(new Money(adminCreateAccountDTO.getBalance()), new Money(new BigDecimal(40)), primaryOwner,
                secondaryOwner, "1234", adminCreateAccountDTO.getCreationDate(), new Money(adminCreateAccountDTO.getMinimBalance()),
                new Money(adminCreateAccountDTO.getInterestRate())));


    }



    //------ ADMIN CREATE CHECKING ACCOUNT---------
    public Account createCheckingAccount(AdminCreateAccountDTO adminCreateAccountDTO) {
        if(accountRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccountDTO.getBalance()), adminCreateAccountDTO.getPrimaryOwner(),
                adminCreateAccountDTO.getSecondaryOwner(), adminCreateAccountDTO.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccountDTO.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccountDTO.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccountDTO.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        if(primaryOwner.getDateOfBirth().isBefore(LocalDate.of(1998, 01, 01))){
            return checkingsRepository.save(new Checking(new Money(adminCreateAccountDTO.getBalance()),new Money(new BigDecimal(40)), primaryOwner,
                    secondaryOwner, "1234"));
        } else {
            return studentCheckingRepository.save(new StudentChecking(new Money(adminCreateAccountDTO.getBalance()), new Money(), primaryOwner,
                    secondaryOwner, adminCreateAccountDTO.getSecretKey(), adminCreateAccountDTO.getCreationDate()));
        }


    }

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    public CreditCard createCreditCardAccount(AdminCreateAccountDTO adminCreateAccountDTO) {
        if (accountRepository.findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(
                new Money(adminCreateAccountDTO.getBalance()), adminCreateAccountDTO.getPrimaryOwner(),
                adminCreateAccountDTO.getSecondaryOwner(), adminCreateAccountDTO.getCreationDate()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Holders primaryOwner = holdersRepository.findById(adminCreateAccountDTO.getPrimaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Holders secondaryOwner = null;
        if(adminCreateAccountDTO.getSecondaryOwner() != null) {
            secondaryOwner = holdersRepository.findById(adminCreateAccountDTO.getSecondaryOwner()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
            return creditCardRepository.save(new CreditCard(new Money(adminCreateAccountDTO.getBalance()), new Money(new BigDecimal(40)), primaryOwner,
                    secondaryOwner, "1234", adminCreateAccountDTO.getCreationDate(), new Money(adminCreateAccountDTO.getMinimBalance()),
                    new Money(adminCreateAccountDTO.getInterestRate())));
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

    //------ ADMIN ACCESS BALANCE ACCOUNT---------
    public Money getSavingAccountByBalance(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Account with the given id does not exist")).getBalance();
    }


    //------ ADMIN DELETE ACCOUNT---------
    public void deleteAccount(Long accountId) {
        Account deleteAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this account doesn't exist"));
        accountRepository.deleteById(accountId);

    }

    //------ ADMIN CREATE THIRD PARTY ---------

    public ThirdParty createThirdPartyDataBase(ThirdParty thirdParty) {
        if(thirdPartyRepository.findByHashKey(thirdParty.getHashKey()).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Third Party already exist in actual Data Base");
        }
        thirdParty.setPassword(passwordEncoder.encode(thirdParty.getPassword()));
        return thirdPartyRepository.save(thirdParty);
    }


}
