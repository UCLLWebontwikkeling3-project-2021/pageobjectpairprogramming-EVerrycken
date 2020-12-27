import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Evert
 */

public class ContactsOverviewTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    @After
    public void clean() { driver.quit(); }

    @Test(expected = NoSuchElementException.class)
    public void test_Not_Logged_In_User_Navigates_From_Home_To_Contacts_Throws_Exception()  {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.toContacts();
        assertEquals(driver.getTitle(),"Error");
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Home_To_Contacts() {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        loginUser();
        page.toContacts();
        assertEquals(driver.getTitle(),"My Contacts");
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Contacts_To_Home() {
        loginUser();
        ContactPage page = PageFactory.initElements(driver,ContactPage.class);
        page.toHome();
        assertEquals(driver.getTitle(),"Home");
    }

    @Test
    public void test_User_Without_Contacts_Shows_No_Contacts() {
        loginUserWithoutContacts();
        ContactPage page = PageFactory.initElements(driver,ContactPage.class);
        assertEquals(page.countContacts(),0);
    }

    @Test
    public void test_User_With_Contacts_Shows_Correct_Amount_Of_Contacts()  {
        loginUser();
        ContactPage page = PageFactory.initElements(driver,ContactPage.class);
        int originalContacts = page.countContacts();
        for (int i = 0; i < 3; i++) {
            page.fillOutContact("Test","Je","11122020","1340","0498151515","testje@ucll.be");
            page.submit();
        }
        int contactsNow = page.countContacts();
        assertEquals(originalContacts + 3, contactsNow);
    }

    public void loginUser(){
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.login("gjongen", "gjongen");
    }

    public void loginUserWithoutContacts(){
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.login("kbeheydt", "kbeheydt");
    }
}
