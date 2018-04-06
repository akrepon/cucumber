package com.cucumber.tutorial.cucumber.steps;

import com.cucumber.tutorial.cucumber.CucumberStepDefinition;
import com.cucumber.tutorial.cucumber.repository.AccountRepository;
import com.cucumber.tutorial.cucumber.repository.ClientRepo;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
public class StepDefinitions extends CucumberStepDefinition {
    public Actionwords actionwords = new Actionwords();

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ClientRepo clientRepo;

    private String accountNr;

    @Given("^the account number \"(.*)\" exists$")
    public void theAccountNumberAccountNrExists(String accountNr) {
        this.accountNr=accountNr;
        assertTrue(accountRepo.existsById(accountNr));
    }

    @Given("^the account is assigned to the client \"(.*)\"$")
    public void theAccountIsAssignedToTheClientClientNr(String clientNr) {
        assertTrue(clientRepo.findById(clientNr).get().getAccounts().stream().filter(account->account.getAccountNumber().equals(accountNr)).findAny().isPresent());
    }

    @When("^client withdraws \"(.*)\" from the account$")
    public void clientWithdrawsAmountFromTheAccount(String amount) {

    }

    @Given("^the account has \"(.*)\" balance$")
    public void theAccountHasAmountBalance(String amount) {


    }

    @Then("^transaction can't be completed$")
    public void transactionCantBeCompleted() {


    }
}