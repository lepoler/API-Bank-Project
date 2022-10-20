package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.enums.Status;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimAmount")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimAmount"))})
    private final Money minimBalance =  new Money(new BigDecimal(250));

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_monthlyMaintenanceFee")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthlyMaintenanceFee"))})
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));

    private Status status;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public Checking() {
    }

    public Checking(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, String secretKey, LocalDate creationDate) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, secretKey, creationDate);

    }



    //--------------------------- GETTERS & SETTERS: -------------------------

    public void setBalance(Money balance) {
        if(balance.getAmount().compareTo(minimBalance.getAmount()) == -1){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(balance);
    }

    public Money getMinimBalance() {
        return minimBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}