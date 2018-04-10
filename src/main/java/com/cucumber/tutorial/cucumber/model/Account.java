package com.cucumber.tutorial.cucumber.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "saving_account")
    private boolean savingAccount;

    @ManyToMany(mappedBy = "accounts",fetch = FetchType.EAGER )
    private List<Client> clients;

    @Column(name="saldo")
    private Long saldo;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(boolean savingAccount) {
        this.savingAccount = savingAccount;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }
}
