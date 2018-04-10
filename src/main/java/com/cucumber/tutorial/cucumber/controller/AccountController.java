package com.cucumber.tutorial.cucumber.controller;

import com.cucumber.tutorial.cucumber.exception.ItemNotFoundException;
import com.cucumber.tutorial.cucumber.exception.OperationNotAllowedException;
import com.cucumber.tutorial.cucumber.model.Account;
import com.cucumber.tutorial.cucumber.repository.AccountRepository;
import com.cucumber.tutorial.cucumber.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ClientRepo clientRepo;

    @RequestMapping(value = "/{accountNr}/withdraw/{amount}", method = RequestMethod.PUT)
    public Long withdraw(@PathVariable String accountNr, @PathVariable Long amount, @RequestHeader String clientNr) throws ItemNotFoundException, OperationNotAllowedException {
        Optional<Account> account = accountRepo.findById(accountNr);
        if (account.isPresent()) {
            if (account.get().getClients().stream()
                    .filter(c -> c.getClientNumber().equals(clientNr))
                    .findAny()
                    .isPresent()) {

                Long newBalance = account.get().getSaldo() - amount;

                if (newBalance >= 0) {
                    account.get().setSaldo(newBalance);
                    accountRepo.save(account.get());
                    return newBalance;
                } else {
                    throw new OperationNotAllowedException("Account has no sufficient balance");
                }
            }
            throw new OperationNotAllowedException("Client has no acces to this account!");

        } else {
            throw new ItemNotFoundException(accountNr);
        }
    }

    @RequestMapping(value = "/{accountNr}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable String accountNr) {
        return this.accountRepo.findById(accountNr).get();
    }


}
