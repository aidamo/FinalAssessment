package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {
    WebDriver webDriver;

    //scenario 1
    @Given("^the PetClinic website$")
    public void thePetClinicWebsite() {
        webDriver = new ChromeDriver();
        webDriver.get("http://bhdtest.endava.com/petclinic/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("^the user navigates to Homepage$")
    public void theUserNavigatesToHomepage() {
        webDriver.findElement(By.xpath("//a[@title='home page']")).click();
    }

    @Then("^the page title should be displayed$")
    public void thePageTitleShouldBeDisplayed() throws Throwable {
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.xpath("//app-welcome/h1")).isDisplayed());
    }

    @And("^the header should be displayed$")
    public void theHeaderShouldBeDisplayed() throws Throwable {
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.xpath("//h2[contains(text(),'Welcome')]")).isDisplayed());
    }

    @And("^the image should be displayed$")
    public void theImageShouldBeDisplayed() throws Throwable {
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed());
    }

    //scenario 2
    @When("^the user navigates to All Owners Page$")
    public void theUserNavigatesToAllOwnersPage() throws Throwable {
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//a[text()='Owners']")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//a[@href='/petclinic/owners']")).click();
    }

    @Then("^the user expects for the owner \"([^\"]*)\" which should be present$")
    public void theUserExpectsForTheOwnerWhichShouldBePresent(String owner) {
        Boolean isPresent = webDriver.findElements(By.name("Betty Davis")).size() > 0;
    }

    //scenario 3
    @When("^the user navigates to Add Owner Page$")
    public void theUserNavigatesToAddOwnerPage() {
        webDriver.findElement(By.xpath("//a[text()='Owners']")).click();
        webDriver.findElement(By.xpath("//a[@routerlink='/owners/add']")).click();
    }

    @And("^the user enters an invalid value for phone number$")
    public void theUserEntersAnInvalidValueForPhoneNumber() throws Throwable {
        webDriver.findElement(By.id("firstName")).sendKeys("Matei");
        webDriver.findElement(By.id("lastName")).sendKeys("Ionescu");
        webDriver.findElement(By.id("address")).sendKeys("Muresilor nr.5");
        webDriver.findElement(By.id("city")).sendKeys("Bucuresti");
        webDriver.findElement(By.id("telephone")).sendKeys("hdhddudn");
    }

    @Then("^the user should see an error message$")
    public void theUserShouldSeeAnErrorMessage() throws Throwable {
        Assert.assertTrue(webDriver.findElement(By.className("help-block")).isDisplayed(), "Phone number only accept digits - should be displayed");
        webDriver.quit();
    }

    //scenario 4
    @And("^the user fills the form correctly$")
    public void theUserFillsTheFormCorrectly() throws Throwable {
        webDriver.findElement(By.id("firstName")).sendKeys("Ioana");
        webDriver.findElement(By.id("lastName")).sendKeys("Ionescu");
        webDriver.findElement(By.id("address")).sendKeys("Ciresilor nr.5");
        webDriver.findElement(By.id("city")).sendKeys("Bucuresti");
        webDriver.findElement(By.id("telephone")).sendKeys("07436087324");
    }

    @And("^the user clicks Save$")
    public void theUserClicksSave() throws Throwable {
        webDriver.findElement(By.xpath("//button[contains(text(),'Add Owner')]")).click();
    }

    @Then("^the user should see the newly added owner in the page$")
    public void theUserShouldSeeTheNewlyAddedOwnerInThePage() throws Throwable {
        Assert.assertTrue(webDriver.findElements(By.xpath("//*[contains(text(), 'Ioana Ionescu')]")).size() > 0, "The owner should be present in the page");
    }

    //scenario 5
    @When("^the user navigates to Veterinarians Page$")
    public void theUserNavigatesToVeterinariansPage() throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(text(),'Veterinarians')]")).click();
        webDriver.findElement(By.xpath("//a[@href='/petclinic/vets/add']")).click();
    }

    @And("^creates a new vet$")
    public void createsANewVet() throws Throwable {
        webDriver.findElement(By.id("firstName")).sendKeys("Radu");
        webDriver.findElement(By.id("lastName")).sendKeys("Ionescu");
        webDriver.findElement(By.id("specialties")).click();
        webDriver.findElement(By.xpath("//*[contains(text(),'surgery')]")).click();
        webDriver.findElement(By.xpath("//*[contains(text(),'Save Vet')]")).click();
    }

    @Then("^the user should see the newly added vet in the page$")
    public void theUserShouldSeeTheNewlyAddedVetInThePage() throws Throwable {
        Assert.assertTrue(webDriver.findElements(By.xpath("//*[contains(text(), 'Radu Ionescu')]")).size() > 0, "The owner should be present in the page");
    }

    //scenario 6
    @When("^the user navigates to All Vets Page$")
    public void theUserNavigatesToAllVetsPage() throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(text(),'Veterinarians')]")).click();
        webDriver.findElement(By.xpath("//a[@routerlink='/vets']")).click();
    }

    @And("^the user clicks Edit vet \"([^\"]*)\"$")
    public void theUserClicksEditVet(String vetName) throws Throwable {
        webDriver.findElement(By.xpath("*//tr[td//text()[contains(., '" + vetName + "')]]/td[3]//button[1]")).click();
    }

    @And("^the user clicks Add Specialty$")
    public void theUserClicksAddSpecialty() throws Throwable {
        webDriver.findElement(By.className("mat-select-arrow-wrapper")).click();

    }

    @And("^the user adds the \"([^\"]*)\" specialty$")
    public void theUserAddsTheSpecialty(String secondSpecialty) throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(@class, 'mat-option-text')][contains(text(), '" + secondSpecialty + "')]")).click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//span[contains(text(),'Home')]")));
        actions.click();
        actions.build().perform();
    }

    @Then("^the user should be able to save the form successfully$")
    public void theUserShouldBeAbleToSaveTheFormSuccessfully() throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(text(),'Save Vet')]")).click();
    }
//scenario 7

    @And("^the user clicks Delete vet \"([^\"]*)\"$")
    public void theUserClicksDeleteVet(String vetName) throws Throwable {
        webDriver.findElement(By.xpath("*//tr[td//text()[contains(., '" + vetName + "')]]/td[3]//button[2]")).click();
    }

    @Then("^the vet \"([^\"]*)\" should no longer be present$")
    public void theVetShouldNoLongerBePresent(String vetName) throws Throwable {
        Assert.assertTrue((webDriver.findElements(By.xpath("*//tr[td//text()[contains(., '" + vetName + "')]]")).size() > 0), "The deleted vet should no longer be present");
    }

    //scenario 8
    @When("^the user navigates to Pet Types$")
    public void theUserNavigatesToPetTypes() throws Throwable {
        webDriver.findElement(By.xpath("//a[@href='/petclinic/pettypes']")).click();
        Thread.sleep(2000);
    }

    @And("^the user creates a new pet \"([^\"]*)\"$")
    public void theUserCreatesANewPet(String petName) throws Throwable {
        webDriver.findElement(By.xpath("(//*[contains(text(), 'Add')])[3]")).click();
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).click();
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).sendKeys(petName);
        webDriver.findElement(By.xpath("//*[contains(text(), 'Save')]")).click();
        Thread.sleep(1000);
    }

    @And("^the user clicks Edit next to the pet \"([^\"]*)\"$")
    public void theUserClicksEditNextToThePet(String petname) throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(@id, '"+petname+"')]/../..//*[contains(text(),'Edit')]")).click();
        Thread.sleep(1000);
    }

    @And("^the user changes the name from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void theUserChangesTheNameFromTo( String petname, String newPetName) throws Throwable {
      webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).click();
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).clear();
       webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).sendKeys(newPetName);
    }


    @Then("^the user should be able to save the form$")
    public void theUserShouldBeAbleToSaveTheForm() throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(text(),'Update')]")).click();
    }


    //scenario 9
    @Then("^the user deletes a pet type$")
    public void theUserDeletesAPetType() throws Throwable {
        webDriver.findElement(By.xpath("//tbody//tr[5]//td[2]//button[2]")).click();
    }

    //scenario 10
    @And("^the user navigates to Specialties$")
    public void theUserNavigatesToSpecialties() throws Throwable {
        webDriver.findElement(By.xpath("//a[@title='specialties']")).click();
    }

    @When("^the user clicks on a specialty to edit it$")
    public void theUserClicksOnASpecialtyToEditIt() throws Throwable {
        webDriver.findElement(By.xpath("//tbody//tr[15]//td[2]//button[1]")).click();
    }

    @And("^the user enters a new name \"([^\"]*)\"$")
    public void theUserEntersANewName(String  newSpec) throws Throwable {
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).click();
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).clear();
        webDriver.findElement(By.xpath("//*[contains(@id, 'name')]")).sendKeys(newSpec);
    }

    @Then("^the name should be updated$")
    public void theNameShouldBeUpdated() throws Throwable {
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
