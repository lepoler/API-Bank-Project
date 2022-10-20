package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.HoldersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class HoldersService implements HoldersInterface {

    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CheckingsRepository checkingsRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    HoldersRepository holdersRepository;

    //------ HOLDERS ACCESS BALANCE SAVINGS ACCOUNT---------
    public Savings getSavingAccountByBalance(Long holderId) {
        return savingsRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Savings Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CHECKING ACCOUNT---------
    public Checking getCheckingAccountByBalance(Long holderId) {
        return checkingsRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }

    //------ HOLDERS ACCESS BALANCE CREDIT CARD ACCOUNT---------
    public CreditCard getCreditCardAccountByBalance(Long holderId) {
        return creditCardRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Credit Card Account with the given id does not exist"));
    }

    //------ HOLDERS MAKE TRANSFER TO ACCOUNT---------
    public Account makeTransferToAccount(HolderTransferMoneyDTO holderTransferMoneyDTO) {
        Holders makerId = holdersRepository.findById(holderTransferMoneyDTO.getHolderId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this Holder doesn't exist"));
        Optional <Account> account1 = makerId.getAccountListPrimary().stream().filter((account)-> account.getId() == holderTransferMoneyDTO.getHolderAccountId()).findFirst();
        if(account1.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of the account Holder doesn't exist");
        }

        Holders receiverId = holdersRepository.findById(holderTransferMoneyDTO.getHolderReceivesId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of this Holder receiver doesn't exist"));
        Optional <Account> account2 = receiverId.getAccountListPrimary().stream().filter((account)-> account.getId() == holderTransferMoneyDTO.getHolderAccountReceivesId()).findFirst();
        if(account2.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of the account Holder doesn't exist");
        }


        if (account1.get().getBalance().getAmount().compareTo(holderTransferMoneyDTO.getTransferAmount()) == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The amount you wan to transfer is higher than you balance");
        } else if (account1.get().getBalance().getAmount().compareTo(holderTransferMoneyDTO.getTransferAmount()) == 0 ||
                account1.get().getBalance().getAmount().compareTo(holderTransferMoneyDTO.getTransferAmount()) == 1) {
            account1.get().getBalance().decreaseAmount(holderTransferMoneyDTO.getTransferAmount());
            account2.get().getBalance().increaseAmount(holderTransferMoneyDTO.getTransferAmount());
            accountRepository.save(account2.get());
            return accountRepository.save(account1.get());
        }
        return account1.get();
    }

}


