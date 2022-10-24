package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderAccessBalanceDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;

public interface HoldersControllerInterface {

    //------ HOLDERS ACCESS BALANCE ACCOUNT---------
    Money accessBalanceAccount(Long holderId, Long holderAccountId);

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    Account makeTransferToAccount (HolderTransferMoneyDTO holderTransferMoneyDTO);
}
