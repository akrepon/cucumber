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
        Account account = accountRepo.findById(accountNr).orElseThrow(() -> new ItemNotFoundException(accountNr));
        if (account.getClients().stream()
                .filter(c -> c.getClientNumber().equals(clientNr))
                .findAny()
                .isPresent()) {

            Long newBalance = account.getSaldo() - amount;

            if (newBalance >= 0) {
                account.setSaldo(newBalance);
                accountRepo.save(account);
                return newBalance;
            } else {
                throw new OperationNotAllowedException("Account has no sufficient balance");
            }
        }
        throw new OperationNotAllowedException("Client has no acces to this account!");
    }

    @RequestMapping(value = "/{accountNr}/transfer/{amount}/{otherAccountNr}", method = RequestMethod.PUT)
    public Long transfer(@PathVariable String accountNr, @PathVariable Long amount, @PathVariable String otherAccountNr, @RequestHeader String clientNr) throws ItemNotFoundException, OperationNotAllowedException {
        if(accountNr.equals(otherAccountNr)){
            throw new OperationNotAllowedException("Can't transfer to the same account number!");
        }

        Account account = accountRepo.findById(accountNr).orElseThrow(() -> new ItemNotFoundException(accountNr));
        Account otherAccount = accountRepo.findById(otherAccountNr).orElseThrow(() -> new ItemNotFoundException(otherAccountNr));

        if (account.getClients().stream()
                .filter(c -> c.getClientNumber().equals(clientNr))
                .findAny()
                .isPresent()) {

            Long newBalance = account.getSaldo() - amount;

            if (newBalance >= 0) {
                account.setSaldo(newBalance);
                accountRepo.save(account);

                otherAccount.setSaldo(otherAccount.getSaldo() + amount);
                accountRepo.save(otherAccount);

                return newBalance;
            } else {
                throw new OperationNotAllowedException("Account has no sufficient balance");
            }
        }
        throw new OperationNotAllowedException("Client has no access to this account!");

    }


    @RequestMapping(value = "/{accountNr}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable String accountNr) {
        return this.accountRepo.findById(accountNr).get();
    }


}
