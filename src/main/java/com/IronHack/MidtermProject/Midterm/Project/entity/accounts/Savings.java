package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.enums.Status;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Savings extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimBalance")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimBalance"))})
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_interestRate")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_interestRate"))})
    private Money interestRate;

    private Status status;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public Savings() {
    }

    public Savings(Money minimumBalance, Money interestRate, Status status) {
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = status;
    }

    public Savings(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate,
                   Money minimumBalance, Money interestRate, Status status) {

        super(balance, penaltyFee, primaryOwner, secondaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = status;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
