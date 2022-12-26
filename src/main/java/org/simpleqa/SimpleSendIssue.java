package org.simpleqa;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.simpleqa.exception.SendFailedException;
import org.simpleqa.info.BotProperties;

@Slf4j
public final class SimpleSendIssue {
    private static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/Users/a1/Desktop/chromedriver";

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

        //로그인 탭 클릭
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div/div/div[2]/a"));
        element.click();

        //로그인 이메일
        element = driver.findElement(By.xpath("//*[@id=\"login_field\"]"));
        element.sendKeys("issuebot07@gmail.com");

        //비밀번호
        element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys("nswon0729@");

        //버튼 클릭
        element = driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[11]"));
        element.submit();
        Thread.sleep(5000);

        //이슈 탭 클릭
        element = driver.findElement(By.xpath("//*[@id=\"issues-tab\"]"));
        element.click();

        //이슈 등록 버튼 클릭
        element = driver.findElement(By.xpath("//*[@id=\"repo-content-turbo-frame\"]/div/div[1]/div[2]/details/summary"));
        element.click();

        //제목 입력
        element = driver.findElement(By.xpath("//*[@id=\"issue_title\"]"));
        Thread.sleep(500);

        element.sendKeys(("작성자 : " + message.getNickname() + ", 제목 : " + message.getTitle()));

        //내용 입력
        element = driver.findElement(By.xpath("//*[@id=\"issue_body\"]"));
        element.sendKeys(message.getContent());

        //생성 클릭
        element = driver.findElement(By.xpath("//*[@id=\"new_issue\"]/div/div/div[1]/div/div[1]/div/div[2]/button"));
        element.submit();

        Thread.sleep(10000);
    }

    private static WebDriver initWebDriver() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_PATH);
        return new ChromeDriver();
    }
}
