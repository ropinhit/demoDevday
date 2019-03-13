package com.cafetownsend.test.stepdefs;

import com.cafetownsend.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;


public class LoginStepdefs {

    public WebDriver driver;

    public LoginStepdefs() {
        driver = Hooks.driver;
    }

    private LoginPage loginPage;

    @Given("^The coffee shop page is opening$")
    public void iGoToCoffeePage() throws Throwable {
        //Launch the Online Website
        Calendar cal = Calendar. getInstance();
        Date date=cal. getTime();
        System.out.println(String.valueOf(date.getTime()));

        driver.get("http://cafetownsend-angular-rails.herokuapp.com/login");
//
//        // Print a Log In message to the screen
        System.out.println("Successfully opened the website cafetownsend-angular-rails.herokuapp.com");

    }


    @When("^I enter username and password$")
    public void testLoginSuccessfully() throws Exception {
        //locating elements by xpath
        //    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String userXpath = "//label[./span[text() = 'Username*']]/input";
        String passXpath = "//label[./span[text() = 'Password*']]/input";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath(userXpath)).clear();
        driver.findElement(By.xpath(userXpath)).sendKeys("Luke");

        driver.findElement(By.xpath(passXpath)).clear();
        driver.findElement(By.xpath(passXpath)).sendKeys("Skywalker");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(5000);
    }

    @When("^I enter username '(.*)' and password '(.*)'$")
    public void testLoginSuccessfullyWithUserPass(String user, String pass) throws Throwable {
        //locating elements by xpath
        String userXpath = "//label[./span[text() = 'Username*']]/input";
        String passXpath = "//label[./span[text() = 'Password*']]/input";

        ///////wait until page loaded////////
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath(userXpath)).clear();
        driver.findElement(By.xpath(userXpath)).sendKeys(user);

        driver.findElement(By.xpath(passXpath)).clear();
        driver.findElement(By.xpath(passXpath)).sendKeys(pass);

        //  Assert.assertTrue(employeePage.isLoaded(), "Verify user is in Employee page!");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(5000);

    }


    @Then("^I should see the greeting message '(.*)'$")
    public void iShouldSeeTheGreetingMessageHelloLuke(String message) throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String text = driver.findElement(By.id("greetings")).getText();
        assertEquals(message, text);
        Thread.sleep(5000);

        driver.quit();
    }

    @And("^I should see the logout button$")
    public void iShouldSeeTheLogoutButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }

    @Then("^I should see 'Add' button is displayed$")
    public void iShouldSeeAddButtonIsDisplayed() throws Throwable {
        boolean isDisplayed = driver.findElement(By.xpath(".//*[@id='bAdd']")).isDisplayed();
        Assert.assertThat("Add button displayed", isDisplayed, is(equalTo(true)));
    }


//        @When("^I enter username and password$")
//        public void testLoginWithValidUserAndValidPassword() throws Exception {
//            loginPage = LoginPage.getInstance(driver);
//            loginPage.loginWithUsernameAndPassword("Luke", "Skywalker");
//            //  Assert.assertTrue(employeePage.isLoaded(), "Verify user is in Employee page!");
//            Thread.sleep(5000);
//            driver.quit();
//        }

}
