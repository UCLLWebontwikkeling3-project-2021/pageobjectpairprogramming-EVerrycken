import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Evert
 */

public class HomePage extends Page {
    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(linkText = "My Contacts")
    private WebElement toContactsButton;

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

        loginButton.click();
    }

    private void fillOutField(String idName, String value) {
        WebElement field = driver.findElement(By.id(idName));
        field.clear();
        field.sendKeys(value);
    }

    public ContactPage toContacts(){
        toContactsButton.click();
        return PageFactory.initElements(driver, ContactPage.class);
    }
}
