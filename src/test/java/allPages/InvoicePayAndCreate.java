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

public class InvoicePayAndCreate extends Locators {
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
	public void InvoicePayClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");

		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(CustomerBtn)).click();
		driver.findElement(By.xpath(InvoicePayBtn)).click();
	}

	@Test
	public void CreateInvoiceClick() throws InterruptedException {
		PropertyFileReader.propertyRead();
		String EmailId=PropertyFileReader.propertymap.get("EmailId");
		String Passwrd=PropertyFileReader.propertymap.get("Passwrd");

		driver.findElement(By.name(Email)).sendKeys(EmailId);
		driver.findElement(By.name(Password)).sendKeys(Passwrd);
		driver.findElement(By.id(LoginBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(CustomerBtn)).click();
		driver.findElement(By.xpath(CretInvBtn)).click();
	}
	
	@Test(priority = 1)
	public void TC01() throws InterruptedException {
		InvoicePayClick();
		String IPBankDDName=PropertyFileReader.propertymap.get("IPBankDDName");
		String IPPaySts=PropertyFileReader.propertymap.get("IPPaySts");
		String IPSrchBox=PropertyFileReader.propertymap.get("IPSrchBox");
		ele1=driver.findElement(By.xpath(IPBankDD));
		Select sel1=new Select(ele1);
		sel1.selectByVisibleText(IPBankDDName);
		ele2=driver.findElement(By.xpath(IPPySts));
		Select sel2=new Select(ele2);
		sel2.selectByVisibleText(IPPaySts);
		driver.findElement(By.xpath(IPSrchBtn)).sendKeys(IPSrchBox);
	}

	@Test(priority = 2)
	public void TC02() throws InterruptedException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		driver.findElement(By.xpath(IPChngInvDate)).click();
		driver.findElement(By.xpath(IPChngInvPayDatBtn)).click();
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div/div/button"));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory Field is shown");
		}
		else {
			System.out.println("Mandatory Field is not shown");
		}
	}

	@Test(priority = 3)
	public void TC03() throws InterruptedException {
		TC02();
		String IPPayDate=PropertyFileReader.propertymap.get("IPPayDate");
		ele1.click();
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String EffectiveDromDate= scanner.nextLine();
		driver.findElement(By.xpath(IPEffMonFrm)).sendKeys(EffectiveDromDate);
		ele2=driver.findElement(By.xpath(IPPyDate));
		Select sel=new Select(ele2);
		sel.selectByVisibleText(IPPayDate);
		driver.findElement(By.xpath(IPChngInvPayDatBtn)).click();
	}

	@Test(priority = 4)
	public void TC04() throws InterruptedException {
		TC02();
		String IpChPyBkNm=PropertyFileReader.propertymap.get("IpChPyBkNm");
		ele1.click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPChPyEditPyBtn)).click();
		ele2=driver.findElement(By.id(IPChPyDepAccName));
		Select sel=new Select(ele2);
		sel.selectByVisibleText(IpChPyBkNm);
		driver.findElement(By.xpath(IPChPyRobChck)).click();
		driver.findElement(By.xpath(IPChPyAgrBtn)).click();
		driver.findElement(By.xpath(IPChPyUpdBtn)).click();
	}

	@Test(priority = 5)
	public void TC06() throws InterruptedException {
		TC02();
		ele1.click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPChPyEditPyBtn)).click();
		driver.findElement(By.xpath(IPChPyBckBtn)).click();
	}

	@Test(priority = 6)
	public void TC07() throws InterruptedException {
		TC02();
		ele1.click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPChPySkpPy)).click();
	}

	@Test(priority = 7)
	public void TC08() throws InterruptedException {
		TC07();
		String IpSkpPayRsn=PropertyFileReader.propertymap.get("IpSkpPayRsn");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String StartMonth= scanner.nextLine();
		driver.findElement(By.name(IPSkpPyStrtMnt)).sendKeys(StartMonth);

		Scanner scanner1 = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String EndMonth= scanner1.nextLine();
		driver.findElement(By.name(IPSkpPyEndMnt)).sendKeys(EndMonth);
		driver.findElement(By.name(IPSkpPyRsn)).sendKeys(IpSkpPayRsn);
		driver.findElement(By.name(IPSkpPyCnfrm)).click();
		driver.findElement(By.xpath(IPSkpPyBtn)).click();
	}

	@Test(priority = 8)
	public void TC09() throws InterruptedException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		driver.findElement(By.xpath(IPChngInvDate)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPChPyBackBtn)).click();
	}

	@Test(priority = 9)
	public void TC10() throws InterruptedException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		driver.findElement(By.xpath(IPChMntPyBtn)).click();
		driver.findElement(By.xpath(IPChMntPySavBtn)).click();
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div/div/div/button"));
		Thread.sleep(2000);
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shown");
		}
		ele1.click();
	}

	@Test(priority = 10)
	public void TC11() throws InterruptedException {
		TC10();
		String IPChMntPayAmt=PropertyFileReader.propertymap.get("IPChMntPayAmt");
		String IPChMntPayRsn=PropertyFileReader.propertymap.get("IPChMntPayRsn");
		driver.findElement(By.name(IPChMntPyAmt)).sendKeys(IPChMntPayAmt);
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String EffDate= scanner.nextLine();
		driver.findElement(By.xpath(IPChMntPyEffDate)).sendKeys(EffDate);
		driver.findElement(By.name(IPChMntPyRsn)).sendKeys(IPChMntPayRsn);
		driver.findElement(By.xpath(IPChMntPySavBtn)).click();
	}

	@Test(priority = 11)
	public void TC12() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(IPChMntPyEditActBtn));
		Actions act1=new Actions(driver);
		act1.click().build().perform();
		element1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditClrBtn)).click();
	}

	@Test(priority = 12)
	public void TC13() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(IPChMntPyEditActBtn));
		Actions act1=new Actions(driver);
		act1.click().build().perform();
		element1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditSavBtn)).click();
	}

	@Test(priority =13 )
	public void TC14() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(IPChMntPyEditActBtn));
		Actions act1=new Actions(driver);
		act1.click().build().perform();
		element1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditBckBtn)).click();
	}


	@Test(priority = 14)
	public void TC15() throws InterruptedException {
		TC10();
		String IPChMntPortName=PropertyFileReader.propertymap.get("IPChMntPortName");
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyAddCusPort)).click();
		driver.findElement(By.name(IPChMntPyAddCusPortName)).sendKeys(IPChMntPortName);
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyAddCusPortClr)).click();
	}

	@Test(priority = 15)
	public void TC16() throws InterruptedException {
		TC10();
		String IPChMntPortName=PropertyFileReader.propertymap.get("IPChMntPortName");
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyAddCusPort)).click();
		driver.findElement(By.name(IPChMntPyAddCusPortName)).sendKeys(IPChMntPortName);
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyAddCusPortSav)).click();
	}

	@Test(priority = 16)
	public void TC17() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyAddCusPort)).click();
		driver.findElement(By.xpath(IPChMntPyAddCusPortCls)).click();
	}

	@Test(priority = 17)
	public void TC18() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPort)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPortClr)).click();
		ele1 = driver.findElement(By.xpath(IPChMntPyEdtCusPortClr));
		String text = ele1.getAttribute("value");
		System.out.println(text);
		if(text.isEmpty())
		{
			System.out.println("Clear button is not working");
		}
		else {
			System.out.println("Clear button is working properly");
		}
	}

	@Test(priority = 18)
	public void TC19() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPort)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPortSav)).click();
	}

	@Test(priority = 19)
	public void TC20() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEditCus)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPort)).click();
		driver.findElement(By.xpath(IPChMntPyEdtCusPortCls)).click();
	}


	@Test(priority = 20)
	public void TC21() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyViwCusBtn)).click();
	}

	@Test(priority = 21)
	public void TC22() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyEdPySch)).click();
		driver.findElement(By.xpath(IPChMntPyEdPySchUpd)).click();
	}

	@Test(priority = 22)
	public void TC23() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyMrgCus)).click();
		driver.findElement(By.xpath(IPChMntPyMrgCusMan)).click();
		driver.findElement(By.xpath(IPChMntPyMrgeBtn)).click();
		ele1 = driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div[2]/div/div[3]/div[1]/div[2]/div/div/span"));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
	}

	
	@Test(priority = 23)
	public void TC24() throws InterruptedException {
		InvoicePayClick();
		String IPBankDDName1=PropertyFileReader.propertymap.get("IPBankDDName1");
		String IPPaySts=PropertyFileReader.propertymap.get("IPPaySts");
		String IPSrchBox1=PropertyFileReader.propertymap.get("IPSrchBox1");
		ele1=driver.findElement(By.xpath(IPBankDD));
		Select sel1=new Select(ele1);
		sel1.selectByVisibleText(IPBankDDName1);
		ele2=driver.findElement(By.xpath(IPPySts));
		Select sel2=new Select(ele2);
		sel2.selectByVisibleText(IPPaySts);
		driver.findElement(By.xpath(IPSrchBtn)).sendKeys(IPSrchBox1);
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		driver.findElement(By.xpath(IPChMntPyBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyMrgCus)).click();
		String IPChMntPayMrgeCusDD=PropertyFileReader.propertymap.get("IPChMntPayMrgeCusDD");
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act1=new Actions(driver);
		act1.click().build().perform();
		element1.click();
		ele1=driver.findElement(By.xpath(IPChMntPyMrgeCusDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(IPChMntPayMrgeCusDD);
		driver.findElement(By.xpath(IPChMntPyMrgCusMan)).click();
		driver.findElement(By.xpath(IPChMntPyMrgeBtn)).click();
	}
	
	@Test(priority = 24)
	public void TC25() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyRcdPymts)).click();
		driver.findElement(By.xpath(IPChMntPyRcdPyPrnt)).click();
	}
	
	@Test(priority = 25)
	public void TC26() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyMonCRM)).click();
		driver.findElement(By.xpath(IPChMntPyMonCRMUpd)).click();
		//Mandatory message check for multiple elements at a same time
		List<WebElement> elements = driver.findElements(By.cssSelector("div[style='color: red;']"));

		for (WebElement ele1 : elements) {
		    System.out.println(ele1.getText());
		}
	}
	
	@Test(priority = 26)
	public void TC27() throws InterruptedException {
		TC10();
		String IPMonCRMDD=PropertyFileReader.propertymap.get("IPMonCRMDD");
		String IPMonCRMName=PropertyFileReader.propertymap.get("IPMonCRMName");
		String IPMonCRMEsc=PropertyFileReader.propertymap.get("IPMonCRMEsc");
		String IPMonCRMProd=PropertyFileReader.propertymap.get("IPMonCRMProd");
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyMonCRM)).click();
		ele1=driver.findElement(By.id(IPChMntPyMonCRMDD));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(IPMonCRMDD);
		
		ele2=driver.findElement(By.id(IPChMntPyMonCRMName));
		Select sel1=new Select(ele2);
		sel1.selectByVisibleText(IPMonCRMName);
		
		driver.findElement(By.name(IPChMntPyMonCRMEsc)).sendKeys(IPMonCRMEsc);
		driver.findElement(By.name(IPChMntPyMonCRMProd)).sendKeys(IPMonCRMProd);
		driver.findElement(By.xpath(IPChMntPyMonCRMUpd)).click();
	}
	
	@Test(priority = 27)
	public void TC28() throws InterruptedException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyDocStup)).click();
		driver.findElement(By.xpath(IPChMntPyDocStupAgree)).click();
		driver.findElement(By.xpath(IPChMntPyDocStupSav)).click();
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_content_container\"]/div[2]/div[2]/form/div/div/div[2]/div/div"));
		Thread.sleep(2000);
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shown");
			
		}
	}
	
	@Test(priority = 28)
	public void TC29() throws InterruptedException, AWTException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyDocStup)).click();
		driver.findElement(By.xpath(IPChMntPyDocStupUpld)).click();
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
		driver.findElement(By.xpath(IPChMntPyDocStupAgree)).click();
		driver.findElement(By.xpath(IPChMntPyDocStupSav)).click();
	}
	
	@Test(priority = 29)
	public void TC30() throws InterruptedException, AWTException {
		TC10();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPChMntPyActBtn));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPChMntPyBckBtn)).click();
	}
	
	@Test(priority = 30)
	public void TC31() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPEditCusEditPy)).click();
		driver.findElement(By.xpath(IPEditCusRob)).click();
		driver.findElement(By.xpath(IPEditCusAgree)).click();
		driver.findElement(By.xpath(IPEditCusUpd)).click();
	}
	
	@Test(priority = 31)
	public void TC32() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPEditCusChngInvDt)).click();
		driver.findElement(By.xpath(IPEditCusChngPyDt)).click();
		Thread.sleep(2000);
		ele1=driver.findElement(By.xpath(IPEditCusChngMan));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory Message is shown");
		}
		ele1.click();
	}
	@Test(priority = 32)
	public void TC33() throws InterruptedException, AWTException {
		TC32();
		String IPInvPyDte=PropertyFileReader.propertymap.get("IPInvPyDte");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String EffectiveFromDate= scanner.nextLine();
		driver.findElement(By.name(IPEditCusChngInvEff)).sendKeys(EffectiveFromDate);
		ele1=driver.findElement(By.xpath(IPEditCusChngInvPyDte));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(IPInvPyDte);
		driver.findElement(By.xpath(IPEditCusChngPyDt)).click();
	}
	
	@Test(priority = 33)
	public void TC34() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPEditCusStpPy)).click();
		driver.findElement(By.name(IPEditCusStpPyCnfrm)).click();
		driver.findElement(By.xpath(IPEditCusStpPyCnfrmBtn)).click();
		Thread.sleep(2000);
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[2]/div/div[2]/div/div[2]/div/div/button"));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		ele1.click();
	}
	
	@Test(priority = 34)
	public void TC35() throws InterruptedException, AWTException {
		TC34();
		String IPStpRsn=PropertyFileReader.propertymap.get("IPStpRsn");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String StopFrom= scanner.nextLine();
		driver.findElement(By.name(IPEditCusStpFrm)).sendKeys(StopFrom);
		driver.findElement(By.name(IPEditCusStpRsn)).sendKeys(IPStpRsn);
		driver.findElement(By.name(IPEditCusStpPyCnfrm)).click();
		driver.findElement(By.xpath(IPEditCusStpPyCnfrmBtn)).click();
	}
	
	@Test(priority = 35)
	public void TC36() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPQckUpld)).click();
		driver.findElement(By.xpath(IPQckUpldBtn)).click();
		ele1=driver.findElement(By.xpath(IPQckUpldTost));
		Thread.sleep(1000);
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory toast appears");
		}
	}
	

	@Test(priority = 36)
	public void TC37() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPQckUpld)).click();
		driver.findElement(By.xpath(IPQckUpldSamFile)).click();
	}
	
	@Test(priority = 37)
	public void TC38() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPQckUpld)).click();
		driver.findElement(By.xpath(IPQckUpldFile)).click();
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
	}
	

	@Test(priority = 38)
	public void TC39() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPQckUpld)).click();
		driver.findElement(By.xpath(IPQckUpldMonth)).click();
	}
	
	@Test(priority = 39)
	public void TC40() throws InterruptedException, AWTException {
		TC38();
		Thread.sleep(2000);
		driver.findElement(By.xpath(IPQckUpldBtn)).click();
	}
	
	@Test(priority = 40)
	public void TC41() throws InterruptedException, AWTException {
		TC01();
		driver.findElement(By.xpath(IPCusEditBtn)).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(IPEditCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(IPEditCusBckBtn)).click();
	}
	
	@Test(priority = 41)
	public void TC42() throws InterruptedException {
		CreateInvoiceClick();
		String CIBankDD=PropertyFileReader.propertymap.get("CIBankDD");
		String CIPayOpt=PropertyFileReader.propertymap.get("CIPayOpt");
		ele1=driver.findElement(By.xpath(CIBnkDD));
		Select sel1=new Select(ele1);
		sel1.selectByVisibleText(CIBankDD);
		ele2=driver.findElement(By.xpath(CIPyOpt));
		Select sel2=new Select(ele2);
		sel2.selectByVisibleText(CIPayOpt);
		ele3=driver.findElement(By.xpath(CISrchCus));
		ele3.click();;
	}
	
	@Test(priority = 42)
	public void TC43() throws InterruptedException {
		TC42();
		String CISrchCust=PropertyFileReader.propertymap.get("CISrchCust");
		ele3.sendKeys(CISrchCust);
	}
	
	@Test(priority = 43)
	public void TC44() throws InterruptedException {
		TC43();
		driver.findElement(By.xpath(CIViewCusBtn)).click();
	}
	
	@Test(priority = 44)
	public void TC44a() throws InterruptedException {
		TC44();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(CIViewCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(CIViewCusAddLine)).click();
		driver.findElement(By.xpath(CIViewCusSavBtn)).click();
	}
	
	@Test(priority = 45)
	public void TC45() throws InterruptedException {
		TC44();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(CIViewCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(CIViewCusAddLine)).click();
		Thread.sleep(2000);
		driver.findElement(By.name(CIViewCusMPBtn)).click();
		driver.findElement(By.xpath(CIViewCusMPSavBtn)).click();
		Thread.sleep(2000);
		ele1=driver.findElement(By.xpath("//*[@id=\"kt_body\"]/div[3]/div/div[2]/div/div[2]/div/div/div[3]/button"));
		if(ele1.isDisplayed()) {
			System.out.println("Mandatory message is shown");
		}
		else {
			System.out.println("Mandatory message is not shsown");
		}
		ele1.click();
	}
	
	@Test(priority = 46)
	public void TC46() throws InterruptedException {
		TC45();
		String CIViewCustMPDes=PropertyFileReader.propertymap.get("CIViewCustMPDes");
		String CIViewCustMPAmt=PropertyFileReader.propertymap.get("CIViewCustMPAmt");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String DueMonth= scanner.nextLine();
		driver.findElement(By.name(CIViewCusMPDueMnt)).sendKeys(DueMonth);
		driver.findElement(By.xpath(CIViewCusMPDes)).sendKeys(CIViewCustMPDes);
		driver.findElement(By.name(CIViewCusMPAmt)).sendKeys(CIViewCustMPAmt);
		Thread.sleep(2000);
		driver.findElement(By.xpath(CIViewCusMPClrBtn)).click();
	}
	
	@Test(priority = 47)
	public void TC47() throws InterruptedException {
		TC45();
		String CIViewCustMPDes=PropertyFileReader.propertymap.get("CIViewCustMPDes");
		String CIViewCustMPAmt=PropertyFileReader.propertymap.get("CIViewCustMPAmt");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String DueMonth= scanner.nextLine();
		driver.findElement(By.name(CIViewCusMPDueMnt)).sendKeys(DueMonth);
		driver.findElement(By.xpath(CIViewCusMPDes)).sendKeys(CIViewCustMPDes);
		driver.findElement(By.name(CIViewCusMPAmt)).sendKeys(CIViewCustMPAmt);
		Thread.sleep(2000);
		driver.findElement(By.xpath(CIViewCusMPSavBtn)).click();
	}
	
	@Test(priority = 48)
	public void TC48() throws InterruptedException {
		TC45();
		String CIViewCustMPDes=PropertyFileReader.propertymap.get("CIViewCustMPDes");
		String CIViewCustMPAmt=PropertyFileReader.propertymap.get("CIViewCustMPAmt");
		Scanner scanner = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please enter in the format like Feb/2024 (or) Mar/2024");
		String DueMonth= scanner.nextLine();
		driver.findElement(By.name(CIViewCusMPDueMnt)).sendKeys(DueMonth);
		driver.findElement(By.xpath(CIViewCusMPDes)).sendKeys(CIViewCustMPDes);
		driver.findElement(By.name(CIViewCusMPAmt)).sendKeys(CIViewCustMPAmt);
		driver.findElement(By.xpath(CIViewCusMPChrgRadBtn)).click();
		driver.findElement(By.xpath(CIViewCusMPSavBtn)).click();
	}
	
	@Test(priority = 49)
	public void TC49() throws InterruptedException {
		TC44();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(CIViewCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(CIViewCusAddLine)).click();
		Thread.sleep(2000);
		driver.findElement(By.name(CIViewCusMPBtn)).click();
		driver.findElement(By.xpath(CIViewCusMPClsBtn)).click();
	}
	
	@Test(priority = 50)
	public void TC50() throws InterruptedException {
		TC44();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath(CIViewCusAct));
		Actions act=new Actions(driver);
		act.click().build().perform();
		element.click();
		driver.findElement(By.xpath(CIViewCusBckBtn)).click();
	}
	}
	
	
	




