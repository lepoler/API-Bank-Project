package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;

public interface HoldersControllerInterface {

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    Savings getSavingAccountByBalance(Long id, Money balance);

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long id, Money balance);

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long id, Money balance);
}
