package com.IronHack.MidtermProject.Midterm.Project.controllers;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.AdminCreateAccount;
import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoney;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.AdminRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CheckingsRepository checkingsRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HoldersRepository holdersRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private Admin admin1;

    private Holders holder, holder2;
    private Account accountChecking, accountSaving, accountCreditCard;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());




    @BeforeEach
    void setUp(){
        admin1 = adminRepository.save(new Admin("Manolo"));
        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));
        accountChecking = checkingsRepository.save(new Checking(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));
        accountSaving = savingsRepository.save(new Savings(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));
        accountCreditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(250)),holder, holder2,
                LocalDate.of(1998, 01, 01)));


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown(){

    }

   //ME FALTA TEST DE CREAR ACCOUNT x3

    @Test
    @DisplayName("Modify Balance Account Savings")
    void patch_Account_ModifyBalance_isOk() throws Exception {

        String balance = objectMapper.writeValueAsString(new Money(new BigDecimal(400)));
        System.out.println(balance);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/1").content(balance).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        assertTrue(accountRepository.findById(1L).get().getBalance().getAmount().compareTo(new BigDecimal(400)) == 0);
    }


    @Test
    @DisplayName("Modify Balance Account Savings")
    void patch_Account_ModifyBalance_ErrorIdAccount_isOk() throws Exception {

        String idAccount = objectMapper.writeValueAsString(new Money(new BigDecimal(400)));
        System.out.println(idAccount);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/5").content(idAccount).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }


    @Test
    @DisplayName("Access Balance Account")
    void get_Account_AccessBalance_isOk() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/adminAccessBalanceAccount/" + accountChecking.getId()))
                .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountChecking.getBalance().getAmount().toString()));
    }

    @Test
    @DisplayName("Access Balance Account")
    void get_Account_AccessBalance_ErrorId_isOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/adminAccessBalanceAccount/"))
                .andExpect(status().isNotFound()).andReturn();

    }

    @Test
    @DisplayName("Delete Credit Card Account")
    void delete_CreditCard_Account_isOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/adminDeleteAccount/1")).andExpect(status().isOk()).andReturn();

        assertTrue(savingsRepository.findById(1L).isEmpty());
    }

}
