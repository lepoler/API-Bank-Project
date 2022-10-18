package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.enums.Status;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimBalance")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimBalance"))})
    private Money minimumBalance = new Money(new BigDecimal(1000));

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_interestRate")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_interestRate"))})
    private Money interestRate = new Money(new BigDecimal(0.0025));

    private Status status;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public Savings() {
    }

    public Savings(Money balance, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate) {
        super(balance, primaryOwner, secondaryOwner, creationDate);
    }

    public Savings(Money balance, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate,
                   Money minimumBalance, Money interestRate) {

        super(balance, primaryOwner, secondaryOwner, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = Status.ACTIVE;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------

    @Override
    public void setBalance(Money balance) {
        if(balance.getAmount().compareTo(minimumBalance.getAmount()) == -1){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(balance);
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if(minimumBalance.getAmount().compareTo(new BigDecimal(1000)) == 1){
            throw new IllegalArgumentException("Maximum Balance needs to be equal or less than 1000");
        } else if(minimumBalance.getAmount().compareTo(new BigDecimal(100))==-1){
            throw new IllegalArgumentException("Minimum Balance needs to be equal or more than 100");
        }
        this.minimumBalance = minimumBalance;
    }

    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        if(interestRate.getAmount().compareTo(new BigDecimal(0.5))== 1){
            throw new IllegalArgumentException("Interes rate can be big than 0.5");
        }
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
