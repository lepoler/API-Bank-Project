package com.IronHack.MidtermProject.Midterm.Project.controllers;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HoldersControllerTest {

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


    @BeforeEach
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
    @DisplayName("Find Savings Accounts By Holders")
    void get_Savings_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceSavingsAccount/1")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Pol"));
    }

    @Test
    @DisplayName("Find Savings Accounts By Holders")
    void get_Savings_Error_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceSavingsAccount/2")).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Find  Checking Accounts By Holders")
    void get_Checking_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceCheckingAccount/2")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Pol"));
    }

    @Test
    @DisplayName("Find  Checking Accounts By Holders")
    void get_Checking_Error_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceCheckingAccount/3")).andExpect(status().isNotFound()).andReturn();

    }

    @Test
    @DisplayName("Find CreditCard Accounts By Holders")
    void get_CreditCard_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceCreditCardAccount/3")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Pol"));
    }

    @Test
    @DisplayName("Find CreditCard Accounts By Holders")
    void get_CreditCard_Error_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceCreditCardAccount/4")).andExpect(status().isNotFound()).andReturn();

    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_worksOk() throws Exception {
        HolderTransferMoneyDTO transfer = new HolderTransferMoneyDTO(holder.getId(), 1L, holder2.getId(), 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(savingsRepository.findById(1L).get().getBalance().getAmount().compareTo(new BigDecimal(400)) == 0);
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_ErrorIdHolder_worksOk() throws Exception {
        HolderTransferMoneyDTO idHolder = new HolderTransferMoneyDTO(10L, 1L, holder2.getId(), 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idHolder);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_ErrorIdAccountHolder_worksOk() throws Exception {
        HolderTransferMoneyDTO idAccountHolder = new HolderTransferMoneyDTO(holder.getId(), 20L, holder2.getId(), 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idAccountHolder);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_ErrorIdReceiver_worksOk() throws Exception {
        HolderTransferMoneyDTO idReceiver = new HolderTransferMoneyDTO(holder.getId(), 1L, 10L, 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idReceiver);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_ErrorIdAccountReceiver_worksOk() throws Exception {
        HolderTransferMoneyDTO idAccountReceiver = new HolderTransferMoneyDTO(holder.getId(), 1L, holder2.getId(), 20L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idAccountReceiver);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }



}
