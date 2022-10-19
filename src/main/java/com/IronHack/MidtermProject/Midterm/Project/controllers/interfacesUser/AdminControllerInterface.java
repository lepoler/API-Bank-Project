package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccount;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;

public interface AdminControllerInterface {

    //------ ADMIN CREATE SAVING ACCOUNT---------
    Savings createSavingsAccount(AdminCreateAccount adminCreateAccount);

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    Account createCheckingAccount(AdminCreateAccount adminCreateAccount);

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    CreditCard createCreditCardAccount(AdminCreateAccount adminCreateAccount);

    //------ ADMIN MODIFY BALANCE ACCOUNTS---------
    Account modifyBalanceAccounts(Long accountId, Money balance);

    /*//------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    Savings modifyBalanceSavingsAccount(Long accountId, Money balance);

    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    Checking modifyBalanceCheckingAccount (Long accountId, Money balance);

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    CreditCard modifyBalanceCreditCardAccount (Long accountId, Money balance);
    */

    //------ ADMIN ACCESS BALANCE SAVINGS ACCOUNT---------
    Savings getSavingAccountByBalance(Long accountId);

    //------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long accountId);

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long accountId);

    //------ ADMIN DELETE ACCOUNT---------
    void deleteAccount(Long accountId);
}


