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
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import allPages.Locators;
import allPages.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewUser extends Locators {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static WebElement ele1,ele2,ele3,ele4,ele5;
	@BeforeMethod
	public void setUp() throws IOException{
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
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
	@Test(priority = 1)
	public void ViewUsersClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
	}



	@Test(priority = 2)
	public void TC01() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		String ViewUserSts=PropertyFileReader.propertymap.get("ViewUserSts");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(2000);
		ele1=driver.findElement(By.xpath(ViewUserStsDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(ViewUserSts);
		driver.findElement(By.xpath(ViewUserBtn)).click();
	}
	
	@Test(priority =3)
	public void TC02() throws InterruptedException{
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
		driver.findElement(By.xpath(AddBtn)).click();
		Thread.sleep(2000);
		ele1 = driver.findElement(By.tagName("body"));
		ele1.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		ele1.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.xpath(SaveBtn)).click();
		WebElement element = driver.findElement(By.xpath(MandatoryPopUp));
		if(element.isDisplayed()) {
			System.out.println("Page shows warning message");
		}
		else {
			System.out.println("Page doesnot any warning message");
		}
	}
	@Test(priority = 4)
	public void TC03() throws InterruptedException{
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
		driver.findElement(By.xpath(AddBtn)).click();
		Thread.sleep(2000);
		ele1=driver.findElement(By.name(FirstName));
		ele1.sendKeys(FirstName);
		Thread.sleep(2000);
		ele2 = driver.findElement(By.tagName("body"));
		ele2.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		ele2.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.xpath(ClearBtn)).click();
		ele2.sendKeys(Keys.PAGE_UP);
		Thread.sleep(2000);
		ele2.sendKeys(Keys.PAGE_UP);
		Thread.sleep(2000);
		if(ele1.getAttribute("value").isEmpty())
		{
			System.out.println("Text field is cleared");
		}
		else {
			System.out.println("Text field is not cleared");
		}
	}
	@Test(description = "Need to add back button", priority = 5)
	public void TC04() throws InterruptedException{
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
		driver.findElement(By.xpath(AddBtn)).click();
		Thread.sleep(2000);
		driver.navigate().back();
	}

	@Test(priority = 6)
	public void TC05() throws InterruptedException{
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		String Firstname=PropertyFileReader.propertymap.get("Firstname");
		String Lastname=PropertyFileReader.propertymap.get("Lastname");
		String ExtraMail=PropertyFileReader.propertymap.get("ExtraMail");
		String Userrole=PropertyFileReader.propertymap.get("Userrole");
		String VisibleTextPST=PropertyFileReader.propertymap.get("VisibleTextPST");		
		String ManLoc=PropertyFileReader.propertymap.get("ManLoc");	
		String StatusBtnEbl=PropertyFileReader.propertymap.get("StatusBtnEbl");
		String StatusBtnDbl=PropertyFileReader.propertymap.get("StatusBtnDbl");
		String AccessBtnAny=PropertyFileReader.propertymap.get("AccessBtn");
		String AccessBtnReg=PropertyFileReader.propertymap.get("AccessBtn");
		String PhoneNo=PropertyFileReader.propertymap.get("PhoneNo");
		String address1=PropertyFileReader.propertymap.get("address1");
		String address2=PropertyFileReader.propertymap.get("address2");
		String city=PropertyFileReader.propertymap.get("city");
		String zipCode=PropertyFileReader.propertymap.get("zipCode");		
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
		driver.findElement(By.xpath(AddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.name(FirstName)).sendKeys(Firstname);
		driver.findElement(By.name(LastName)).sendKeys(Lastname);
		driver.findElement(By.name(EmailField)).sendKeys(ExtraMail);
		driver.findElement(By.name(UserRole)).sendKeys(Userrole);
		ele1= driver.findElement(By.name("status"));
		Select select=new Select(ele1);
		select.selectByVisibleText(VisibleTextPST);

		ele2 = driver.findElement(By.name(ManLoc));
		if(!ele2.isSelected())
		{
			ele2.click();
		}
		else
		{
			System.out.println("Checkbox is already selected");
		}

		Thread.sleep(2000);
		ele3 = driver.findElement(By.tagName("body"));
		ele3.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		ele3 = driver.findElement(By.tagName("body"));
		ele3.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.name(PhoneNum)).sendKeys(PhoneNo);
		driver.findElement(By.name(Address1)).sendKeys(address1);
		driver.findElement(By.name(Address2)).sendKeys(address2);
		driver.findElement(By.name(City)).sendKeys(city);
		driver.findElement(By.name(ZipCode)).sendKeys(zipCode);
		driver.findElement(By.xpath(SaveBtn)).click();		
	}

	@Test(priority = 7)
	public void TC06() throws InterruptedException{
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");
		String Firstname=PropertyFileReader.propertymap.get("Firstname");
		String Lastname=PropertyFileReader.propertymap.get("Lastname");
		String ExtraMail=PropertyFileReader.propertymap.get("ExtraMail");
		String Userrole=PropertyFileReader.propertymap.get("Userrole");
		String VisibleTextIU=PropertyFileReader.propertymap.get("VisibleTextIU");		
		String ManLoc=PropertyFileReader.propertymap.get("ManLoc");	
		String StatusBtnEbl=PropertyFileReader.propertymap.get("StatusBtnEbl");
		String StatusBtnDbl=PropertyFileReader.propertymap.get("StatusBtnDbl");
		String AccessBtnAny=PropertyFileReader.propertymap.get("AccessBtn");
		String AccessBtnReg=PropertyFileReader.propertymap.get("AccessBtn");
		String PhoneNo=PropertyFileReader.propertymap.get("PhoneNo");
		String address1=PropertyFileReader.propertymap.get("address1");
		String address2=PropertyFileReader.propertymap.get("address2");
		String city=PropertyFileReader.propertymap.get("city");
		String zipCode=PropertyFileReader.propertymap.get("zipCode");		
		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(AdminBtn)).click();
		driver.findElement(By.xpath(ViewUserBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(AddBtn)).click();
		Thread.sleep(2000);
		driver.findElement(By.name(FirstName)).sendKeys(Firstname);
		driver.findElement(By.name(LastName)).sendKeys(Lastname);
		driver.findElement(By.name(EmailField)).sendKeys(ExtraMail);
		driver.findElement(By.name(UserRole)).sendKeys(Userrole);
		ele1= driver.findElement(By.name("status"));
		Select select=new Select(ele1);
		select.selectByVisibleText(VisibleTextIU);

		ele2 = driver.findElement(By.name(ManLoc));
		if(!ele2.isSelected())
		{
			ele2.click();
		}
		else
		{
			System.out.println("Checkbox is already selected");
		}

		Thread.sleep(2000);
		ele3 = driver.findElement(By.tagName("body"));
		ele3.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		ele3 = driver.findElement(By.tagName("body"));
		ele3.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.name(PhoneNum)).sendKeys(PhoneNo);
		driver.findElement(By.name(Address1)).sendKeys(address1);
		driver.findElement(By.name(Address2)).sendKeys(address2);
		driver.findElement(By.name(City)).sendKeys(city);
		driver.findElement(By.name(ZipCode)).sendKeys(zipCode);
		driver.findElement(By.xpath(SaveBtn)).click();		
	}

	@Test (priority = 8)
	public void TC08() throws InterruptedException{
		TC06();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//a[@class='btn btn-icon btn-md btn-active-color-primary mt-n2']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='menu-link px-5 bg-danger']")).click();
		String ExtraMail=PropertyFileReader.propertymap.get("ExtraMail");
		driver.findElement(By.name(Email)).sendKeys(ExtraMail);
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine();
		driver.findElement(By.name(Password)).sendKeys(inputText);
		driver.findElement(By.id(LoginBtn)).click();
	}

	@Test(description = "Need to change", priority = 9)
	public void TC12() throws InterruptedException{
		ViewUsersClick();
		String desiredContent ="vevobe6669@sfpixel.com";
		System.out.println(desiredContent);
		int columnIndex = 2;
		List<WebElement> ele1 = driver.findElements(By.xpath("//*[@id=\"kt_table_users\"]/tbody"));
		int desiredRowPosition = -1;
		for (int i = 1; i <= ele1.size(); i++) {
			System.out.println(ele1.size());
			String xpathExpression = "//table[@id=\"kt_table_users\"]/tbody/tr[" + i + "]/td[" + columnIndex + "]";
			WebElement cell = driver.findElement(By.xpath(xpathExpression));
			if (cell.getText().equals(desiredContent)) {
				String xpathArrowButtonExpression = "//table[@id='yourTableId']/tbody/tr[" + i + "]/td[last()]/div";
				System.out.println(xpathArrowButtonExpression);
				WebElement lastArrowButton = driver.findElement(By.xpath(xpathArrowButtonExpression));	
				lastArrowButton.click();
				break;

			}

		}
	}

	@Test(description = "ok",priority = 10)
	public void TC17() throws InterruptedException{
		ViewUsersClick();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"kt_table_users\"]/tbody/tr[13]/td[8]/div")).click();
		Thread.sleep(3000);
		WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div/div[4]/div[2]"));
		String status="Enabled";
		String text = ele1.getText();
		System.out.println(text);
		if(!(text==status)) {
			driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div/div[4]/div[3]/img")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@name='yes']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@title='Click here to update status']")).click();
		}
		else {
			System.out.println("Status already in diabled state");
		}	
	}

	@Test(description = "ok",priority = 11)
	public void TC19() throws InterruptedException{
		ViewUsersClick();
		WebElement element= driver.findElement(By.name("status"));
		Select select=new Select(element);
		select.selectByIndex(2);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"kt_table_users\"]/tbody/tr/td[8]/div/div/a/span")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div/div[4]/div[3]/img")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='yes']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Click here to update status']")).click();
	}

	@Test(description = "ok",priority = 12)
	public void TC20() throws InterruptedException{
		TC19();
		Thread.sleep(3000);
		WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div/div[2]/div[2]"));
		String text1 = ele1.getText();
		System.out.println(text1);
		driver.findElement(By.xpath("//a[@class='btn btn-icon btn-md btn-active-color-primary mt-n2']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='menu-link px-5 bg-danger']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name(Email)).sendKeys(text1);
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine();
		driver.findElement(By.name(Password)).sendKeys(inputText);
		Thread.sleep(5000);
		driver.findElement(By.id(LoginBtn)).click();	
	}
	@Test(description = "ok",priority = 13)
	public void TC22() throws InterruptedException{
		ViewUsersClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div/div[1]/div[1]/div/input")).sendKeys("test@yopmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath(EditButtonSymbol)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[3]/div/div[3]/div[3]/img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[3]/div/div/div/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@title='Clcik here to update']")).click();
	}
	@Test(description = "ok",priority = 14)
	public void TC23() throws InterruptedException{
		TC22();
		WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div/div[2]/div[2]"));
		String text = ele1.getText();
		driver.findElement(By.xpath("//a[@class='btn btn-icon btn-md btn-active-color-primary mt-n2']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='menu-link px-5 bg-danger']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name(Email)).sendKeys(text);
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine();
		driver.findElement(By.name(Password)).sendKeys(inputText);
		Thread.sleep(5000);
		driver.findElement(By.id(LoginBtn)).click();	
	}
	
	@Test(description = "ok", priority = 15)
	public void TC32() throws InterruptedException, AWTException{
		ViewUsersClick();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div/div[1]/div[1]/div/input")).sendKeys("test@yopmail.com");
		driver.findElement(By.xpath(EditButtonSymbol)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_lblImage\"]/span")).click();
		Thread.sleep(2000);
		WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]"));
		ele1.click();
		Thread.sleep(2000);
		String FilePath=PropertyFileReader.propertymap.get("FilePath");
		Robot robot = new Robot();
		StringSelection selection = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@title='Click here to upload/change image']")).click();

	}
	
	@Test(description = "ok",priority = 16)
	public void TC33() throws InterruptedException, AWTException{
		TC32();
		//need to change the file path
	}
}



