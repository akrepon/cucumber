Feature: Bank transfer


  Scenario: Transfer to the same account
    Given the account number "BE762789890" exists
    And the account is assigned to the client "DHM555"
    When client transfers "100" to account  "BE762789890"
    Then transaction can't be completed

  Scenario: Transfer to another account
    Given the account number "BE762789890" exists
    And the account is assigned to the client "DHM555"
    When client transfers "100" to account  "BE762789880"
    Then the transaction is successfull
