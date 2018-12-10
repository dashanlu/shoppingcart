Feature: Equal Expert test

  Scenario: Shopping cart case three

    Given an empty shopping cart
    And a product, Dove Soap with a unit price of 39.99
    And a product, Axe Deo with a unit price of 99.99
    And a sales tax rate of 12.5%

    When the user adds 2 Dove Soap to the shopping cart
    And then adds 2 Axe Deo to the shopping cart

    Then The shopping cart should contain 2 Dove Soap each with a unit price of 39.99
    And The shopping cart should contain 2 Axe Deo each with a unit price of 99.99
    And the total sales tax amount for the shopping cart should equal 35.00
    And the shopping cart’s total price should equal 314.96
