package com.cucumber.tutorial.cucumber.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @Column(name="client_number")
    private String clientNumber;

    @ManyToMany
    @JoinTable(name = "account_client",
            joinColumns = @JoinColumn(name = "client_nr"),
            inverseJoinColumns = @JoinColumn(name = "account_nr")
    )
    private List<Account> accounts;

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
