package com.IronHack.MidtermProject.Midterm.Project;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HoldersControllerTest {

    @Autowired
    HoldersRepository holdersRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private Holders holder, holder2;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void SetUp(){

        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Falsa", "Springfild", "Russia", "12345")));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Find All Accounts By Holders")
    void get_All_Accounts_Ok(){
        MockMvc mvcResult = mockMvc.perform(get("/holdersAccessBalanceSavingsAccount/{id}")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(""));


    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_fromSavings_worksOk() throws JsonProcessingException {
        Savings transfer = new Savings(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18));
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/{id}").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_fromChecking_worksOk() throws JsonProcessingException {
        Checking transfer = new Checking(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18));
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/{id}").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("Holder Transfer Money Works")
    void patch_transferMoney_fromCreditCard_worksOk() throws JsonProcessingException {
        CreditCard transfer = new CreditCard(new Money(new BigDecimal(333)),holder, holder2,
                LocalDate.of(1988, 07, 18));
        String body = objectMapper.writeValueAsString(transfer);

        MvcResult mvcResult = mockMvc.perform(patch("/holdersMakeTransfer/{id}").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }





}
