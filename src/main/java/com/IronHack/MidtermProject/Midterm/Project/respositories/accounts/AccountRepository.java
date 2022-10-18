package com.IronHack.MidtermProject.Midterm.Project.respositories.accounts;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //------ ADMIN CREATE CHECKING or STUDENT ACCOUNT---------
    Optional<Account> findByBalanceAndPrimaryOwnerIdAndSecondaryOwnerIdAndCreationDate(Money balance, Long primaryOwner, Long secondaryOwner, LocalDate creationDate);

    Account findByAgeBefore(LocalDate dateOfBirth);

}
