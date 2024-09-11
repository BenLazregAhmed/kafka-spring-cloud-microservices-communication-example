package summer.camp.events;

public class BankAccountCreatedEvent {
    private Long customerId;
    private String accountId;
    public Long getCustomerId() {
        return customerId;
    }
    public BankAccountCreatedEvent() {
    }
    public BankAccountCreatedEvent(Long customerId, String accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
