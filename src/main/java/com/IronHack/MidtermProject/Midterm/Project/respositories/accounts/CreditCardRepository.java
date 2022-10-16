package com.IronHack.MidtermProject.Midterm.Project.respositories.accounts;


import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.CreditCard;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    //------ ADMIN CREATE CREDIT CARD ACCOUNT---------
    Optional<CreditCard> findByBalanceAndPrimaryOwnerAndSecondaryOwnerAndCreationDate(Money balance, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate);



}
