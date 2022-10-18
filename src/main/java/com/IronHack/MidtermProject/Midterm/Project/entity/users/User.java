package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //User, pasword, listRole -> cuando sepa security

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------

    public User() {
    }
    public User(String name) {
        this.name = name;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
