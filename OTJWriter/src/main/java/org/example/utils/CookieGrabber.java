package org.example.utils;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Cookie;

import java.util.Set;
import java.util.stream.Collectors;


@Getter
public class CookieGrabber {
    WebDriver driver;

    public CookieGrabber() {
        System.setProperty("webdriver.gecko.driver", "/Users/danperkins/Downloads/geckodriver");

        driver = new FirefoxDriver();
        driver.get("https://www.smartassessor.co.uk");
    }

    public String getCookie() {
        Set<Cookie> cookies = driver.manage().getCookies();

        return cookies.stream().map(cookie -> String.format("%s=%s; ", cookie.getName(), cookie.getValue())).collect(Collectors.joining());
    }

    public void shutdown() {
        driver.quit();
    }
}
