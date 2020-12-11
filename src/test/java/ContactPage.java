import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @author Evert
 */

public class ContactPage extends Page {
    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Contacts");
    }

    public void addContact(String firstName, String lastName, String date, String hour, String gsm, String email){
        submitContactForm(firstName, lastName, date, hour, gsm, email);
    }

    private void submitContactForm(String firstName, String lastName, String date, String hour, String gsm, String email) {
        fillOutField("firstName", firstName);
        fillOutField("lastName", lastName);
        fillOutField("date", date);
        fillOutField("hour", hour);
        fillOutField("gsm", gsm);
        fillOutField("email", email);

        WebElement submit = driver.findElement(By.id("addContact"));
        submit.click();
    }

    private void fillOutField(String idName, String value) {
        WebElement field = driver.findElement(By.id(idName));
        field.clear();
        field.sendKeys(value);
    }

    public int countContacts(){
        ArrayList<WebElement> contacts = (ArrayList<WebElement>)  driver.findElements(By.id("myContact"));
        return contacts.size();
    }

    public void toHome(){
        WebElement webElement = driver.findElement(By.linkText("Home"));
        webElement.click();
    }
}
