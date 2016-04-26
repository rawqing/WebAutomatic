package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rawq.autoweb.actions.DriverAction;

public class SeleniumTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new DriverAction().getDriver();
		
		driver.get("http://www.jd.com/");
		WebElement el01 = driver.findElement(By.cssSelector("#categorys-2014 div.dd-inner")).findElement(By.linkText("家用电器"));
		System.out.println(el01.getText());
		System.out.println(el01.getSize());
		System.out.println(el01);
		
		WebElement el02 = By.linkText("家用电器").findElement(el01);
		el02.click();
		
		Thread.sleep(5000);
		driver.quit();
	}

}
