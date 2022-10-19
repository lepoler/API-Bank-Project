package com.IronHack.MidtermProject.Midterm.Project;

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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    @Test
    @DisplayName("Create savings Account")
    void createSavingsAccount(){
        Savings savings = savingsRepository.save(new Savings(new Money(new BigDecimal(400)),holder, holder2,
                LocalDate.of(1991, 11, 11)));
        Optional<Savings> savingsOptional = savingsRepository.findById(savings.getId());
        assertTrue(savingsOptional.isPresent());

    }

    @Test
    @DisplayName("Find Savings Account by ID")
    void findBySavingsAccountId_works(){
        Account account = accountRepository.save(new Savings(new Money(new BigDecimal(500)),holder, holder2,
                LocalDate.of(2000, 02, 02)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        assertTrue(accountOptional.isPresent());
    }

    @Test
    @DisplayName("Find Checking Account by ID")
    void findByCheckingAccountId_works(){
        Account account = accountRepository.save(new Checking(new Money(new BigDecimal(500)),holder, holder2,
                LocalDate.of(2000, 02, 02)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        assertTrue(accountOptional.isPresent());
    }

    @Test
    @DisplayName("Find CreditCard Account by ID")
    void findByCreditCardAccountId_works(){
        Account account = accountRepository.save(new CreditCard(new Money(new BigDecimal(300)),holder, holder2,
                LocalDate.of(1993, 03, 13)));
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        assertTrue(accountOptional.isPresent());
    }

    @Test
    @DisplayName("Modify Balance Account Savings")
    void patch_Account_Savings_ModifyBalance_isOk() throws Exception {
        Savings savings = savingsRepository.save(new Savings(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18)));

        String balance = objectMapper.writeValueAsString(savings);
        System.out.println(balance);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/1").content(balance).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(savingsRepository.findById(savings.getId()).isPresent());
    }

    @Test
    @DisplayName("Modify Balance Account Checking")
    void patch_Account_Checking_ModifyBalance_isOk() throws Exception {
        Checking checking = checkingsRepository.save(new Checking(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18)));
        String balance = objectMapper.writeValueAsString(checking);
        System.out.println(balance);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/1").content(balance).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(savingsRepository.findById(checking.getId()).isPresent());
    }

    @Test
    @DisplayName("Modify Balance Account CreditCard")
    void patch_Account_CreditCard_ModifyBalance_isOk() throws Exception {
        CreditCard creditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18)));
        String balance = objectMapper.writeValueAsString(creditCard);
        System.out.println(balance);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/1").content(balance).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(savingsRepository.findById(creditCard.getId()).isPresent());
    }

    @Test
    @DisplayName("Delete Credit Card Account")
    void delete_CreditCard_Account_isOk() throws Exception {
        CreditCard creditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18)));
        String creditCard2 = objectMapper.writeValueAsString(creditCard);
        System.out.println(creditCard2);

        MvcResult mvcResult = mockMvc.perform(patch("/admin/modifyBalanceAccounts/1").content(creditCard2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(savingsRepository.findById(creditCard.getId()).isEmpty());
    }




}
