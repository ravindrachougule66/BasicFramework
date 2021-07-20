package TestScript;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DriverScript {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/Utilities/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		
		
		Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/TestCases/TestData.xlsx");
		int rowcount = xr.getRowCount("Sheet1");
		System.out.println(rowcount);
		for(int i=2;i<=rowcount;i++)
		{
			String run = xr.getCellData("Sheet1", "Run", i).trim();
			//System.out.println(run);
			if(run.equalsIgnoreCase("ON"))
			{
				String TCName = xr.getCellData("Sheet1", "TestCaseName", i).trim();
				//System.out.println(TCName);
				switch(TCName)
				{
					case "verifyAppUrl_TC01":
						String Exptitle = "vtiger CRM - Commercial Open Source CRM";
						String Actualtitle = driver.getTitle().trim();
						if(Exptitle.equals(Actualtitle))
						{
							System.out.println(TCName+"  = Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyAppLogo_TC03":
						if(driver.findElement(By.xpath("//img[@src='include/images/vtiger-crm.gif']")).isDisplayed())
						{
							System.out.println(TCName+"  = Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}				
						break;
					case "verifyTextKeyModules_TC04":
						String ExpText = "Key Modules";
						String ActualText = driver.findElement(By.xpath("//font[text()= 'Key Modules']")).getText().trim();
						if(ExpText.equals(ActualText))
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1","Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyLinkvtigerCustomerPortal_TC05":
						if(driver.findElements(By.linkText("vtiger Customer Portal")).size()==1)
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1","Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyUsernameTextBox_TC06":
						driver.findElement(By.name("user_name")).sendKeys("IsEditable");
						String textval = driver.findElement(By.name("user_name")).getAttribute("value");
						if(textval.equals("IsEditable"))
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1","Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyPasswordTextEncyption_TC07":
						String textvalpwd = driver.findElement(By.xpath("//input[@type='password']")).getAttribute("type");
						if(textvalpwd.equals("password"))
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1","Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyErrMsgwithInvalidCredentials_TC08":
						driver.findElement(By.name("user_name")).sendKeys("admin");
						driver.findElement(By.name("user_password")).sendKeys("admin123");
						driver.findElement(By.name("Login")).click();
						if(driver.findElement(By.xpath("//td[contains(text(),'You must specify a valid username and password.')]")).isDisplayed())
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyLoginwithValidCredentials_TC09":
						driver.findElement(By.name("user_name")).clear();
						driver.findElement(By.name("user_password")).clear();
						driver.findElement(By.name("user_name")).sendKeys("admin");
						driver.findElement(By.name("user_password")).sendKeys("admin");
						driver.findElement(By.name("Login")).click();
						if(driver.findElements(By.linkText("Logout")).size()==1)
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyLogoutLink_TC10":
						driver.findElement(By.linkText("Logout")).click();
						if(driver.findElement(By.name("Login")).isDisplayed())
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyLeadMandatoryFields_TC11":
						driver.findElement(By.linkText("New Lead")).click();
						driver.findElements(By.name("button")).get(0).click();
						String lastname = driver.switchTo().alert().getText();
						driver.switchTo().alert().accept();
						driver.findElement(By.name("lastname")).sendKeys("Patil");
						driver.findElements(By.name("button")).get(0).click();
						String compname = driver.switchTo().alert().getText();
						driver.switchTo().alert().accept();
						driver.findElement(By.name("company")).sendKeys("HDFC Bank");
						driver.findElements(By.name("button")).get(0).click();
						driver.findElement(By.name("Delete")).click();
						String delmsg = driver.switchTo().alert().getText();
						driver.switchTo().alert().dismiss();
						driver.findElement(By.name("Delete")).click();
						driver.switchTo().alert().accept();
						
						if((lastname.equals("Last Name cannot be empty") && (compname.equals("Company cannot be empty") && (delmsg.equals("Are you sure you want to delete this record?")))))
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verifyMouseMovement_TC12":
						Actions act = new Actions(driver);
						act.moveToElement(driver.findElement(By.id("showSubMenu"))).build().perform();
						Thread.sleep(2000);
						driver.findElement(By.linkText("New Vendor")).click();
						
						if(driver.findElement(By.xpath("//td[contains(text(),'Vendor Name:')][@class='moduleTitle']")).isDisplayed())
						{
							System.out.println(TCName+" =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"  = Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "verify_drag_drop_TC13":
						driver.findElement(By.linkText("My Account")).click();
						driver.findElement(By.name("Customise")).click();
						Actions act1 = new Actions(driver);					
						act1.dragAndDrop(driver.findElement(By.id("cl2")), driver.findElement(By.id("cl8"))).build().perform();
						Thread.sleep(2000);					
						if(driver.findElement(By.id("cl8")).getText().equals("Calendar"))
						{
							System.out.println(TCName+"   =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"   =  Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
					case "Action_on_popup_Window_TC14":
						driver.findElement(By.linkText("New Account")).click();
						driver.findElement(By.name("btn1")).click();					
						Thread.sleep(2000);	
						
						Set<String> set = driver.getWindowHandles();
						Iterator<String> iter = set.iterator();
						String fisrtwindow = iter.next();
						System.out.println(fisrtwindow);
						String Secondwindow = iter.next();
						System.out.println(fisrtwindow);
						driver.switchTo().window(Secondwindow);
						driver.findElement(By.name("name")).sendKeys("vtiger");
						driver.close();
						driver.switchTo().window(fisrtwindow);					
						if(true)
						{
							System.out.println(TCName+"   =  Passed");
							xr.setCellData("Sheet1", "Status", i, "Passed");
						}
						else
						{
							System.out.println(TCName+"   =  Failed");
							xr.setCellData("Sheet1", "Status", i, "Failed");
						}
						break;
				}
			}
		}
		driver.quit();
	}

}
