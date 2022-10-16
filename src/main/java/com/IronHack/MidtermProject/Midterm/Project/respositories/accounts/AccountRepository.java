package com.IronHack.MidtermProject.Midterm.Project.respositories.accounts;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



}
