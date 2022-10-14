package com.IronHack.MidtermProject.Midterm.Project.respositories.accounts;


import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingRepository  extends JpaRepository<StudentChecking, Long> {
}
