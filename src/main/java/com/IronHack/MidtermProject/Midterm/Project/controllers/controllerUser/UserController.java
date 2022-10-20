package com.IronHack.MidtermProject.Midterm.Project.controllers.controllerUser;


import com.IronHack.MidtermProject.Midterm.Project.controllers.interfacesUser.UserControllerInterface;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;
import com.IronHack.MidtermProject.Midterm.Project.respositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());

        return userRepository.findAll();
    }
}
