package com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;

import java.math.BigDecimal;

public interface AdminInterface {


    //------ ADMIN CREATE SAVING ACCOUNT---------
    Savings createSavingsAccount (Admin id, Savings savings);

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    Checking createCheckingAccount (Admin id, Checking checking);

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    CreditCard createCreditCardAccount (Admin id, CreditCard creditCard);

    //------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    Savings modifyBalanceSavingsAccount (Long id, Money balance);

    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    Checking modifyBalanceCheckingAccount (Long id, Money balance);

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    CreditCard modifyBalanceCreditCardAccount (Long id, Money balance);

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    Savings getSavingAccountByBalance(Long id, Money balance);

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long id, Money balance);

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long id, Money balance);
}
