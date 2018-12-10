Feature: Equal Expert test

  Scenario: Shopping cart case one

    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99

    When the user adds 5 Dove Soap to the shopping cart

    Then The shopping cart should contain 5 Dove Soap each with a unit price of 39.99
    And the shopping cart’s total price should equal 199.95


  Scenario: Shopping cart case two

    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99

    When the user adds 5 Dove Soap to the shopping cart
    And then adds another 3 Dove Soap to the shopping cart

    Then The shopping cart should contain 8 Dove Soap each with a unit price of 39.99
    And the shopping cart’s total price should equal 319.92