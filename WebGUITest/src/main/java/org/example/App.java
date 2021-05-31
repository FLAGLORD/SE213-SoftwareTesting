package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import javax.print.DocFlavor;
import javax.security.auth.callback.CallbackHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App
{
    public static WebDriver driverInit(String testUrl){
        // 可以通过 chrome://version 查看版本，去网站上下载相应的 chromedriver
        String dirPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",dirPath + "\\chromedriver\\91.0.4472\\chromedriver.exe");
        // 指定你电脑上 chrome 路径
        System.setProperty("webdriver.chrome.bin","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        WebDriver chromeDriver = new ChromeDriver();

        // 红字仅仅是提醒讯息，不影响使用
        chromeDriver.get(testUrl);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return chromeDriver;
    }

    public static void Case1(){
        String testUrl = "https://www.cnki.net/";
        WebDriver chromeDriver = driverInit(testUrl);

        // 输入框
        WebElement searchInput = chromeDriver.findElement(By.id("txt_SearchText"));
        searchInput.sendKeys("张三");


        // 控制多选框选项
        // 选中专利
        chromeDriver.findElement(By.id("SCOD")).findElement(By.tagName("i")).click();
        // 取消选中 学术期刊、博硕、会议、报纸、图书
        chromeDriver.findElement(By.id("CJFQ")).findElement(By.tagName("i")).click();
        chromeDriver.findElement(By.id("CDMD")).findElement(By.tagName("i")).click();
        chromeDriver.findElement(By.id("CIPD")).findElement(By.tagName("i")).click();
        chromeDriver.findElement(By.id("CCND")).findElement(By.tagName("i")).click();
        chromeDriver.findElement(By.id("BDZK")).findElement(By.tagName("i")).click();

        // 先悬浮，找到选中的下拉框
        WebElement DBFieldListBox = chromeDriver.findElement(By.className("sort-default"));
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(DBFieldListBox).perform();

        // 选中第一作者
        chromeDriver.findElement(By.id("DBFieldList")).findElement(By.xpath("./ul/li[@value='FI']")).click();


        // 搜索按钮
        WebElement searchButton = chromeDriver.findElement(By.className("search-btn"));
        searchButton.click();

        chromeDriver.close();
    }

    public static void Case2(){
        String testUrl = "https://www.cnki.net/";
        WebDriver chromeDriver = driverInit(testUrl);
        // 测试正常跳转
        chromeDriver.findElement(By.id("highSearch")).click();
        List<String> windowHandles = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
        System.out.println(chromeDriver.getCurrentUrl());

        chromeDriver.findElement(By.className("btn-search")).click();
        // 处理对话框
        chromeDriver.findElement(By.id("layui-layer1")).findElement(By.className("layui-layer-btn0")).click();

        // 表单
        chromeDriver.findElement(By.xpath("//*[@id=\"gradetxt\"]/dd[1]/div[2]/input")).sendKeys("软件测试");
        chromeDriver.findElement(By.xpath("//*[@id=\"gradetxt\"]/dd[2]/div[2]/input")).sendKeys("王月波");
        chromeDriver.findElement(By.xpath("//*[@id=\"gradetxt\"]/dd[3]/div[2]/input")).sendKeys("计算机测量与控制");
        chromeDriver.findElement(By.className("btn-search")).click();


    }
    public static void main( String[] args )
    {
        //Case1();
        Case2();
    }


}
