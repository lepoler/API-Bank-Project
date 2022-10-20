package com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.ThirdPartyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ThirdPartyControllerInterface {

    //------ THIRD PARTY MAKE TRANSFER TO ACCOUNT---------
    Account makeTransferToAccount (ThirdPartyDTO thirdPartyDTO, String hashKey);

    Account receiveTransferToAccount(ThirdPartyDTO thirdPartyDTO, String hashKey);

}
