import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTvNetTest {
    private final By LOCATOR_BY_ID = By.id("elementId");    // privatnoe/ konstanta peremennaja nazvanie tolko v takom vide.GODITJSA DLJA UNIKALNIH ELLEMENTOV
    private final By LOCATOR_BY_NAME = By.name("elementName"); // vtoroe po prioritetu. GODITJSA DLJA UNIKALNIH ELLEMENTOV
    private final By LOCATOR_BY_TAGMANE = By.tagName("nameOfTag");
    private final By LOCATOR_BY_CLASS = By.className("elementClassName"); // ne sovetujut ispolzovatj/ dlja raboti so mnozestvom odinakovih ellementov. iskatj po Xpath
    private final By LOCATOR_BY_XPATH = By.xpath(".//[contains(@class, 'list-article__meta-info flex')];"); // po xpathu, v "" vstavljatj konkretnij adres = locator

    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode = 'primary']");

    @Test
    public void firstTest(){
        //WebDriver = browser window
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe"); // system eto object
        WebDriver browserWindow = new ChromeDriver();    // sozdatj objekt gde webdriver eto peremennaja
        browserWindow.manage().window().maximize();     // otkritoe okno rashlopnutj na polnij ekran
        browserWindow.get("http://tvnet.lv");     // peremennaja + funkcija // i kogda rashlopnuli - togda tolko pere6li na saj

        // Thread.sleep(); - tak delatj nelzja!!!!!!!!!!!!!!!!!

      //  WebDriverWait wait = new WebDriverWait(browserWindow, 10); // objekt kotorij pokazivaet tolko ozidanie. ozidanie ellementa 10 sekund.
     //  WebDriverWait mailWait = new WebDriverWait(browserWindow, 60,2000); ozidatj po4tu. 60 sekund 2000 millisekund tak 4asto obnovljatj

       // wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN)); // ili clicable ili visible

       // WebElement acceptBtn = browserWindow.findElement(ACCEPT_COOKIES_BTN); // sozdali peremennuju i na6li element po xpath
       // acceptBtn.click(); // dali komandu nazatj na knopku

        // comment to GIT  bn
        //ghjhjm


      browserWindow.findElement(ACCEPT_COOKIES_BTN).click();  // rekomendovano ispolzovatj etot variant. on koro4e i ne sozvaet dopolnitelnie peremennie,
    }
}
