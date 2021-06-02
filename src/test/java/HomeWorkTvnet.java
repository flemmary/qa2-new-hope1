import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeWorkTvnet {
    private final By ACCEPT_COOKIES = By.xpath(".//button[@mode = 'primary']");
    private final By ITEM_HEADLINE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By COMMENT_IN_FIRST_ITEM = By.xpath(".//img[@src = '/v5/img/icons/comment-v2.svg']");
    private final By ALL_COMMENTS_ON_MAIN_PAGE = By.xpath(".//span[@class = 'list-article__comment section-font-color']");
    private final By LOGO = By.xpath(".//img[@alt = 'Postimees']");
    private final By LANGUAGE_SWITCH = By.xpath(".//a[@class = 'menu-item']");


    private final By HOME_PAGE_ARTICLE = By.tagName("article");
    private final By HOME_PAGE_TITLE = By.xpath(".//span[@class = 'list-article__headline']");
    private final By HOME_PAGE_COMMENTS_COUNT = By.xpath(".//span[@class = 'list-article__comment section-font-color']");

    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[@class= 'article-headline']");
    private final By ARTICLE_PAGE_COMMENTS_COUNT = By.xpath(".//a[contains(@class, 'item--comments')]");

    private WebDriver driver;

    private final Logger LOG = LogManager.getLogger(HomeWorkTvnet.class);

    @Test
    public void tvNetTestFirst() {
        LOG.info("Test about TVnet articles and comments");

        LOG.info("Browser opening");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOG.info("Opening TVnet homepage");
        driver.get("http://tvnet.lv");

        LOG.info("Waiting for Accept cookies btn");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES));

        LOG.info("Accepting Cookies");
        driver.findElement(ACCEPT_COOKIES).click();

        LOG.info("Scrolling up homepage");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,500)");

        LOG.info("First Item headline printing");
        WebElement firstItem = driver.findElement(ITEM_HEADLINE);
        System.out.println("First item name: " + firstItem.getText());

        LOG.info("First Item Comments page opening");
        firstItem.click();
        driver.findElement(COMMENT_IN_FIRST_ITEM).click();

        LOG.info("Opening TVnet homepage");
        driver.get("http://tvnet.lv");

        LOG.info("Printing language switch btn text");
        List<WebElement> menus = driver.findElements(LANGUAGE_SWITCH);
        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getText().equals("RUS")) {
                System.out.println(menus.get(i).getText());
            }
        }

        //----------------------zadanie 4-------------------
        LOG.info("Printing all homepage articles names without comments count in brackets");
        List<WebElement> allItems = driver.findElements(ITEM_HEADLINE);
        for (int i = 0; i < allItems.size(); i++) {
            String withComments = allItems.get(i).getText();
            String withOutComments = withComments.replaceAll("\\(\\d+\\)$", "").trim();
            System.out.println(withOutComments);
        }

        //----------------------zadanie 5-------------------
        LOG.info("Creating list of all headlines and comments near it");
        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        for (WebElement we : articles) {
            String articleName = we.findElement(HOME_PAGE_TITLE).getText();
            String articleNameClean = articleName.replaceAll("\\(\\d+\\)$", "").trim();

            int homePageCommentsCount = 0;
            if (!we.findElements(HOME_PAGE_COMMENTS_COUNT).isEmpty()) {
                String commentsCount = we.findElement(HOME_PAGE_COMMENTS_COUNT).getText();
                commentsCount = commentsCount.substring(1, commentsCount.length() - 1);
                homePageCommentsCount = Integer.parseInt(commentsCount);
            }
            System.out.println(articleNameClean + " : " + homePageCommentsCount);

            // ----------------------zadanie 6-------------------

            //-------------main page-------------------
            List<WebElement> articleHeadlines = driver.findElements(HOME_PAGE_ARTICLE);
            WebElement article = articleHeadlines.get(3);
            String articleHeadline = article.findElement(HOME_PAGE_TITLE).getText();
            String articleHeadlineText = articleHeadline.replaceAll("\\(\\d+\\)$", "").trim();

            int commentsCountInteger = 0;
            if (!article.findElements(HOME_PAGE_COMMENTS_COUNT).isEmpty()) {
                String commentsCountInArticle = article.findElement(HOME_PAGE_COMMENTS_COUNT).getText();
                commentsCountInArticle = commentsCountInArticle.substring(1, commentsCountInArticle.length() - 1);
                commentsCountInteger = Integer.parseInt(commentsCountInArticle);

                System.out.println(articleHeadlineText + " " + commentsCountInteger);

                LOG.info("Home page Scrolling up");
                JavascriptExecutor executorTwo = (JavascriptExecutor) driver;
                executorTwo.executeScript("window.scrollBy(0,250)");

                LOG.info("Opening article page");
                article.findElement(HOME_PAGE_TITLE).click();

                //------------  article page-------------
                LOG.info("Getting title in article page");
                String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();
                int commentsCountOnPage = 0;
                if (driver.findElement(ARTICLE_PAGE_COMMENTS_COUNT).isDisplayed()) {
                    String commentsOnPage = driver.findElement(ARTICLE_PAGE_COMMENTS_COUNT).getText();
                    commentsCountOnPage = Integer.parseInt(commentsOnPage);

                    LOG.info("Printing result of title and comments count from article page");
                    System.out.println(articlePageTitle + " " + commentsCountOnPage);
                }

                //----------------test-------------
                LOG.info("Comparing both titles and both comments count");
                Assertions.assertEquals(articleHeadlineText, articlePageTitle, "Incorrect title");
                Assertions.assertEquals(commentsCountInteger, commentsCountOnPage, "Incorrect comments count");
            }
        }
    }

    @AfterEach
    public void closeBrowser() {
        LOG.info("Close browser window");
        driver.close();
    }
}












