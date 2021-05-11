import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWorkTvnet {
    private final By ACCEPT_COOKIES = By.xpath(".//button[@mode = 'primary']");
    private final By ITEM_HEADLINE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By COMMENT_IN_FIRST_ITEM = By.xpath(".//img[@src = '/v5/img/icons/comment-v2.svg']");
    private final By ALL_COMMENTS_ON_MAIN_PAGE = By.xpath(".//span[@class = 'list-article__comment section-font-color']");
    private final By LOGO = By.xpath(".//img[@alt = 'Postimees']");
    private final By LANGUAGE_SWITCH = By.xpath(".//a[@class = 'menu-item']");

    @Test
    public void tvNetTestFirst() {

        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://tvnet.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES));
        driver.findElement(ACCEPT_COOKIES).click();


        WebElement firstItem = driver.findElement(ITEM_HEADLINE);
        System.out.println("First item name: " + firstItem.getText());
        firstItem.click();
        driver.findElement(COMMENT_IN_FIRST_ITEM).click();

        driver.get("http://tvnet.lv");

        List<WebElement> menus = driver.findElements(LANGUAGE_SWITCH);
        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getText().equals("RUS")) {
                System.out.println(menus.get(i).getText());
            }
        }


        System.out.println("Задание 4");


        List<WebElement> allItems = driver.findElements(ITEM_HEADLINE);
        for (int i = 0; i < allItems.size(); i++) {
            String withComments = allItems.get(i).getText();
            String withOutComments = withComments.replaceAll("\\(\\d+\\)$", "");
            System.out.println(withOutComments);
        }


        System.out.println("Задание 5");


        List<WebElement> itemsFindComments = driver.findElements(ITEM_HEADLINE);
        //Map<String, Integer> linkedTitleWithComments = new HashMap<>();
        for (int i = 0; i < itemsFindComments.size(); i++) {
            WebElement titleWithComments = itemsFindComments.get(i);
            int commentCounter;
            try {
                String comment = titleWithComments.findElement(ALL_COMMENTS_ON_MAIN_PAGE).getText();
                int startNumberCommentIndex = 1;
                int endNumberCommentIndex = comment.length() - 1;
                String stringComment = comment.substring(startNumberCommentIndex, endNumberCommentIndex);
                commentCounter = Integer.parseInt(stringComment);
            } catch (NoSuchElementException ex) {
                commentCounter = 0;
            }
            String withOutComments = titleWithComments.getText().replaceAll("\\(\\d+\\)$", "");
            System.out.println(withOutComments + ": " + commentCounter);
        }
        driver.quit();
    }
}








