package com.cucumber.tutorial.cucumber.repository;

import com.cucumber.tutorial.cucumber.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
}
