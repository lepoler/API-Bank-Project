package com.IronHack.MidtermProject.Midterm.Project.respositories.accounts;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Checking;
import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
