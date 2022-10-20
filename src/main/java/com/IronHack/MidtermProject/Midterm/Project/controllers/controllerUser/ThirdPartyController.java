package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.ThirdPartyDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.ThirdPartyControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.ThirdPartyRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.ThirdPartyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyInterface thirdPartyService;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @PatchMapping("/thirdPartyMakeTransfer/")
    public Account makeTransferToAccount(@RequestBody ThirdPartyDTO thirdPartyDTO, @RequestHeader String hashKey) {
            return thirdPartyService.makeTransferToAccount(thirdPartyDTO, hashKey);

    }

    @PatchMapping("/thirdPartyReceiveTransfer/")
    public Account receiveTransferToAccount(@RequestBody ThirdPartyDTO thirdPartyDTO, @RequestHeader String hashKey) {
        return thirdPartyService.receiveTransferToAccount(thirdPartyDTO, hashKey);

    }


}
