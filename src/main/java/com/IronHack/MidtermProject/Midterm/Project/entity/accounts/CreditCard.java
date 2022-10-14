package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_interestRate")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_interestRate"))})
    private BigDecimal interestRate;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit"))})
    private BigDecimal creditLimit;

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public CreditCard() {
    }

    public CreditCard(BigDecimal interestRate, BigDecimal creditLimit) {
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }
//--------------------------- GETTERS & SETTERS: -------------------------


    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
