package allPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BankSetup extends Locators {

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
	public void BankSetupClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(BankSetupBtn)).click();
	}

	@Test
	public void AuthorizedNetClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AuthorizedNetClick)).click();
	}

	@Test
	public void IPWhitelist() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(IPWhitelistBtn)).click();		
	}

	@Test
	public void UserLog() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(UsrLog)).click();		
	}

	@Test
	public void EventLog() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(EventLogBtn)).click();		
	}

	@Test
	public void ExceptionLog() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(ExceptionLogBtn)).click();		
	}

	@Test(description = "Ok",priority = 1)
	public void TC01() throws InterruptedException {
		BankSetupClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(SaveBtnBankSetup)).click();
		WebElement ele1 = driver.findElement(By.xpath(WarningMsg));
		Thread.sleep(2000);
		if(ele1.isDisplayed())
		{
			System.out.println("Warning message is displayed");
		}
		else {
			System.out.println("Warning message is not displayed");
		}
	}
	@Test(description = "Ok",priority = 2)
	public void TC02() throws InterruptedException, AWTException {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id(ClrBtn)).click();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		if(ele1.isSelected()){
			System.out.println("Text box is cleared");
		}
		else {
			System.out.println("Text box is not cleared");
		}
	}

	@Test(description = "Need to add disable button",priority = 3)
	public void TC03() throws InterruptedException, AWTException {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		String location=PropertyFileReader.propertymap.get("location");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		WebElement ele3 = driver.findElement(By.id(DropDown));
		Select sel=new Select(ele3);
		sel.selectByVisibleText("Testing Account");
		driver.findElement(By.name(Location)).sendKeys(location);
		//Thread.sleep(1000);
		//driver.findElement(By.name("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div[2]/form/div/div[1]/div[5]/div[2]/div/span/input")).click();
	}

	@Test(description ="Ok",priority = 4)
	public void TC04() throws InterruptedException, AWTException {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.xpath(AddAccntBtn)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(SaveAccntBtn)).click();
		ele3=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[4]/div/div[2]/div/div[2]/div/div/div[1]/p"));
		if(!ele3.isDisplayed()) {
			System.out.println("Warning message is showing");
		}
		else {
			System.out.println("waring messange is not shown");
		}
	}

	@Test(priority = 5)
	public void TC05() throws InterruptedException, AWTException {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		String Accntgrpname=PropertyFileReader.propertymap.get("Accntgrpname");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.xpath(AddAccntBtn)).click();
		Thread.sleep(1000);
		driver.findElement(By.name(AccntGrpName)).sendKeys(Accntgrpname);
		driver.findElement(By.xpath(SaveAccntBtn)).click();
		Thread.sleep(2000);
		ele3=driver.findElement(By.name(DropDown));
		Select select=new Select(ele3);
		select.selectByVisibleText(Accntgrpname);	
	}

	@Test(priority = 6)
	public void TC06() throws InterruptedException, AWTException {
		TC05();
		Thread.sleep(5000);
		driver.findElement(By.xpath(AccntGrpEditBtn)).click();
	}

	@Test(priority = 7)
	public void TC07() throws InterruptedException, Exception {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		String location=PropertyFileReader.propertymap.get("location");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Thread.sleep(1000);
		WebElement ele3 = driver.findElement(By.id(DropDown));
		Select sel=new Select(ele3);
		sel.selectByVisibleText("Testing Account");
		driver.findElement(By.name(Location)).sendKeys(location);
		Thread.sleep(2000);
		driver.findElement(By.xpath(StatusDisable)).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement ele4 = driver.findElement(By.xpath(UpdateBtn));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", ele4);
		driver.findElement(By.xpath(AuthorizeNo)).click();
		Thread.sleep(2000);
		ele4.click();	
	}

	@Test(priority = 8)
	public void TC08() throws InterruptedException, Exception {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String Accnum=PropertyFileReader.propertymap.get("Accnum");
		String location=PropertyFileReader.propertymap.get("location");
		String AuthorizeName=PropertyFileReader.propertymap.get("AuthorizeName");
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		ele1=driver.findElement(By.id(BankName));
		ele1.sendKeys(Bankname);
		ele2=driver.findElement(By.name(AccNum));
		ele2.sendKeys(Accnum);
		Thread.sleep(1000);
		ele3 = driver.findElement(By.id(DropDown));
		Select sel=new Select(ele3);
		sel.selectByVisibleText("Testing Account");
		driver.findElement(By.name(Location)).sendKeys(location);
		Thread.sleep(2000);
		driver.findElement(By.xpath(StatusDisable)).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ele4 = driver.findElement(By.xpath(UpdateBtn));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", ele4);
		driver.findElement(By.xpath(AuthorizeYes)).click();
		Thread.sleep(2000);
		ele5=driver.findElement(By.xpath("//select[@name='dropdownValuenew']"));
		ele5.click();
		Select selet=new Select(ele5);
		selet.selectByVisibleText(AuthorizeName);
		Thread.sleep(2000);
		ele4.click();		
	}

	@Test(description = "There is no back button, it only have close button",priority = 9)
	public void TC09() throws InterruptedException, Exception {
		BankSetupClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.className("ant-modal-close")).click();
	}


	@Test(priority = 10)
	public void TC10() throws InterruptedException, Exception {
		BankSetupClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath(BankAddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AccntGrpEditBtn)).click();
	}

	@Test(priority = 11)
	public void TC11() throws InterruptedException, Exception {
		BankSetupClick();
		Thread.sleep(2000);
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String ModifiedBankName=PropertyFileReader.propertymap.get("ModifiedBankName");
		driver.findElement(By.id(SearchBox)).sendKeys(Bankname);
		Thread.sleep(2000);
		driver.findElement(By.xpath(EditBttn)).click();
		Thread.sleep(2000);
		ele1 = driver.findElement(By.name(ModifiedBankname));
		ele1.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(2000);
		ele1.sendKeys(Keys.BACK_SPACE);
		ele1.sendKeys(ModifiedBankName);
		driver.findElement(By.xpath(UpdateBtn)).click();
	}

	@Test(priority = 12)
	public void TC12() throws InterruptedException, Exception {
		BankSetupClick();
		Thread.sleep(2000);
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		String ModifiedBankName=PropertyFileReader.propertymap.get("ModifiedBankName");
		driver.findElement(By.id(SearchBox)).sendKeys(Bankname);
		Thread.sleep(2000);
		driver.findElement(By.xpath(EditBttn)).click();
		Thread.sleep(2000);
		ele1 = driver.findElement(By.name(ModifiedBankname));
		ele1.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(2000);
		ele1.sendKeys(Keys.BACK_SPACE);
		ele1.sendKeys(ModifiedBankName);
		driver.findElement(By.id("reset")).click();

		boolean emptyCheck = ele1.getAttribute("value").isEmpty();
		System.out.println("Text box is cleared :" + emptyCheck);		
	}

	@Test(description = "There is no back button, it only have close button", priority = 13)
	public void TC13() throws InterruptedException, Exception {
		BankSetupClick();
		String Bankname=PropertyFileReader.propertymap.get("Bankname");
		Thread.sleep(2000);
		driver.findElement(By.id(SearchBox)).sendKeys(Bankname);
		Thread.sleep(2000);
		driver.findElement(By.xpath(EditBttn)).click();
		Thread.sleep(2000);
		driver.findElement(By.className("ant-modal-close")).click();
	}

	@Test(priority = 14)
	public void TC14() throws InterruptedException, Exception {
		AuthorizedNetClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthorizeAddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthorizeSaveBtn)).click();
		WebElement ele1 = driver.findElement(By.xpath(AuthorizeWarningMsg));
		Thread.sleep(2000);
		if(ele1.isDisplayed())
		{
			System.out.println("Warning message is displayed");
		}
		else {
			System.out.println("Warning message is not displayed");
		}	
	}

	@Test(priority = 15)
	public void TC15() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		String AuthoTKey=PropertyFileReader.propertymap.get("AuthoTKey");
		String AuthoLoginId=PropertyFileReader.propertymap.get("AuthoLoginId");
		String AuthoNotes=PropertyFileReader.propertymap.get("AuthoNotes");
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthorizeAddBtn)).click();
		driver.findElement(By.name(AuthorizeName)).sendKeys(AuthoName);
		driver.findElement(By.name(AuthorizeTKey)).sendKeys(AuthoTKey);
		driver.findElement(By.name(AuthorizeLoginID)).sendKeys(AuthoLoginId);
		driver.findElement(By.name(AuthorizeNotes)).sendKeys(AuthoNotes);
		driver.findElement(By.name(AuthoStatusRun)).click();
		driver.findElement(By.xpath(AuthoSave)).click();		
	}

	@Test(priority = 16)
	public void TC16() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		String AuthoTKey=PropertyFileReader.propertymap.get("AuthoTKey");
		String AuthoLoginId=PropertyFileReader.propertymap.get("AuthoLoginId");
		String AuthoNotes=PropertyFileReader.propertymap.get("AuthoNotes");
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthorizeAddBtn)).click();
		driver.findElement(By.name(AuthorizeName)).sendKeys(AuthoName);
		driver.findElement(By.name(AuthorizeTKey)).sendKeys(AuthoTKey);
		driver.findElement(By.name(AuthorizeLoginID)).sendKeys(AuthoLoginId);
		driver.findElement(By.name(AuthorizeNotes)).sendKeys(AuthoNotes);
		driver.findElement(By.name(AuthoStatusStop)).click();
		driver.findElement(By.xpath(AuthoSave)).click();		
	}

	@Test(priority = 17)
	public void TC17() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		String AuthoTKey=PropertyFileReader.propertymap.get("AuthoTKey");
		String AuthoLoginId=PropertyFileReader.propertymap.get("AuthoLoginId");
		String AuthoNotes=PropertyFileReader.propertymap.get("AuthoNotes");
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthorizeAddBtn)).click();
		driver.findElement(By.name(AuthorizeName)).sendKeys(AuthoName);
		driver.findElement(By.name(AuthorizeTKey)).sendKeys(AuthoTKey);
		driver.findElement(By.name(AuthorizeLoginID)).sendKeys(AuthoLoginId);
		driver.findElement(By.name(AuthorizeNotes)).sendKeys(AuthoNotes);
		driver.findElement(By.name(AuthoStatusStop)).click();
		driver.findElement(By.xpath(AuthoClr)).click();		
	}


	@Test(priority = 18)
	public void TC18() throws InterruptedException, Exception {
		AuthorizedNetClick();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(AuthorizeAddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AuthoBackBtn)).click();
	}		

	@Test(priority = 19)
	public void TC19() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		Thread.sleep(2000);
		driver.findElement(By.id(AuthoSearchBtn)).sendKeys(AuthoName);
		driver.findElement(By.xpath(AuthoEditBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AutExistBackBtn)).click();	
	}

	@Test(priority = 20)
	public void TC20() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		Thread.sleep(2000);
		driver.findElement(By.id(AuthoSearchBtn)).sendKeys(AuthoName);
		driver.findElement(By.xpath(AuthoEditBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AutExistClrBtn)).click();	
	}

	@Test(priority =21 )
	public void TC21() throws InterruptedException, Exception {
		AuthorizedNetClick();
		String AuthoName=PropertyFileReader.propertymap.get("AuthoName");
		String AuthoExistName=PropertyFileReader.propertymap.get("AuthoExistName");
		String AuthoExistNotes=PropertyFileReader.propertymap.get("AuthoExistNotes");
		Thread.sleep(2000);
		driver.findElement(By.id(AuthoSearchBtn)).sendKeys(AuthoName);
		driver.findElement(By.xpath(AuthoEditBtn)).click();
		driver.findElement(By.name(AuthorizeExistName)).sendKeys(AuthoExistName);
		driver.findElement(By.name(AuthorizeExistNotes)).sendKeys(AuthoExistNotes);
		driver.findElement(By.name(AuthoExistRun)).click();
		driver.findElement(By.xpath(AutExistUpdBtn)).click();		
	}

	@Test(priority = 22)
	public void TC22() throws InterruptedException, Exception {
		IPWhitelist();
		Thread.sleep(2000);
		String IpAllDropDown=PropertyFileReader.propertymap.get("IpAllDropDown");
		String IpAddress=PropertyFileReader.propertymap.get("IpAddress");
		ele1 = driver.findElement(By.name("status"));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(IpAllDropDown);
		driver.findElement(By.id(IpSearchBtn)).sendKeys(IpAddress);

	}

	@Test(priority = 23)
	public void TC23() throws InterruptedException, Exception {
		IPWhitelist();
		Thread.sleep(2000);
		String IpAddres=PropertyFileReader.propertymap.get("IpAddres");
		String IpResPerName=PropertyFileReader.propertymap.get("IpResPerName");
		String IpLoc=PropertyFileReader.propertymap.get("IpLoc");
		String IpNote=PropertyFileReader.propertymap.get("IpNote");
		driver.findElement(By.xpath(IpAddBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.id(IpAddress)).sendKeys(IpAddres);
		driver.findElement(By.name(IpResPerson)).sendKeys(IpResPerName);
		driver.findElement(By.name(IpLocation)).sendKeys(IpLoc);
		driver.findElement(By.name(IpNotes)).sendKeys(IpNote);
		driver.findElement(By.name(IpNotes)).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.name(IpStatusEnbl)).click();
		driver.findElement(By.xpath(IpAdressSave)).click();
	}

	@Test(priority = 24)
	public void TC24() throws InterruptedException, Exception {
		IPWhitelist();
		Thread.sleep(2000);
		String IpAddres=PropertyFileReader.propertymap.get("IpAddres");
		String IpResPerName=PropertyFileReader.propertymap.get("IpResPerName");
		String IpLoc=PropertyFileReader.propertymap.get("IpLoc");
		String IpNote=PropertyFileReader.propertymap.get("IpNote");
		driver.findElement(By.xpath(IpAddBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.id(IpAddress)).sendKeys(IpAddres);
		driver.findElement(By.name(IpResPerson)).sendKeys(IpResPerName);
		driver.findElement(By.name(IpLocation)).sendKeys(IpLoc);
		driver.findElement(By.name(IpNotes)).sendKeys(IpNote);
		driver.findElement(By.name(IpNotes)).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.name(IpStatusDSbl)).click();
		driver.findElement(By.xpath(IpAdressSave)).click();
	}


	@Test(priority = 25)
	public void TC25() throws InterruptedException, Exception {
		IPWhitelist();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IpAddBtn)).click();
		driver.findElement(By.xpath(IpCloseBtn)).click();
		driver.findElement(By.xpath(IpAddBtn)).click();
	}

	@Test(priority = 26)
	public void TC26() throws InterruptedException, Exception {
		IPWhitelist();
		Thread.sleep(2000);
		String IpAddres=PropertyFileReader.propertymap.get("IpAddres");
		String IpResPerName=PropertyFileReader.propertymap.get("IpResPerName");
		String IpLoc=PropertyFileReader.propertymap.get("IpLoc");
		String IpNote=PropertyFileReader.propertymap.get("IpNote");
		driver.findElement(By.xpath(IpAddBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.id(IpAddress)).sendKeys(IpAddres);
		driver.findElement(By.name(IpResPerson)).sendKeys(IpResPerName);
		driver.findElement(By.name(IpLocation)).sendKeys(IpLoc);
		driver.findElement(By.name(IpNotes)).sendKeys(IpNote);
		driver.findElement(By.name(IpNotes)).sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.name(IpStatusDSbl)).click();
		driver.findElement(By.xpath(IpAdressClr)).click();
	}

	@Test(priority = 27)
	public void TC27() throws InterruptedException, Exception {
		UserLog();
		Thread.sleep(2000);
		driver.findElement(By.xpath(UsrLogSrch)).click();	
	}

	@Test(priority = 28)
	public void TC28() throws InterruptedException, Exception {
		UserLog();
		Thread.sleep(2000);
		driver.findElement(By.xpath(UsrLogView)).click();
	}

	@Test(priority =29 )
	public void TC29() throws InterruptedException, Exception {
		TC28();
		driver.findElement(By.xpath(UsrLogBackBtn)).click();
		driver.findElement(By.xpath(UsrLogView)).click();		
	}

	@Test(priority = 30)
	public void TC30() throws InterruptedException, Exception {
		EventLog();
		ele1=driver.findElement(By.xpath(EventLogSearchBtn));
		ele1.click();
		boolean enabled = ele1.isEnabled();
		System.out.println("Search box is clickable:" + enabled);		
	}

	@Test(priority = 31)
	public void TC31() throws InterruptedException, Exception {
		EventLog();
		driver.findElement(By.xpath(EventLogViewBtn)).click();
	}

	@Test(priority = 32)
	public void TC32() throws InterruptedException, Exception {
		TC31();
		driver.findElement(By.xpath(EventLogCloseBtn)).click();
		driver.findElement(By.xpath(EventLogViewBtn)).click();		
	}

	@Test(priority = 33)
	public void TC33() throws InterruptedException, Exception {
		ExceptionLog();	
		ele1=driver.findElement(By.xpath(ExceSearchBtn));
		ele1.click();
		boolean enabled = ele1.isEnabled();
		System.out.println("Search box is clickable:" + enabled);	
	}

}

