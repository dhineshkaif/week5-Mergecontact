package com.leafff;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Merge Contacts")).click();

		driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif'][1]")).click();
		String parent = driver.getWindowHandle();
		Set<String> fromcontact = driver.getWindowHandles();
//		System.out.println(fromcontact);
		for (String need : fromcontact) {
			if (!parent.equals(need)) {
				driver.switchTo().window(need);

			}
		}

		WebElement table = driver.findElement(By.id("ext-gen156"));
		List<WebElement> from = table.findElements(By.tagName("tr"));
		WebElement txt = from.get(1);
		WebElement ss = txt.findElement(By.xpath("(//a[text()='DemoCustomer'])"));
		System.out.println(ss.getText());
//      String text = txt.getText();
		ss.click();
//		System.out.println(text);
		driver.switchTo().window(parent);
		System.out.println(driver.getCurrentUrl());
//
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> tocontact = driver.getWindowHandles();
		for (String need : tocontact) {
			if (!parent.equals(need)) {
				driver.switchTo().window(need);

			}
		}
		WebElement totable = driver.findElement(By.id("ext-gen246"));
		List<WebElement> to = totable.findElements(By.tagName("tr"));
		WebElement totxt = to.get(2);
		totxt.findElement(By.xpath("//a[text()='DemoLBCust']")).click();
		
		driver.switchTo().window(parent);
		Thread.sleep(3000);
	

		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert testleaf = driver.switchTo().alert();
		Thread.sleep(3000);
		testleaf.accept();

		Thread.sleep(5000);
		driver.close();
	}

}
