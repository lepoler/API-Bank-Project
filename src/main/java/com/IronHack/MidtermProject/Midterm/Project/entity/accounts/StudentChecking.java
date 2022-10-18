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

    public StudentChecking(Money balance, Holders primaryOwner, Holders secondaryOwner, LocalDate creationDate) {
        super(balance, primaryOwner, secondaryOwner, creationDate);
        this.status = Status.ACTIVE;
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
