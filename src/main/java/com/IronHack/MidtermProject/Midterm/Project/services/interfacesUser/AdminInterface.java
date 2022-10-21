package com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccountDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;

public interface AdminInterface {


    //------ ADMIN CREATE SAVING ACCOUNT---------
    Savings createSavingsAccount (AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    Account createCheckingAccount (AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    CreditCard createCreditCardAccount (AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN MODIFY BALANCE ACCOUNTS GENERIC---------
    Account modifyBalanceAccounts (Long accountId, Money balance);

    /*//------ ADMIN MODIFY BALANCE SAVING ACCOUNT---------
    Savings modifyBalanceSavingsAccount (Long accountId, Money balance);

    //------ ADMIN MODIFY BALANCE CHECKING ACCOUNT---------
    Checking modifyBalanceCheckingAccount (Long accountId, Money balance);

    //------ ADMIN MODIFY BALANCE CREDIT CARD ACCOUNT---------
    CreditCard modifyBalanceCreditCardAccount (Long accountId, Money balance);
    */

    //------ ADMIN ACCESS BALANCE ACCOUNT---------
    Money getSavingAccountByBalance(Long accountId);

    /*//------ ADMIN ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long accountId);

    //------ ADMIN ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long accountId);*/

    //------ ADMIN DELETE ACCOUNT---------
    void deleteAccount(Long accountId);

    //------ ADMIN CREATE THIRD PARTY ---------
    ThirdParty createThirdPartyDataBase (ThirdParty thirdParty);




}
