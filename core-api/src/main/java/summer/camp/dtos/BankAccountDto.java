package summer.camp.dtos;

import summer.camp.enums.AccountType;

import java.time.LocalDate;
public class BankAccountDto {
    private String accountId;
    private double solde;
    private LocalDate createdAt;
    private String currency;
    private AccountType type;
    private Long customerId;
    public BankAccountDto()
    {

    }
    public BankAccountDto(String accountId, double solde, LocalDate createdAt, String currency, AccountType type, Long customerId) {
        this.accountId = accountId;
        this.solde = solde;
        this.createdAt = createdAt;
        this.currency = currency;
        this.type = type;
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


}
