package com.cafetownsend.test.stepdefs;

import com.google.common.base.Verify;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JenkinsStepdefs {
    WebDriver driver = Hooks.driver;

    @Given("^I go the Jenkins page$")
    public void iGoTheJenkinsPage() throws Throwable {
        driver.get("http://deerbergbuilder/jenkins");
    }

    @And("^I login with username '(.+)' and password$")
    public void iLoginWithUserAndPassword(String userName) throws Throwable {
        driver.findElement(By.id("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys("Mgm4@nhd");
    }

    @And("^I click '(.*)' button$")
    public void iClickLogInButton(String btn) throws Throwable {
        // driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath(".//button[text() ='" + btn + "']")).click();
    }

    @When("^I click on the '(.*)' tab$")
    public void iClickOnTheTab(String tabName) throws Throwable {
        driver.findElement(By.linkText(tabName)).click();
    }

    @And("^I open the link '(.*)'$")
    public void iOpenTheLink(String link) throws Throwable {
        driver.findElement(By.linkText(link)).click();
    }

    @And("^I enter the version '(.*)'$")
    public void iEnterTheVersionVersion(String version) throws Throwable {
        driver.findElement(By.xpath(".//div[@name='parameter']//input[@name='value']")).sendKeys(version);
        Thread.sleep(3000);
    }

    @And("^I enter the versionBoFrontend '(.*)'$")
    public void iEnterTheVersionBoFrontendSNAPSHOT(String version) throws Throwable {
        driver.findElement(By.xpath(".//div[@name='parameter']//input[@value='VersionBoFrontend']/following-sibling::input")).sendKeys(version);
        Thread.sleep(3000);
    }


    @Then("^I should see Katalog icon$")
    public void checkIcon() {
        try {
            List<WebElement> no = driver.findElements(By.tagName("a"));
            int nooflinks = no.size();
            System.out.println(nooflinks);
            for (WebElement pagelink : no) {
                String linktext = pagelink.getText();
                String link = pagelink.getAttribute("href");
                System.out.println("<--" + linktext + " -->");
                System.out.println(link);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @When("^I go to the '(.+)' page$")
    public void iGoToTheHttpsStagingDeerbergDeDePage(String url) throws Throwable {
        driver.get(url);
        // driver.get(url+"/catalog/fullTextSearch?queryString=");
//        //ch
//        WebElement eCh = driver.findElement(By.xpath("/html/body/div[3]/footer/div[5]/div/div[2]/a[2]/img"));
//
//        //at /html/body/div[3]/footer/div[5]/div/div[2]/a[2]/img
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eCh);
//        eCh.click();

        //##########ch
        // driver.get("https://www.deerberg.ch/ch/?CHECKING_DOMAIN_BY_IP=false");

        //#at
        //driver.get("https://www.deerberg.at/at/?CHECKING_DOMAIN_BY_IP=false");
    }

    @And("^I click all link from menu$")
    public void iGetAllLinkFromMenu() throws Throwable {
        WebElement menuE = driver.findElement(By.id("nav"));
        try {
            List<WebElement> no = menuE.findElements(By.tagName("a"));
            int noOfLinks = no.size();
            System.out.println(noOfLinks);

            List<String> allLinks = new ArrayList<String>();
            for (WebElement linkE : no) {
                allLinks.add(linkE.getAttribute("href"));
            }
            int count = 1;
            for (String perLink : allLinks) {
                if (perLink != null) {
                    if (!perLink.isEmpty()) {
                        if (perLink.contains("blog") || perLink.contains("/mode/") || perLink.contains("/flomax") || perLink.contains("/service/"))
                            continue;
                        System.out.println(perLink);
                        count++;
                        driver.get(perLink);
                        verifyPageEmpty();

                        int totalPages = getTotalPage();
                        if (totalPages == 1) continue;
                        else {
                            if (totalPages == 2) {
                                enterNumberOfPage(2);
                                verifyPageEmpty();
                            } else {
                                //Verify random page
                                int randomPageNumber = getRandomNumber(totalPages);
                                enterNumberOfPage(randomPageNumber);
                                verifyPageEmpty();

                                //Verify the last page
                                enterNumberOfPage(totalPages);
                                verifyPageEmpty();
                            }
                        }
                    }
                }
            }
            System.out.println(count);

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void backupFunctions() {
        List<WebElement> no = driver.findElements(By.tagName("a"));
        int nooflinks = no.size();
        System.out.println(nooflinks);
        for (WebElement pagelink : no) {
            String linktext = pagelink.getText();
            String link = pagelink.getAttribute("href");
                System.out.println("<--" + linktext + " -->");
                System.out.println(link);
        }
//            for (WebElement plink : no) {
//                String link = plink.getAttribute("href");
//                if (link != null) {
//                    System.out.println(link);
//                    System.out.println(plink.getText());
//                    plink.click();
//                    WebElement entryProductE = driver.findElement(By.id("entry-inside-id"));
//                    List<WebElement> productlistE = entryProductE.findElements(By.xpath("./div[contains(@class,'product_container')]"));
//                    System.out.println(productlistE.size());
//                }
//            }
    }

    @When("^I open the link and next link '(.*)'$")
    public void iOpenTheLinkAndNextLink(String url) throws Throwable {
        driver.get(url);
        int total = getTotalPage();
//        List<WebElement> nextE = driver.findElements(By.id("pagination-next-btn-img"));
//        int page = 0;
//        if (nextE.size() > 0) {
//            page++;
//            System.out.println(" >> " + page);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextE.get(0));
//            Thread.sleep(500);
//            nextE.get(0).click();
//            verifyPageEmpty();
//        }

        enterNumberOfPage(getTotalPage());
        verifyPageEmpty();
    }

    public void verifyPageEmpty() {
        WebElement entryProductE = driver.findElement(By.id("entry-inside-id"));
        List<WebElement> productlistE = entryProductE.findElements(By.xpath("./div[contains(@class,'product_container')]"));
        int size = productlistE.size();
        System.out.println("size: " + productlistE.size());
        System.out.println("link: " + driver.getCurrentUrl());
        Assert.assertTrue("page is empty", size > 0);
    }

    @When("^I check number of page '(.*)'$")
    public void iCheckNumberOfPage(String url) throws Throwable {
        driver.get(url);

        backupFunctions();
//        int totalPage = getTotalPage();
//        int randomNO = 1;
//        if (totalPage > 2) {
//            randomNO = getRandomNumber(totalPage);
//            System.out.println(" " + randomNO);
//            enterNumberOfPage(randomNO);
//            verifyPageEmpty();
//        }
    }


    public int getRandomNumber(int totalPage) {
        int randomNO = 1;
        Random r = new Random();
        randomNO = r.nextInt((totalPage - 2) + 1) + 2;
        return randomNO;
    }

    public int getTotalPage() {
        WebElement e = driver.findElement(By.className("pagination-total-page"));
        e.getText();
        return Integer.parseInt(e.getText());
    }

    public void enterNumberOfPage(int pageNumber) throws InterruptedException {
        WebElement currentPageTextBoxEl = driver.findElement(By.id("currentPageTxt"));
        currentPageTextBoxEl.clear();
        currentPageTextBoxEl.sendKeys(String.valueOf(pageNumber));
        currentPageTextBoxEl.sendKeys(Keys.ENTER);
        Thread.sleep(500);
    }
}
