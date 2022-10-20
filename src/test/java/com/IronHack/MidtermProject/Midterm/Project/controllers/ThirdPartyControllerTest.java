package com.IronHack.MidtermProject.Midterm.Project.controllers;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.ThirdPartyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.validator.constraints.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyControllerTest {

    @Autowired
    HoldersRepository holdersRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    CheckingsRepository checkingsRepository;
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    PasswordEncoder passwordEncoder;
    private MockMvc mockMvc;
    private Holders holder, holder2;
    private Account accountChecking, accountSaving, accountCreditCard;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

   /* @BeforeEach
    public void SetUp(){

        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345"), "Julian", passwordEncoder.encode("1234")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Falsa", "Springfild", "Russia", "12345"), "Pablo", passwordEncoder.encode("1234")));
        accountSaving = savingsRepository.save(new Savings(new Money(new BigDecimal(800)), new Money(), holder, holder2,
               "1234", LocalDate.of(1998, 01, 01)));
        accountChecking = checkingsRepository.save(new Checking(new Money(new BigDecimal(550)), new Money(new BigDecimal(40)), holder2, holder,
                "1234", LocalDate.of(1977, 01, 01)));
        accountCreditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(850)),
                new Money(new BigDecimal(40)), holder, holder2, "1234",
                LocalDate.of(1958, 01, 01)));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @DisplayName("Third Party Transfer Money Works")
    void patch_transferMoney_worksOk() throws Exception {
        ThirdPartyDTO transfer = new ThirdPartyDTO((new BigDecimal(500)), "1234", accountSaving.getId());
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/thirdPartyMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

    }*/
}
