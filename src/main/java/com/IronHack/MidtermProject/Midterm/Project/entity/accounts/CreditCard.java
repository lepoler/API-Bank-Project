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
    private Money interestRate = new Money(new BigDecimal(0.2));

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_creditLimit")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_creditLimit"))})
    private Money creditLimit = new Money(new BigDecimal(100));

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public CreditCard() {
    }

    public CreditCard(Money interestRate, Money creditLimit) {
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, String secretKey,
                      LocalDate creationDate, Money interestRate, Money creditLimit) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, secretKey, creationDate);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCard(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, String secretKey,
                      LocalDate creationDate) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, secretKey, creationDate);

    }



    //--------------------------- GETTERS & SETTERS: -------------------------


    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        if(interestRate.getAmount().compareTo(new BigDecimal(0.2 )) == 1) {
            throw new IllegalArgumentException("Maximum Interest Rate needs to be equal or less than 0.2");
        } else if (interestRate.getAmount().compareTo(new BigDecimal(0.1)) == -1) {
            throw new IllegalArgumentException("Minimum Interest Rate needs to be equal or more than 0.1");
        }
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }
}
