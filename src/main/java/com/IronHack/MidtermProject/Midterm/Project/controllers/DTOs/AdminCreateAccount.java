package com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs;

import com.IronHack.MidtermProject.Midterm.Project.entity.accounts.Money;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Address;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AdminCreateAccount {

    @NotEmpty
    private Long adminId;

    @Min(value = 1)
    private Long primaryOwner;

    private Long secondaryOwner;

    @NotNull
    private BigDecimal balance;

    private BigDecimal minimBalance;

    @NotNull
    private LocalDate creationDate;
    @NotNull
    private Address address;

    private  Address secondaryAddress;

    private BigDecimal interestRate;

    private LocalDate dateOfBirth;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public AdminCreateAccount() {
    }

    public AdminCreateAccount(Long adminId, Long primaryOwner, BigDecimal balance, LocalDate creationDate, Address address) {
        this.adminId = adminId;
        this.primaryOwner = primaryOwner;
        this.balance = balance;
        this.creationDate = creationDate;
        this.address = address;
    }

    public AdminCreateAccount(Long adminId, Long primaryOwner, Long secondaryOwner, BigDecimal balance,
                              BigDecimal minimBalance, LocalDate creationDate, Address address, Address secondaryAddress,
                              BigDecimal interestRate) {
        this.adminId = adminId;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
        this.minimBalance = minimBalance;
        this.creationDate = creationDate;
        this.address = address;
        this.secondaryAddress = secondaryAddress;
        this.interestRate = interestRate;
    }



    //--------------------------- GETTERS & SETTERS: -------------------------


    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Long getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Long secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public BigDecimal getMinimBalance() {
        return minimBalance;
    }

    public void setMinimBalance(BigDecimal minimBalance) {
        this.minimBalance = minimBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }
}
