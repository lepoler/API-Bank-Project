package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------

    public Admin(String name) {
        super(name);
    }

    public Admin() {
    }



    //--------------------------- GETTERS & SETTERS: -------------------------

}
