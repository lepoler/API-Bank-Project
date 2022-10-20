package com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;

public interface HoldersInterface {

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    Savings getSavingAccountByBalance(Long holderId);

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    Checking getCheckingAccountByBalance(Long holderId);

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    CreditCard getCreditCardAccountByBalance(Long holderId);

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    Account makeTransferToAccount (HolderTransferMoneyDTO holderTransferMoneyDTO);
}
