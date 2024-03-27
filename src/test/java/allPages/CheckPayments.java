package allPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckPayments extends Locators {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static WebElement ele1,ele2,ele3,ele4,ele5;

	@BeforeMethod
	public void setUp() throws IOException{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("http://192.168.1.36:81/#/auth");
		File file=new File("C:\\Users\\thirumaran\\eclipse-workspace\\PowerFundOnee\\Data.properties");
		FileInputStream FIS=new FileInputStream(file);
		Properties prop=new Properties();
		prop.load(FIS);	
	}

			@AfterMethod
			public void tearDown() throws IOException, InterruptedException{
				Thread.sleep(30000);
				driver.quit();
			}

	@Test
	public void CheckPaymentsClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(CustomerBtn)).click();
		driver.findElement(By.xpath(CheckPayBtn)).click();
	}
	
	@Test(priority = 1)
	public void TC01() throws InterruptedException {
		CheckPaymentsClick();
		String CPBankDDName=PropertyFileReader.propertymap.get("CPBankDDName");
		String CPBankSrchBtn=PropertyFileReader.propertymap.get("CPBankSrchBtn");
		ele1=driver.findElement(By.xpath(CPBankDD));
		Select sel1=new Select(ele1);
		sel1.selectByVisibleText(CPBankDDName);
		driver.findElement(By.xpath(CPBankSrch)).sendKeys(CPBankSrchBtn);
	}

	@Test(priority = 2)
	public void TC02() throws InterruptedException {
		TC01();
		String CPAddInstlrDD=PropertyFileReader.propertymap.get("CPAddInstlrDD");
		String CPAddCusName=PropertyFileReader.propertymap.get("CPAddCusName");
		driver.findElement(By.xpath(CPAddBtn)).click();
		ele1=driver.findElement(By.xpath(CPAddInstDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(CPAddInstlrDD);
		driver.findElement(By.xpath(CPAddCustName)).sendKeys(CPAddCusName);
	}
	
	@Test(priority = 3)
	public void TC03() throws InterruptedException {
		TC02();
		Thread.sleep(2000);
		driver.findElement(By.xpath(CPAddSrchSugBtn)).click();
		driver.findElement(By.xpath(CPAddSrchBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(CPCusEditRobBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(CPCusEditSavBtn)).click();
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div/div/div[7]/button"));
		Thread.sleep(2000);
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shown");
		}
		ele1.click();
	}
	
	@Test(priority = 4)
	public void TC04() throws InterruptedException {
		TC02();
		driver.findElement(By.xpath(CPAddSrchBtn)).click();
		driver.findElement(By.xpath(CPCusEditBckBtn)).click();
	}
	
	@Test(priority = 5)
	public void TC05() throws InterruptedException, AWTException {
		TC03();
		String CPCusEditChkNam=PropertyFileReader.propertymap.get("CPCusEditChkNam");
		String CPCusEditChkDate=PropertyFileReader.propertymap.get("CPCusEditChkDate");
		String CPCusEditChkAmt=PropertyFileReader.propertymap.get("CPCusEditChkAmt");
		String CPCusEditNamAcc=PropertyFileReader.propertymap.get("CPCusEditNamAcc");
		String CPCusEditBnkAcc=PropertyFileReader.propertymap.get("CPCusEditBnkAcc");
		String CPCusEditBnkAccRout=PropertyFileReader.propertymap.get("CPCusEditBnkAccRout");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		driver.findElement(By.xpath(CPCusEditDocBtn)).click();
		Thread.sleep(2000);
		String FilePath="C:\\Users\\thirumaran\\Desktop\\Screenshot 2024-03-06 105214.png";
		Robot robot = new Robot();
		StringSelection selection = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
		ele1=driver.findElement(By.name(CPCusEdiChkNam));
		ele1.click();
		ele1.sendKeys(CPCusEditChkNam);
		driver.findElement(By.name(CPCusEdiChkDate)).sendKeys(CPCusEditChkDate);
		driver.findElement(By.name(CPCusEdiChkAmt)).sendKeys(CPCusEditChkAmt);
		driver.findElement(By.name(CPCusEdiNamAcc)).sendKeys(CPCusEditNamAcc);
		driver.findElement(By.name(CPCusEdiBnkAcc)).sendKeys(CPCusEditBnkAcc);
		driver.findElement(By.name(CPCusEdiBnkAccRout)).sendKeys(CPCusEditBnkAccRout);
		driver.findElement(By.xpath(CPCusEditSavBtn)).click();
		
}
}