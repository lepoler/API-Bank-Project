package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.User;

import javax.persistence.Entity;


@Entity
public class ThirdParty extends User {

    private String hashKey;



    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public ThirdParty() {
    }

    public ThirdParty(String hashKey) {
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
