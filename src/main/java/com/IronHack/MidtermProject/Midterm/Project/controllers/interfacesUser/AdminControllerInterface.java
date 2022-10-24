package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccountDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;

public interface AdminControllerInterface {

    //------ ADMIN CREATE SAVING ACCOUNT---------
    Savings createSavingsAccount(AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN CREATE CHECKING ACCOUNT---------
    Account createCheckingAccount(AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    CreditCard createCreditCardAccount(AdminCreateAccountDTO adminCreateAccountDTO);

    //------ ADMIN MODIFY BALANCE ACCOUNTS---------
    Account modifyBalanceAccounts(Long accountId, Money balance);


    //------ ADMIN ACCESS BALANCE ACCOUNT---------
    Money getSavingAccountByBalance(Long accountId);


    //------ ADMIN DELETE ACCOUNT---------
    void deleteAccount(Long accountId);

    //------ ADMIN CREATE THIRD PARTY ---------
    ThirdParty createThirdPartyDataBase (ThirdParty thirdParty);
}


