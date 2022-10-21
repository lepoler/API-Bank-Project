package com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs;

import java.math.BigDecimal;

public class HolderAccessBalanceDTO {

    private Long holderId;

    private Long holderAccountId;

    private BigDecimal balance;

    //--------------------------- CONSTRUCTORS: -------------------------


    public HolderAccessBalanceDTO() {
    }

    public HolderAccessBalanceDTO(Long holderId, Long holderAccountId, BigDecimal balance) {
        this.holderId = holderId;
        this.holderAccountId = holderAccountId;
        this.balance = balance;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------

    public Long getHolderId() {
        return holderId;
    }

    public void setHolderId(Long holderId) {
        this.holderId = holderId;
    }

    public Long getHolderAccountId() {
        return holderAccountId;
    }

    public void setHolderAccountId(Long holderAccountId) {
        this.holderAccountId = holderAccountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
