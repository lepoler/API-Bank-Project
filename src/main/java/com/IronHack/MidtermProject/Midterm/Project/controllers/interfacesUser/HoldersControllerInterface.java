package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoney;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;

public interface HoldersControllerInterface {

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    Savings getSavingAccountByBalance(Long holderId, Money balance);

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long holderId, Money balance);

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long holderId, Money balance);

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    Account makeTransferToAccount (Long holderAccountId, HolderTransferMoney holderTransferMoney);
}
