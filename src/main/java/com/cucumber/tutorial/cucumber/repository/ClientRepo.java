package com.cucumber.tutorial.cucumber.repository;

import com.cucumber.tutorial.cucumber.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client,String> {
}
