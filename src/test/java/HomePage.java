import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Evert
 */

public class HomePage extends Page {
    public HomePage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Home");
    }

    public void login(String userid, String password){
        submitLoginForm(userid,password);
    }

    private void submitLoginForm(String userid, String password) {
        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement submit = driver.findElement(By.id("login"));
        submit.click();
    }

    private void fillOutField(String idName, String value) {
        WebElement field = driver.findElement(By.id(idName));
        field.clear();
        field.sendKeys(value);
    }

    public void toContacts(){
        WebElement webElement = driver.findElement(By.linkText("Contacts"));
        webElement.click();
    }
}
