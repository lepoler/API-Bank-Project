package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderAccessBalanceDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.HoldersControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.HoldersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HoldersController implements HoldersControllerInterface {

    @Autowired
    HoldersInterface holdersService;


    @GetMapping("holdersAccessBalanceAccount/{holderId}/{holderAccountId}")
    public Money accessBalanceAccount(@PathVariable Long holderId, @PathVariable Long holderAccountId) {
        return holdersService.accessBalanceAccount(holderId, holderAccountId);
    }

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    @PatchMapping("/holdersMakeTransfer/")
    public Account makeTransferToAccount(@RequestBody HolderTransferMoneyDTO holderTransferMoneyDTO) {
        return holdersService.makeTransferToAccount(holderTransferMoneyDTO);
    }

}
