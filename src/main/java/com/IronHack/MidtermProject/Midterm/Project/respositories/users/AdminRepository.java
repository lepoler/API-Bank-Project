package com.IronHack.MidtermProject.Midterm.Project.respositories.users;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
