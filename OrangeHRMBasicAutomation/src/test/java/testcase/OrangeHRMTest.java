package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.DataGenerationUtility;

import java.time.Duration;

public class OrangeHRMTest {

    public WebDriver driver;
    public WebDriverWait wait;
    String randomIncorrectUserName = DataGenerationUtility.generateRandomIncorrectUserName();
    String randomIncorrectPassword = DataGenerationUtility.generateRandomIncorrectPassword();
    String password = DataGenerationUtility.generatePassword();
    String userName = DataGenerationUtility.randomUserName();
    String number = DataGenerationUtility.getRandomNumber(5);
    String randomName = DataGenerationUtility.firstRandomName();
    String randomLastName = DataGenerationUtility.lastRandomName();
    String alphaNum = DataGenerationUtility.randomAlphaNumeric();

    @BeforeClass
    @Parameters({"opSystem", "browser"})
    void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    void Login(){
        //Invalid details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']"))).sendKeys(randomIncorrectUserName);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(randomIncorrectPassword);
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        //Valid details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']"))).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }

    @Test (priority = 2)
    void AddNewEmployee() throws InterruptedException {
        String title = driver.getTitle();
        System.out.println("Logon Successful. Title name: " + title);

        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Add Employee']"))).click();

        try{
            Thread.sleep(3000);
            WebElement uploadIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-plus']")));
            //WebElement uploadIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']"));
            uploadIcon.click();

            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            fileInput.sendKeys("C:\\Users\\Lebo\\YPGJAVALessons\\YPGAutomation\\src\\test\\resources\\Avatar.png");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Image not loaded.");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']"))).sendKeys(randomName);
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(randomLastName);
        driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).clear();
        driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")).sendKeys(number);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']"))).click();
        driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]")).sendKeys(alphaNum);
        driver.findElement(By.xpath("//label[normalize-space()='Enabled']")).isSelected();

        String userPassword = password;
        driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(userPassword);
        driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(userPassword);
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-toast-content")));

        // Validate the toast message
        String expectedMessage = "Successfully Saved";
        String actualMessage = toastMessage.getText();

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("New Employee added successfully. Toast message: " + expectedMessage);
        } else {
            System.out.println("Test Failed: Expected '" + expectedMessage + "' but got '" + actualMessage + "'");
        }
    }

    @Test (priority = 3)
    void AddPersonalDetails() throws InterruptedException {
        //Step 15: OtherID
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/input[1]"))).sendKeys(number);
        //Step 16: Driver's License
        driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[2]/div[1]/div[1]/div[2]/input[1]")).sendKeys(number);
        //Step 17: License Expiry Date

        String month = "July";
        String year = "2030";
        String day = "31";

        //Select Drop Down
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]/div[1]/div[2]/div[1]/div[1]/i[1]"))).click();
        // driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/i[1]")).click();

        //Select month & year
        while(true) {
            String mnth = driver.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']//p")).getText();
            String yr = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']//p")).getText();

            if(mnth.equals(month) && yr.equals(year)) {
                break;
            }

            driver.findElement(By.xpath("//i[@class='oxd-icon bi-chevron-right']")).click(); //Foward Date search
        }

        //Select date
        java.util.List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
        for(WebElement dt:allDates) {
            if(dt.getText().equals(day)) {
                dt.click();
                break;
            }
        }

        //Nationality DropDown
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[2]/i")).click();
        java.util.List<WebElement> nation = driver.findElements(By.xpath("//div[@role='listbox']//span"));

        System.out.println("Total number of options: " + nation.size());

        for(WebElement option:nation) {
            String text = option.getText();

            if(text.equals("Brazilian")) {
                option.click();
                break;
            }
        }

        //Marital Status DropDown
        driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")).click();
        java.util.List<WebElement> status = driver.findElements(By.xpath("//div[@role='listbox']//span"));

        for(WebElement statusOption:status) {
            String statusText = statusOption.getText();

            if(statusText.equals("Married")) {
                statusOption.click();
                break;
            }
        }

        //Date of Birth
        String bMonth = "August";
        String bYear = "2018";
        String bDay = "15";

        //Select Drop Down
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/i")).click();

        //Select month & year
        while(true) {
            String bMnth = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[1]/div/p")).getText();
            String bYr = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[2]/div/p")).getText();

            if(bMnth.equals(bMonth) && bYr.equals(bYear)) {
                break;
            }

            driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/button[1]/i")).click(); //Back Date search
        }

        //Select date
        java.util.List<WebElement> bDates = driver.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
        for(WebElement bDt:bDates) {
            if(bDt.getText().equals(bDay)) {
                bDt.click();
                break;
            }

        }

        //Gender Radio Button
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/label/span")).click();

        Thread.sleep(2000);

        //Blood Type DropDown
        driver.findElement(By.xpath("//div[@class='orangehrm-custom-fields']//div[@class='orangehrm-card-container']//form[@class='oxd-form']//div[@class='oxd-form-row']//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
        java.util.List<WebElement> bloodType = driver.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[3]/div[1]/div"));

        for(WebElement typeOption:bloodType) {
            String bldType = typeOption.getText();

            if(bldType.equals("B-")) {
                typeOption.click();
                break;
            }
        }

        //Save Button 2
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[2]/button")).click();
    }

    @Test (priority = 4)
    void EmergencyContacts() throws InterruptedException {
        Thread.sleep(2000);
        // Create an instance of JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0);");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Emergency Contacts']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/div[@class='orangehrm-action-header']/button[1]"))).click();
        driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[1]//div[1]//div[1]//div[1]//div[2]//input[1]")).sendKeys(randomName);
        driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[1]//div[1]//div[2]//div[1]//div[2]//input[1]")).sendKeys("Father");
        driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']//div[2]//div[1]//div[1]//div[1]//div[2]//input[1]")).sendKeys(number);
        driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']//div[2]//div[1]//div[2]//div[1]//div[2]//input[1]")).sendKeys(number);
        driver.findElement(By.xpath("//div[3]//div[1]//div[2]//input[1]")).sendKeys(number);
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    @Test (priority = 5)
    void Logoff(){
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
    }

    @Test (priority = 6)
    void ResetPassword(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']"))).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Reset Password']"))).click();

        String successfulReset = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Reset Password link sent successfully']"))).getText();
        if (successfulReset.equals("Reset Password link sent successfully")){
            System.out.println("Password rest sent on email.");
        }
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
