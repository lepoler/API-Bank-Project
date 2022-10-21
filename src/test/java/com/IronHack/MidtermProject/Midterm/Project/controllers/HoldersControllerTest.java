package com.IronHack.MidtermProject.Midterm.Project.controllers;

import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderAccessBalanceDTO;
import com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs.HolderTransferMoneyDTO;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.RoleRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
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

    @Autowired
    RoleRepository roleRepository;

    private MockMvc mockMvc;
    private Holders holder, holder2;
    private Account accountChecking, accountSaving, accountCreditCard;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @BeforeEach
    public void SetUp(){

        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345"), "Julian", passwordEncoder.encode("1234")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Verdadera", "Springfild", "Russia", "12345"), "Pablo", passwordEncoder.encode("4321")));
        accountSaving = savingsRepository.save(new Savings(new Money(new BigDecimal(800)), new Money(), holder, holder2,
                "1234", LocalDate.of(1998, 01, 01)));
        accountChecking = checkingsRepository.save(new Checking(new Money(new BigDecimal(550)), new Money(new BigDecimal(40)), holder2, holder,
                "1234"));
        accountCreditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(850)),
                new Money(new BigDecimal(40)), holder, holder2, "1234",
                LocalDate.of(1958, 01, 01)));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown(){


        creditCardRepository.deleteAll();
        checkingsRepository.deleteAll();
        savingsRepository.deleteAll();
        roleRepository.deleteAll();


    }

    @Test
    @DisplayName("Holders Access Balance Account")
    void get_Balance_Account_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceAccount/"+ holder.getId() + "/" + accountSaving.getId())).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("800"));
    }

    @Test
    @DisplayName("Holder Error Access Balance Works")
    void patch_accessBalance_ErrorHolderId_worksOk() throws Exception {
        HolderAccessBalanceDTO holderId = new HolderAccessBalanceDTO(10020L, accountSaving.getId(), new BigDecimal(800));
        String body = objectMapper.writeValueAsString(holderId);

        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceAccount/"+ 10002 + "/" + accountSaving.getId()))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Error Access Balance Works")
    void patch_accessBalance_ErrorHolderAccountId_worksOk() throws Exception {
        HolderAccessBalanceDTO holderAccountId = new HolderAccessBalanceDTO(holder.getId(), 80L, new BigDecimal(800));
        String body = objectMapper.writeValueAsString(holderAccountId);

        MvcResult mvcResult = mockMvc.perform(get("/holdersAccessBalanceAccount/"+ holder.getId() + "/" + 78524).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }



    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_worksOk() throws Exception {
        HolderTransferMoneyDTO transfer = new HolderTransferMoneyDTO(holder.getId(), accountSaving.getId(), holder2.getId(), accountChecking.getId(), new BigDecimal(400));
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(savingsRepository.findById(accountSaving.getId()).get().getBalance().getAmount().compareTo(new BigDecimal(400)) == 0);
    }

    @Test
    @DisplayName("Holder Error Transfer Money Works")
    void patch_transferMoney_ErrorIdHolder_worksOk() throws Exception {
        HolderTransferMoneyDTO idHolder = new HolderTransferMoneyDTO(10L, 1L, holder2.getId(), 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idHolder);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Error Transfer Money Works")
    void patch_transferMoney_ErrorIdAccountHolder_worksOk() throws Exception {
        HolderTransferMoneyDTO idAccountHolder = new HolderTransferMoneyDTO(holder.getId(), 20L, holder2.getId(), 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idAccountHolder);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Error Transfer Money Works")
    void patch_transferMoney_ErrorIdReceiver_worksOk() throws Exception {
        HolderTransferMoneyDTO idReceiver = new HolderTransferMoneyDTO(holder.getId(), 1L, 10L, 2L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idReceiver);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @DisplayName("Holder Error Transfer Money Works")
    void patch_transferMoney_ErrorIdAccountReceiver_worksOk() throws Exception {
        HolderTransferMoneyDTO idAccountReceiver = new HolderTransferMoneyDTO(holder.getId(), 1L, holder2.getId(), 20L, new BigDecimal(400));
        String body = objectMapper.writeValueAsString(idAccountReceiver);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
    }



}
