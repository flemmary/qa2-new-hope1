import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiTestMy {
    private final By ACCEPT_COOKIES = By.xpath(".//button[@mode = 'primary']"); // lokatori
    private final By ALL_HEADERS = By.tagName("article");
    private final By MAIN_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By MAIN_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By TITLE_ON_ARTICLE_PAGE = By.xpath(".//h1[contains(@class,'text-size-md-30')]");
    private final By COMMENTS_TO_CLICK = By.xpath(".//a[contains(@class, 'text-size-19')]");
    private final By REGISTERED = By.xpath(".//li[@class ='as-link is-active show-reg']");
    private final By ANONIM = By.xpath(".//li[@class ='as-link show-anon']");
    private final By TITLE_IN_COMMENTS = By.xpath(".//h1[@class = 'article-title']");
    private final By COUNT_COMMENTS = By.xpath(".//span[@class = 'type-cnt']");

    private final Logger LOG = LogManager.getLogger(DelfiTestMy.class);

    @Test
    public void titleAndComments() {
        LOG.info("Browser settings");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        LOG.info("Opening browser");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOG.info("Opening Homepage");
        driver.get("http://delfi.lv");

        LOG.info("Waiting for Accept cookies btn");
        WebDriverWait wait = new WebDriverWait(driver, 10); //WebDriverWait  tip dannih
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES));

        LOG.info("Accept cookies");
        driver.findElement(ACCEPT_COOKIES).click();

        LOG.info("Getting title and comments count On main page");
        List<WebElement> titles = driver.findElements(ALL_HEADERS); //List<WebElement> - eto vid peremennoj
        WebElement articleMainPage = titles.get(0);
        String mainPageArticle = articleMainPage.findElement(MAIN_PAGE_TITLE).getText().trim();
        System.out.println(mainPageArticle);

        int mainPageCommentsInteger = 0;
        if (!articleMainPage.findElements(MAIN_PAGE_COMMENTS).isEmpty()) {
            String commentsMainPage = articleMainPage.findElement(MAIN_PAGE_COMMENTS).getText();
            commentsMainPage = commentsMainPage.substring(1, commentsMainPage.length() - 1);
            mainPageCommentsInteger = Integer.parseInt(commentsMainPage);
            System.out.println(mainPageCommentsInteger);
        }

        LOG.info("Home page Scrolling up");
        JavascriptExecutor executorTwo = (JavascriptExecutor) driver;
        executorTwo.executeScript("window.scrollBy(0,750)");

        LOG.info("Opening article page");
        articleMainPage.findElement(MAIN_PAGE_TITLE).click();

        LOG.info("Getting title and comments count On article page");
        String titleOnArticlePage = driver.findElement(TITLE_ON_ARTICLE_PAGE).getText().trim();
        System.out.println(titleOnArticlePage);
        int commentsOnArticlePage = 0;
        String titleOnComments = "";
        int regComCount = 0;
        int anonimComCount = 0;
        if (!driver.findElements(COMMENTS_TO_CLICK).isEmpty()) {
            String commentsArticlePage = driver.findElement(COMMENTS_TO_CLICK).getText();
            commentsArticlePage = commentsArticlePage.substring(1, commentsArticlePage.length() - 1);
            commentsOnArticlePage = Integer.parseInt(commentsArticlePage);
            System.out.println(commentsOnArticlePage);

            LOG.info("Opening comments page");
            driver.findElement(COMMENTS_TO_CLICK).click();

            titleOnComments = driver.findElement(TITLE_IN_COMMENTS).getText().trim();
            System.out.println(titleOnComments);
            WebElement regComm = driver.findElement(REGISTERED);
            String regCommentsCount = regComm.findElement(COUNT_COMMENTS).getText();
            regCommentsCount = regCommentsCount.substring(1, regCommentsCount.length() - 1);
            regComCount = Integer.parseInt(regCommentsCount);
            System.out.println(regComCount);
            WebElement anonimComm = driver.findElement(ANONIM);
            String anonimCommentsCount = anonimComm.findElement(COUNT_COMMENTS).getText();
            anonimCommentsCount = anonimCommentsCount.substring(1, anonimCommentsCount.length() - 1);
            anonimComCount = Integer.parseInt(anonimCommentsCount);
            System.out.println(anonimComCount);
            System.out.println(anonimComCount + regComCount);
        }

        Assertions.assertEquals(mainPageArticle, titleOnArticlePage, "Wrong title name1");
        Assertions.assertEquals(titleOnArticlePage, titleOnComments, "Wrong title name2");
        Assertions.assertEquals(mainPageCommentsInteger, commentsOnArticlePage, "Wrong comments count1");
        Assertions.assertEquals(commentsOnArticlePage, anonimComCount + regComCount, "Wrong comments count2");


    }
}


//String mainPageArticleClean = mainPageArticle.replaceAll("\\(\\d+\\)$", "").trim();