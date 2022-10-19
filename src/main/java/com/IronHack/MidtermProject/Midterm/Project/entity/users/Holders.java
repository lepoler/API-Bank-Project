package com.IronHack.MidtermProject.Midterm.Project.entity.users;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Holders extends User {

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    List<Account> accountListPrimary;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    List<Account> accountListSecondary;


    private LocalDate dateOfBirth;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "street", column = @Column(name = "street_mail")),
            @AttributeOverride(name = "city", column = @Column(name = "city_mail")),
            @AttributeOverride(name = "country", column = @Column(name = "country_mail")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postalCode_mail"))})
    private Address mail;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "street", column = @Column(name = "street_address")),
            @AttributeOverride(name = "city", column = @Column(name = "city_address")),
            @AttributeOverride(name = "country", column = @Column(name = "country_address")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postalCode_address"))})
    private Address address;



    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------

    public Holders() {
    }

    public Holders(String name, LocalDate dateOfBirth, Address address) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Holders(String name, LocalDate dateOfBirth, Address mail, Address address) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.address = address;
    }

    public Holders(List<Account> accountListPrimary, List<Account> accountListSecondary, LocalDate dateOfBirth, Address mail, Address address) {
        this.accountListPrimary = accountListPrimary;
        this.accountListSecondary = accountListSecondary;
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.address = address;
    }

    public Holders(LocalDate dateOfBirth, Address mail, Address address) {
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.address = address;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public List<Account> getAccountListPrimary() {
        return accountListPrimary;
    }

    public void setAccountListPrimary(List<Account> accountListPrimary) {
        this.accountListPrimary = accountListPrimary;
    }

    public List<Account> getAccountListSecondary() {
        return accountListSecondary;
    }

    public void setAccountListSecondary(List<Account> accountListSecondary) {
        this.accountListSecondary = accountListSecondary;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getMail() {
        return mail;
    }

    public void setMail(Address mail) {
        this.mail = mail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
