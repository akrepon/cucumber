package com.cucumber.tutorial.cucumber.steps;

import com.cucumber.tutorial.cucumber.CucumberStepDefinition;
import com.cucumber.tutorial.cucumber.repository.AccountRepository;
import com.cucumber.tutorial.cucumber.repository.ClientRepo;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StepDefinitions extends CucumberStepDefinition {
    public Actionwords actionwords = new Actionwords();

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ClientRepo clientRepo;

    private String accountNr;

    private String clientNr;

    private ResponseEntity<?> response;

    public StepDefinitions(){
        super();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Given("^the account number \"(.*)\" exists$")
    public void theAccountNumberAccountNrExists(String accountNr) {
        this.accountNr=accountNr;
        assertTrue(accountRepo.existsById(accountNr));
    }

    @Given("^the account is assigned to the client \"(.*)\"$")
    public void theAccountIsAssignedToTheClientClientNr(String clientNr) {
        this.clientNr=clientNr;
        assertTrue(clientRepo.findById(clientNr).get().getAccounts().stream().filter(account->account.getAccountNumber().equals(accountNr)).findAny().isPresent());
    }

    @When("^client withdraws \"(.*)\" from the account$")
    public void clientWithdrawsAmountFromTheAccount(String amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("clientNr",clientNr);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = this.rootUri + "/accounts/"+accountNr+"/withdraw/"+amount;
        response = template.exchange(url, HttpMethod.PUT, entity, Long.class);
    }

    @Given("^the account has \"(.*)\" balance$")
    public void theAccountHasAmountBalance(Long amount) {
        assertEquals(amount,accountRepo.findById(accountNr).get().getSaldo());
    }

    @Then("^transaction can't be completed$")
    public void transactionCantBeCompleted() {
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CONFLICT));
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/db/dataset.xml"));
    }
}