package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class ThirdParty extends User {

    private String hashKey;





    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public ThirdParty() {
    }

    public ThirdParty(String name, String username, String password, String hashKey) {
        super(name, username, password);
        this.hashKey = hashKey;
    }

//--------------------------- GETTERS & SETTERS: -------------------------


    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }



}
