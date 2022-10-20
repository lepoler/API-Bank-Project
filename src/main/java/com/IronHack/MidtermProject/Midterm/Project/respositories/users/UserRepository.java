package com.IronHack.MidtermProject.Midterm.Project.respositories.users;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
