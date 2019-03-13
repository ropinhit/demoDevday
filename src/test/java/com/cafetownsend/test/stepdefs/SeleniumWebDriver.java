package com.cafetownsend.test.stepdefs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by hkpa on 5/21/2018
 */
public class SeleniumWebDriver {

    public WebDriver driver;
    public String expectedEmail = "anhdang@gmail.com";
    public String actualTitle = "";
    String VARIABLE = "123";

    public void initDriver() {
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\attdang\\Downloads\\Software\\geckodriver-v0.20.1-win64\\geckodriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\attdang\\Downloads\\Software\\chromedriver_win32\\chromedriver.exe");
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    public void exitDriver() {
        // declaration and instantiation of objects/variables
        driver.close(); // close Firefox

        // exit the program explicitly
        System.exit(0);
    }

    public void openPage() {
        String baseUrl = "https://www.google.com/en";
        String expectedTitle = "Google";
        String actualTitle = "";

        // launch browser and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        actualTitle = driver.getTitle();

        /*          * compare the actual title of the page with the expected one and print          * the result as "Passed" or "Failed"          */

        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
    }

    public void signIn() throws InterruptedException {
        WebElement signinBtn = driver.findElement(By.xpath("//a[contains(text(),'Sign in')]"));
        signinBtn.click();

        WebElement idForm = driver.findElement(By.xpath("//div[contains(text(),'Email or phone')]//preceding::input"));
        idForm.clear();
        idForm.sendKeys(expectedEmail);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(text(),'Next') ]")).click();

        WebElement passForm = driver.findElement(By.xpath("//input[@aria-label='Enter your password']"));
        passForm.clear();
        passForm.sendKeys("huong5testing92017");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(text(),'Next') ]")).click();

    }

    public void goToGmail() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Gmail')]")).click();
        Thread.sleep(1000);

    }

    public void verifyEmail() {
        driver.findElement(By.xpath("//a[contains(@title,'" + expectedEmail + "')]")).click();

        String actualEmail;
        WebElement emailAddress = driver.findElement(By.xpath("//div[@class='gb_Ib']"));
        actualEmail = emailAddress.getText();

        Assert.assertEquals(expectedEmail, actualEmail);
    }

    public void logOut() {
        driver.findElement(By.xpath("//a[text()='Sign out']")).click();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void openPage(String url) {
        // launch browser and direct it to the Base URL
        driver.get(url);
    }

    public void verifyTitle(String expectedTitle) {
        actualTitle = driver.getTitle();
        Assert.assertThat(actualTitle, is(equalTo(expectedTitle)));
    }

    public void openAccount() {
//        driver.findElement(By.xpath(".//div[@class='footer']//a[contains(@title,'My Account')]")).click();
        driver.findElement(By.partialLinkText("My Account")).click();
    }

    public void clickCreateAccountBtn(){
        driver.findElement(By.xpath(".//a[@title='Create an Account']")).click();
    }

    public void backToPageBefore(){
        driver.navigate().back();
    }
    public void verifyURL(String url){
        Assert.assertThat(url,is(equalTo(driver.getCurrentUrl())));
    }
    public void forwardToPage(){
        driver.navigate().forward();
    }
    public void test01() {
        openPage("http://live.guru99.com/");
        verifyTitle("Home page");
        openAccount();
        clickCreateAccountBtn();//move to page create acc
        backToPageBefore();
        verifyURL("http://live.guru99.com/index.php/customer/account/login/");
        forwardToPage();
        verifyURL("http://live.guru99.com/index.php/customer/account/create/");
    }


    public static void main(String arg[]) throws InterruptedException {
        SeleniumWebDriver setup = new SeleniumWebDriver();
        setup.initDriver();
        setup.openPage();
        setup.signIn();
        setup.goToGmail();
        setup.verifyEmail();
        setup.logOut();
        //setup.test01();
        setup.exitDriver();
    }
}
