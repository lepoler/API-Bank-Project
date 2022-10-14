package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.enums.Status;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Checking extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimAmount")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimAmount"))})
    private Money minimAmount;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_monthlyMaintenanceFee")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthlyMaintenanceFee"))})
    private Money monthlyMaintenanceFee;

    private Status status;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public Checking() {
    }

    public Checking(Money minimAmount, Money monthlyMaintenanceFee, Status status) {
        this.minimAmount = minimAmount;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public Checking(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate, Money minimAmount, Money monthlyMaintenanceFee, Status status) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, creationDate);
        this.minimAmount = minimAmount;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }


    //--------------------------- GETTERS & SETTERS: -------------------------


    public Money getMinimAmount() {
        return minimAmount;
    }

    public void setMinimAmount(Money minimAmount) {
        this.minimAmount = minimAmount;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}