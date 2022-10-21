package com.IronHack.MidtermProject.Midterm.Project.services.servicesUser;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.ThirdPartyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.ThirdParty;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.ThirdPartyRepository;
import com.IronHack.MidtermProject.Midterm.Project.services.interfacesUser.ThirdPartyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ThirdPartyService implements ThirdPartyInterface {

    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CheckingsRepository checkingsRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;



    public Account makeTransferToAccount(ThirdPartyDTO thirdPartyDTO, String hashKey) {

        ThirdParty thirdParty = thirdPartyRepository.findByHashKey(hashKey).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The hashKey don't correspont to any Third Party"));

        Account account1 = accountRepository.findById(thirdPartyDTO.getAccountId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id don't exist"));
        if(!account1.getSecretKey().equals(thirdPartyDTO.getAccountSecretKey())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doesn't match");
        }
        Money amount1 = new Money(thirdPartyDTO.getAmount());

       account1.getBalance().increaseAmount(amount1);
        return accountRepository.save(account1);
    }

    public Account receiveTransferToAccount(ThirdPartyDTO thirdPartyDTO, String hashKey) {

        ThirdParty thirdParty = thirdPartyRepository.findByHashKey(hashKey).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The hashKey don't correspont to any Third Party"));

        Account account1 = accountRepository.findById(thirdPartyDTO.getAccountId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id don't exist"));
        if(!account1.getSecretKey().equals(thirdPartyDTO.getAccountSecretKey())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doesn't match");
        }
        Money amount1 = new Money(thirdPartyDTO.getAmount());
        if(account1.getBalance().getAmount().subtract(amount1.getAmount()).compareTo(new BigDecimal(0)) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The are no sufficient founds to do de transfer");
        }
        return accountRepository.save(account1);
    }
}
