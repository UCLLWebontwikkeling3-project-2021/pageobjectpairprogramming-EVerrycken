import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver = new ChromeDriver();
    }

    @After
    public void clean() { driver.quit(); }

    @Test(expected = NoSuchElementException.class)
    public void test_Not_Logged_In_User_Navigates_From_Home_To_Contacts_Throws_Exception()  {
        HomePage page = new HomePage(driver);
        page.toContacts();
        assertEquals(driver.getTitle(),"GC Witbos - Error");
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Home_To_Contacts() {
        HomePage page = new HomePage(driver);
        loginUser();
        page.toContacts();
        assertEquals(driver.getTitle(),"GC Witbos - Contacts");
    }

    @Test
    public void test_Logged_In_User_Can_Navigate_From_Contacts_To_Home() {
        loginUser();
        ContactPage page = new ContactPage(driver);
        page.toHome();
        assertEquals(driver.getTitle(),"GC Witbos - Home");
    }

    @Test
    public void test_User_Without_Contacts_Shows_No_Contacts() {
        loginUserWithoutContacts();
        ContactPage page = new ContactPage(driver);
        assertEquals(page.countContacts(),0);
    }

    @Test
    public void test_User_With_Contacts_Shows_Correct_Amount_Of_Contacts()  {
        loginUser();
        ContactPage page = new ContactPage(driver);
        int originalContacts = page.countContacts();
        for (int i = 0; i < 3; i++) {
            page.addContact("Test","Je","11122020","1340","0498151515","testje@ucll.be");
        }
        int contactsNow = page.countContacts();
        assertEquals(originalContacts + 3, contactsNow);
    }

    public void loginUser(){
        HomePage page = new HomePage(driver);
        page.login("gjongen", "gjongen");
    }

    public void loginUserWithoutContacts(){
        HomePage page = new HomePage(driver);
        page.login("kbeheydt", "kbeheydt");
    }
}
