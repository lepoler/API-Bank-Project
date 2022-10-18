package com.IronHack.MidtermProject.Midterm.Project.respositories.users;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
