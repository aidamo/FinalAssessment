Feature:

  Scenario: Verify PetClinic's Homepage
    Given the PetClinic website
    When the user navigates to Homepage
    Then the page title should be displayed
    And the header should be displayed
    And the image should be displayed

  Scenario: Check presence of an Owner
    Given the PetClinic website
    When the user navigates to All Owners Page
    Then the user expects for the owner "Betty Davis" which should be present

  Scenario: Adding an invalid phone number for a new owner should throw an error
    Given the PetClinic website
    When the user navigates to Add Owner Page
    And the user enters an invalid value for phone number
    Then the user should see an error message

  Scenario: Adding a valid phone number for a new owner
    Given the PetClinic website
    When the user navigates to Add Owner Page
    And the user fills the form correctly
    And the user clicks Save
    Then the user should see the newly added owner in the page

  Scenario: Creating a new vet
    Given the PetClinic website
    When the user navigates to Veterinarians Page
    And creates a new vet
    Then the user should see the newly added vet in the page

  Scenario: Edit a vet, add the specialty and save
    Given the PetClinic website
    When the user navigates to All Vets Page
    And the user clicks Edit vet "Radu Ionescu"
    And the user clicks Add Specialty
    And the user adds the "Radiology" specialty
    Then the user should be able to save the form successfully

  Scenario: Delete the vet just created
    Given the PetClinic website
    When the user navigates to All Vets Page
    And the user clicks Delete vet "Radu Ionescu"
    Then the vet "Radu Ionescu" should no longer be present

  Scenario: Adding a new pet
    Given the PetClinic website
    When the user navigates to Pet Types
    And the user creates a new pet "turtle"
    And the user clicks Edit next to the pet "12"
    And the user changes the name from "turtle" to "newTurtle"
    Then the user should be able to save the form

  Scenario: Delete new pet
    Given  the PetClinic website
    When the user navigates to Pet Types
    Then the user deletes a pet type

    Scenario: Edit a specialty
      Given the PetClinic website
      And the user navigates to Specialties
      When the user clicks on a specialty to edit it
      And the user enters a new name "newSpecialty"
      Then the name should be updated

