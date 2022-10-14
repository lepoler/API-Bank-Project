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

    //--------------------------- GETTERS & SETTERS: -------------------------
}
