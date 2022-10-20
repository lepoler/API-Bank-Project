package com.IronHack.MidtermProject.Midterm.Project;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.AdminRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    HoldersRepository holdersRepository;
    @Autowired
    CheckingsRepository checkingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    List<Account> accounts;
    private Holders holder, holder2;

    @BeforeEach
    void setUp(){
        holder = holdersRepository.save(new Holders("Pol Bermu", LocalDate.of(1998, 01, 01),
                new Address("calle Falsa", "Springfild", "Russia", "12345"), "Julian", passwordEncoder.encode("1234")));
        holder2 = holdersRepository.save(new Holders("Anna Nana", LocalDate.of(2000, 10, 22),
                new Address("calle Falsa", "Springfild", "Russia", "12345"), "Pablo", passwordEncoder.encode("1234")));
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    @DisplayName("Test Account Repository")
    void find_Account_NotExist_WorksOk(){

    }




}
