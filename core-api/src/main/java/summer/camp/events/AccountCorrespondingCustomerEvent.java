package summer.camp.events;

import summer.camp.enums.CustomerAccountStatus;

public class AccountCorrespondingCustomerEvent {

    private String accountId;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CustomerAccountStatus status;
    public AccountCorrespondingCustomerEvent(){}
    public AccountCorrespondingCustomerEvent(String accountId, Long id, String firstName, String lastName, String email, CustomerAccountStatus status) {
        this.accountId = accountId;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public AccountCorrespondingCustomerEvent(String accountId,CustomerAccountStatus customerAccountStatus) {
        status=customerAccountStatus;
        this.accountId=accountId;
    }


    public String getAccountId() {
        return accountId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public CustomerAccountStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerAccountStatus status) {
        this.status = status;
    }
}
