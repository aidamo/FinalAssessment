Feature: API Testing
  Scenario: User wants to check the OwnerController permission on VET_ADMIN
    Given I have access to perform GET request with username "aidavets" and password "test2"
    When I perform a GET request to "/owners"
    Then I will have the status code "400"

  Scenario: User wants to check the OwnerController permission on VET_ADMIN
    Given I have access to perform GET request with username "aidavets" and password "test2"
    When I perform a GET request to "/vets"
    Then I will have the status code "200"

    Scenario: User wants to check the VetController permission on OWNER_ADMIN
      Given I have access to perform GET request with username "aidaowners" and password "test1"
      When I perform a GET request to "/vets"
      Then I will have the status code "400"

  Scenario: User wants to check the VetController permission on OWNER_ADMIN
    Given I have access to perform GET request with username "aidaowners" and password "test1"
    When I perform a GET request to "/owners"
    Then I will have the status code "200"

