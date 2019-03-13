import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AccountStepdefs {
    WebDriver driver;

    @When("^I go to '(.*)' page$")
    public void iGoToGuruPage(String url ) throws Throwable {
         driver = new FirefoxDriver();
    }
}
