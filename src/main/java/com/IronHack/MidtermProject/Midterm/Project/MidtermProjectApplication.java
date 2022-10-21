package com.IronHack.MidtermProject.Midterm.Project;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.*;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Role;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.AccountRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CheckingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.CreditCardRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.accounts.SavingsRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.AdminRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.RoleRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class MidtermProjectApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	HoldersRepository holdersRepository;
	@Autowired
	CheckingsRepository checkingsRepository;
	@Autowired
	SavingsRepository savingsRepository;
	@Autowired
	CreditCardRepository creditCardRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	//private Account accountChecking, accountSaving, accountCreditCard;
	public static void main(String[] args) {
		SpringApplication.run(MidtermProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Holders holder1 = holdersRepository.save(new Holders("Alberto", LocalDate.of(1992, 05, 01),
				null, "Dolores", passwordEncoder.encode("1234")));
		Holders holder2 = holdersRepository.save(new Holders("Diana", LocalDate.of(1994, 07, 27),
				null, "Asuncion", passwordEncoder.encode("1234")));
		Admin administrador = adminRepository.save(new Admin("Manolo", "Julio0", passwordEncoder.encode("1234")));
		roleRepository.save(new Role("ADMIN", administrador));
		roleRepository.save(new Role("USER", holder1));
		roleRepository.save(new Role("USER", holder2));


		Savings accountSaving = savingsRepository.save(new Savings(new Money(new BigDecimal(800)), new Money(), holder1, null,
				"1234", LocalDate.of(1998, 01, 01), new Money(new BigDecimal(250)), new Money(new BigDecimal(40))));
		savingsRepository.save(accountSaving);

		Checking accountChecking = checkingsRepository.save(new Checking(new Money(new BigDecimal(550)), new Money(new BigDecimal(40)), holder2, null,
				"1234"));
		checkingsRepository.save(accountChecking);


		CreditCard accountCreditCard = creditCardRepository.save(new CreditCard(new Money(new BigDecimal(850)),
				new Money(new BigDecimal(40)), holder1, null, "1234", LocalDate.of(1958, 01, 01)));

		creditCardRepository.save(accountCreditCard);
	}


}
