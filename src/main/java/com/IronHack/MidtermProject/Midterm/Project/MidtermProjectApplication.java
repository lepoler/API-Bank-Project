package com.IronHack.MidtermProject.Midterm.Project;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.AdminRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.HoldersRepository;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MidtermProjectApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	HoldersRepository holdersRepository;

	public static void main(String[] args) {
		SpringApplication.run(MidtermProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Holders holder1 = holdersRepository.save(new Holders("Pol", LocalDate.of(1992,05,01), null, null));
		Admin administrador = adminRepository.save(new Admin("Manolo"));





	}
}
