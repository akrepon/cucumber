Feature: Account withdrawal


  Scenario: Account has enough balance
    Given the account number "BE762789890" exists
    And the account is assigned to the client "DHM555"
    And the account has "200" balance
    When client withdraws "100" from the account
    Then the account has "100" balance

  Scenario: Account has not enough balance
    Given the account number "BE762789880" exists
    And the account is assigned to the client "DHM555"
    And the account has "90" balance
    When client withdraws "100" from the account
    Then transaction can't be completed
