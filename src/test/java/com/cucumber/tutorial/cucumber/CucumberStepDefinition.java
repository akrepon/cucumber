package com.cucumber.tutorial.cucumber;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;

@ActiveProfiles("test")
@ContextConfiguration
@SpringBootTest(
        classes = CucumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class CucumberStepDefinition extends DBTestCase {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate template;

    protected String rootUri;

    public CucumberStepDefinition() {
        super();
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @PostConstruct
    public void initialize() {
        this.rootUri = "http://localhost:" + port;
    }

}