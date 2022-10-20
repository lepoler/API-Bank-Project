package com.IronHack.MidtermProject.Midterm.Project.entity.accounts;

import com.IronHack.MidtermProject.Midterm.Project.enums.Status;
import com.IronHack.MidtermProject.Midterm.Project.entity.users.Holders;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class StudentChecking extends Account {

    private Status status;

    //-------------------------------------------------------------------


    //--------------------------- CONSTRUCTORS: -------------------------

    public StudentChecking() {
    }

    public StudentChecking(Money balance, Money penaltyFee, Holders primaryOwner, Holders secondaryOwner,
                           String secretKey, LocalDate creationDate) {
        super(balance, penaltyFee, primaryOwner, secondaryOwner, secretKey, creationDate);

    }

    //--------------------------- GETTERS & SETTERS: -------------------------
    public StudentChecking(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
