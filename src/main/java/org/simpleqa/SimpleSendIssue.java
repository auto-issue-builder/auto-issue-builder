package org.simpleqa;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.simpleqa.exception.SendFailedException;

@Slf4j
public final class SimpleSendIssue {
    private static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    private static final WebDriver driver = initWebDriver();

    public static void send(IssueMessage message) {
        try {
            crawling(message);
        } catch (InterruptedException e) {
            throw new SendFailedException(e.getMessage());
        }
    }

    private static void crawling(IssueMessage message) throws InterruptedException {
        driver.get(message.getGithubRepoPath());
        WebElement element = driver.findElement(By.xpath("//*[@id=\"login_field\"]"));
        element.sendKeys(BotProperties.BOT_EMAIL);

        element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys(BotProperties.BOT_PASSWORD);

        element = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[11]"));
        element.submit();
        Thread.sleep(5000);

        element = driver.findElement(By.xpath("//*[@id=\"issues-tab\"]"));
        element.click();

        element = driver.findElement(By.xpath("//*[@id=\"repo-content-turbo-frame\"]/div/div[2]/div[2]/a"));
        element.click();

        element = driver.findElement(By.xpath("//*[@id=\"issue_title\"]"));
        Thread.sleep(500);

        element.sendKeys(("작성자 : " + message.getNickname() + ", 제목 : " + message.getTitle()));

        element = driver.findElement(By.xpath("//*[@id=\"issue_body\"]"));
        element.sendKeys(message.getContent());

        element = driver.findElement(By.xpath("//*[@id=\"new_issue\"]/div/div/div[1]/div/div[1]/div/div[2]/button"));
        element.submit();

        Thread.sleep(10000);
    }

    private static WebDriver initWebDriver() {
        System.setProperty(WEB_DRIVER_KEY, WebDriverProperties.WEB_DRIVER_PATH);
        return new ChromeDriver();
    }
}
