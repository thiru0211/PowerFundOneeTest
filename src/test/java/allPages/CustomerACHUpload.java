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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerACHUpload extends Locators {
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
	public void ACHUploadClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");

		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(CustomerBtn)).click();
		driver.findElement(By.xpath(ACHUploadBtn)).click();
	}


	@Test(priority = 1)
	public void TC01() throws InterruptedException {
		ACHUploadClick();
		driver.findElement(By.xpath(ACHUploadBtn)).click();
		driver.findElement(By.xpath(ACHSubBtn)).click();
		ele1=driver.findElement(By.xpath(ACHManMsgChk));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shown");
		}
	}
	
	@Test(priority = 2)
	public void TC02() throws InterruptedException, AWTException {
		ACHUploadClick();
		driver.findElement(By.xpath(ACHUploadFileBtn)).click();
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
		driver.findElement(By.xpath(ACHSubBtn)).click();
	}
	
	@Test(priority = 3)
	public void TC03() throws InterruptedException, AWTException {
		ACHUploadClick();
		driver.findElement(By.xpath(ACHUploadDateBtn)).click();
	}
	
	@Test(priority = 4)
	public void TC04() throws InterruptedException, AWTException {
		ACHUploadClick();
		driver.findElement(By.xpath(ACHUpldSampFilBtn)).click();
	}
	
	@Test(priority = 5)
	public void TC05() throws InterruptedException, AWTException {
		ACHUploadClick();
		driver.findElement(By.xpath(ACHFrmStupBtn)).click();
	}
	
	@Test(priority = 6)
	public void TC06() throws InterruptedException, AWTException {
		ACHUploadClick();
		String ACHInstlrDD=PropertyFileReader.propertymap.get("ACHInstlrDD");
		ele1=driver.findElement(By.id(ACHUpldInstlrDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(ACHInstlrDD);
		driver.findElement(By.xpath(ACHUploadFileBtn)).click();
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
		driver.findElement(By.xpath(ACHSubBtn)).click();
	}
	
	@Test(priority = 7)
	public void TC07() throws InterruptedException, AWTException {
		ACHUploadClick();
		driver.findElement(By.xpath(CusListBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(CusLisActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(CusLisExclUpldBtn)).click();
		driver.findElement(By.xpath(CusLisExclUplSumBtn)).click();
		ele1=driver.findElement(By.xpath(CusLisExclMandMsg));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shown");
		}
	}
	
	@Test(priority = 8)
	public void TC08() throws InterruptedException, AWTException {
		TC07();
		driver.findElement(By.xpath(CusListExclUpldFilUpld)).click();
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
		driver.findElement(By.xpath(ACHSubBtn)).click();
	}
	

	@Test(priority =9 )
	public void TC09() throws InterruptedException, AWTException {
		TC07();
		driver.findElement(By.xpath(CusListExclUpldDateBtn)).click();
	}
	
	@Test(priority = 10)
	public void TC11() throws InterruptedException, AWTException {
		TC07();
		String ACHInstlrDD=PropertyFileReader.propertymap.get("ACHInstlrDD");
		ele1=driver.findElement(By.id(ACHUpldInstlrDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(ACHInstlrDD);
		driver.findElement(By.xpath(CusListExclUpldFilUpld)).click();
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
		driver.findElement(By.xpath(ACHSubBtn)).click();
	}
	}
	
	
	

