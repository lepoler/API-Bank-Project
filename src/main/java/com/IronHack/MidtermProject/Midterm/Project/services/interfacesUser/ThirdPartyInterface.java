package com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.ThirdPartyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;

public interface ThirdPartyInterface {

    //------ THIRD PARTY MAKE TRANSFER TO ACCOUNT---------
    Account makeTransferToAccount(ThirdPartyDTO thirdPartyDTO, String hashKey);

    //------ THIRD PARTY RECEIVE TRANSFER TO ACCOUNT---------
    Account receiveTransferToAccount(ThirdPartyDTO thirdPartyDTO, String hashKey);


}
