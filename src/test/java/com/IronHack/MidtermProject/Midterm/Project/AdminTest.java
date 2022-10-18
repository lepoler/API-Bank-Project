package com.IronHack.MidtermProject.Midterm.Project;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.AdminRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AdminTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CheckingsRepository checkingsRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HoldersRepository holdersRepository;

    @Autowired
    CreditCardRepository creditCardRepository;


    private Admin admin1;

    private Holders holder, holder2;
    private Account accountChecking, accountSaving, accountCreditCard;




    @BeforeEach
    void setUp(){
        admin1 = adminRepository.save(new Admin("Manolo"));
        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));
        accountChecking = checkingsRepository.save(new Checking(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));
        accountSaving = savingsRepository.save(new Savings(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));
        accountCreditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    @DisplayName("Create savings Account")
    void createSavingsAccount(){
        Savings savings = savingsRepository.save(new Savings(new Money(new BigDecimal(400)),holder, holder2,
                LocalDate.of(1991, 11, 11)));
        Optional<Savings> savingsOptional = savingsRepository.findById(savings.getId());
        Assertions.assertTrue(savingsOptional.isPresent());

    }

    @Test
    @DisplayName("Find Savings Account by ID")
    void findBySavingsAccountId_works(){
        Account account = accountRepository.save(new Savings(new Money(new BigDecimal(500)),holder, holder2,
                LocalDate.of(2000, 02, 02)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        Assertions.assertTrue(accountOptional.isPresent());
    }

    @Test
    @DisplayName("Find Checking Account by ID")
    void findByCheckingAccountId_works(){
        Account account = accountRepository.save(new Checking(new Money(new BigDecimal(500)),holder, holder2,
                LocalDate.of(2000, 02, 02)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        Assertions.assertTrue(accountOptional.isPresent());
    }

    @Test
    @DisplayName("Find CreditCard Account by ID")
    void findByCreditCardAccountId_works(){
        Account account = accountRepository.save(new CreditCard(new Money(new BigDecimal(300)),holder, holder2,
                LocalDate.of(1993, 03, 13)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        Assertions.assertTrue(accountOptional.isPresent());
    }



}
