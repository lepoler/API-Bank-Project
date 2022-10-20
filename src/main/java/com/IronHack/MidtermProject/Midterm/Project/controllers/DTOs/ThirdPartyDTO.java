package com.IronHack.MidtermProject.Midterm.Project.controllers.DTOs;

import java.math.BigDecimal;

public class ThirdPartyDTO {


    private BigDecimal amount;

    private Long accountId;

    private String accountSecretKey;


    //--------------------------- CONSTRUCTORS: -------------------------

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(BigDecimal amount, Long accountId, String accountSecretKey) {
        this.amount = amount;
        this.accountId = accountId;
        this.accountSecretKey = accountSecretKey;
    }

    //--------------------------- GETTERS & SETTERS: -------------------------


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountSecretKey() {
        return accountSecretKey;
    }

    public void setAccountSecretKey(String accountSecretKey) {
        this.accountSecretKey = accountSecretKey;
    }


}