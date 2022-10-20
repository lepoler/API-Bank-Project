package com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs;

import java.math.BigDecimal;

public class HolderTransferMoneyDTO {

    private Long holderId;
    private Long holderAccountId;

    private Long holderReceivesId;
    private Long holderAccountReceivesId;
    private BigDecimal transferAmount;

    // decidir con que cuenta puede hacer la transfe
    // id de las dos cuentas
    //hacer validacion balance en el service

    //-------------------------------------------------------------------

    //--------------------------- CONSTRUCTORS: -------------------------


    public HolderTransferMoneyDTO() {
    }

    public HolderTransferMoneyDTO(Long holderId, Long holderAccountId, Long holderReceivesId, Long holderAccountReceivesId, BigDecimal transferAmount) {
        this.holderId = holderId;
        this.holderAccountId = holderAccountId;
        this.holderReceivesId = holderReceivesId;
        this.holderAccountReceivesId = holderAccountReceivesId;
        this.transferAmount = transferAmount;
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

    public Long getHolderAccountReceivesId() {
        return holderAccountReceivesId;
    }

    public void setHolderAccountReceivesId(Long holderAccountReceivesId) {
        this.holderAccountReceivesId = holderAccountReceivesId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Long getHolderReceivesId() {
        return holderReceivesId;
    }

    public void setHolderReceivesId(Long holderReceivesId) {
        this.holderReceivesId = holderReceivesId;
    }

    /*public void setBalance(BigDecimal balance) {
        if(balance.compareTo(transferAmount) == -1){
            throw new IllegalArgumentException("You can not do this transfer, because your balance is less than the amount of transaction");
        } if (balance.compareTo(transferAmount) == 0) {
            throw new IllegalArgumentException("You transfer was successfully, but your actual balance is 0");
        }if (balance.compareTo(transferAmount) == 1) {
            throw new IllegalArgumentException("You transfer was successfully");
        }
            this.balance = balance;
    }*/


}
