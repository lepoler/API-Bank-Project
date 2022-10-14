package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_balance")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_balance"))})
    private Money balance;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_penaltyFee")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_penaltyFee"))})
    private Money penaltyFee;


    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private Holders primaryOwner;


    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private Holders secondaryOwner;

    private LocalDate creationDate;
    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public Account() {
    }

    public Account(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate) {
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Holders getSecondaryOwner() {

        return secondaryOwner;
    }

    public void setSecondaryOwner(Holders secondaryOwner) {

        this.secondaryOwner = secondaryOwner;
    }

    public Holders getPrimaryOwner() {

        return primaryOwner;
    }

    public void setPrimaryOwner(Holders primaryOwner) {

        this.primaryOwner = primaryOwner;
    }
}
