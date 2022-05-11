package StepDefinitions;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import gherkin.lexer.Th;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en_old.Ac;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.poi.ss.formula.functions.Na;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.BaseClass;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;




@RunWith(Cucumber.class)
public class stepDefinitions extends BaseClass  {
    public Properties Pro;
    public Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public Actions actions;



    public static sharedatastep sharedata;
    public String ReferenceNumber = "MANP/000002433/2020";
    public String Name = "";
    public String FirstName = "";
    public String SecondName = "";
    public String Email = "";


    public stepDefinitions(sharedatastep sharedata) {

        stepDefinitions.sharedata = sharedata;

    }

    @Before(order = 2)
    public void method1() throws Exception {
        Pro = new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\resources\\global.properties");
        Pro.load(fls);
        driver = BaseClass.getDriver();
        actions = new Actions(driver);

    }

    public void switchToFrameBackoffice(){
        WebElement frame = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(frame);
    }

    @Then("Switch to backoffice frame")
    public void switchToBoFrame() {
        switchToFrameBackoffice();
    }

    @Then("Switch to default")
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    @Then("^Verify success message \"([^\"]*)\"$")
    public void verify_success_message(String Message) throws Throwable {

        WebElement successMessage = wait(200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + Message + "')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message(String error) throws Throwable {

        WebElement errorMessage = wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + error + "')]")));
        if (errorMessage.isDisplayed()) {
            //This will scroll the page till the element is found
            System.out.println("Error message ('" + error + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebElement noDataXpath = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'No record(s) found.')]")));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {
            Assert.assertFalse("Data found in table", false);
        }
    }

    @Given("^User navigates to the login page$")
    public void user_navigates_to_the_login_page() throws Throwable {
        driver.get(prop.getProperty("MRA_BO_URL"));
    }

    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_username_something_and_password_something_to_login(String username, String password) throws Throwable {
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("loginForm:username"))).sendKeys(username);
        Thread.sleep(350);
        driver.findElement(By.id("loginForm:password")).sendKeys(password);
        Thread.sleep(350);
        driver.findElement(By.xpath("//button[span='Login']")).click();
    }

    @Then("^User should be logged in$")
    public void user_should_be_logged_in() throws Throwable {
        String URL = driver.getCurrentUrl();

//    	Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml" );
        Assert.assertEquals(URL, "https://backoffice.mra.mw:8443/trips-ui/faces/login/Welcome.xhtml");
    }

    @Then("^User logs out successfully$")
    public void user_logs_out_successfully() throws Throwable {
        driver.findElement(By.id("Logout")).click();

    }

    //---------------------------------------------------------------------Verify the Process of Assign Audit Case-----------------------------------------------------------------------------------------------//
    @Given("^Open CRM URL Module as \"([^\"]*)\"$")
    public void open_crm_url_module_as_something(String strArg1) throws Throwable {
        driver.get("https://" + strArg1 + ":Passw0rd@trips-crm.mra.mw:5555/TripsWorkflow/main.aspx");
    }

    @And("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
        switch_to_frame0();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Active Cases in Progress Overview')]"))).isDisplayed();
        switchToDefault();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(1000);
    }


    @Then("^switch to frame0$")
    public void switch_to_frame0() throws Throwable {
        driver.switchTo().defaultContent();
        WebElement specificframe = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @Then("^switch to frame1$")
    public void switch_to_frame1() throws Throwable {
        driver.switchTo().defaultContent();
        WebElement specificframe = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID1"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @Then("^Enter Outcome Reason$")
    public void enter_Outcome_Reason() throws Throwable {
        Thread.sleep(2000);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("OutComeReason_Frame_XPATH"))));
        driver.switchTo().frame(specificframe);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_XPATH"))).click();
        WebDriverWait ReasonValue = new WebDriverWait(driver, 60);
        ReasonValue.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_Options_XPATH")))).click();
        Thread.sleep(8000);
    }


    @And("Click on compliance and enforcement > Perform tax compliance processing")
    public void clickOnComplianceUrl() throws InterruptedException {
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Compliance and Enforcement']"))).click();
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Configure Penalty Rules']"))).click();
    }

    @Then("Open CRM and close modal")
    public void openCRMAndCloseModal() {
        driver.get(Pro.getProperty("NRA_CRM_URL"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @Then("search for reference number")
    public void searchForReferenceNumber() throws InterruptedException {
        WebElement search = wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));

        search.clear();
        Thread.sleep(2000);
        //search.sendKeys("*AV/000000875/2021");
        search.sendKeys(ReferenceNumber);
        Thread.sleep(2000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }

    @Then("^Click on reference number$")
    public void click_on_reference_number() {

        WebElement elementLocator = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]")));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();

    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(dirContents).length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(10000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @And("Click on Vat Free Projects > Vat Free Project Application")
    public void clickOnVatFreeProjectsVatFreeProjectApplication() {
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='VAT Free Projects']"))).click();
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='VAT Free Project Application']"))).click();
    }

    @Then("Enter project owner details with tin {string}")
    public void enterProjectOwnerDetailsWithTin(String arg0) throws InterruptedException {
        wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[span='Find']"))).click();
        switchToBoFrame();
        wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(300);
        driver.findElement(By.xpath("//button[span='Search']")).click();
        switchToDefault();
        Thread.sleep(2000);
    }

    @Then("Enter project financier details")
    public void enterProjectFinancierDetails() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("VatFreeProjectApplication:FinancierName")).sendKeys("Brian Omondi");
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeProjectApplication:FinancierAddress")).sendKeys("Lavington");
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeProjectApplication:FinancierContactNumber")).sendKeys("254765443345");
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeProjectApplication:FinancierEmail")).sendKeys("info@codeisystems.co.ke");
        Thread.sleep(300);
    }

    @Then("Add contractor details with tin {string}")
    public void addContractorDetailsWithTin(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreeProjectApplication:j_idt74")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(500);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
        Thread.sleep(1000);
        switchToDefault();
        Thread.sleep(1000);

    }

    @Then("Add project details")
    public void addProjectDetails() throws Throwable{
        driver.findElement(By.id("VatFreeProjectApplication:ProjectName")).sendKeys("Project name : "+getRandom(5));
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeProjectApplication:ValueOfProject_input")).sendKeys("2000000");
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeProjectApplication:ValueOfExemptedPuchases_input")).sendKeys("0");
        Thread.sleep(3000);
    }

    @Then("Select bill of quantities as goods")
    public void selectBillOfQuantitiesAsGoods() {
        driver.findElement(By.xpath("//*[@id=\"VatFreeProjectApplication:Goods\"]/div[2]")).click();
    }

    @Then("Click add under bill of quantities")
    public void clickAddUnderBillOfQuantities() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreeProjectApplication:vatFreeItemTable:AddBOQ")).click();

    }

    @Then("Enter Vat free goods and services details")
    public void enterVatFreeGoodsAndServicesDetails() throws InterruptedException {
        switchToBoFrame();
        Thread.sleep(300);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFreeItem:ProformaInvoice"))).sendKeys(getRandom(5));
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"VatFreeItem:Item\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeItem:Code\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeItem:Unit\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeItem:UnitPrice_input")).sendKeys("5000");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeItem:Quantity_input")).sendKeys("400");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeItem:Value_input")).sendKeys("2000000");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeItem:VatToBeExempted_input")).sendKeys("0");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeItem:Ok")).click();
        switchToDefault();
        wait(35).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Cement']"))).isDisplayed();
    }

    @Then("Select bill of quantities certified")
    public void selectBillOfQuantitiesCertified() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeProjectApplication:BillsQunatityCertified\"]/div[3]")).click();
        Thread.sleep(400);
        driver.findElement(By.xpath("//li[text()='No']")).click();
    }


    @Then("Enter supplier details with tin {string}")
    public void enterSupplierDetailsWithTin(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreeProjectApplication:j_idt168")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(500);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
        Thread.sleep(1000);
        switchToDefault();
        Thread.sleep(6000);
    }

    @Then("Enter details of authorizing officials with tin {string}")
    public void enterDetailsOfAuthorizingOfficialsWithTin(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreeProjectApplication:authorisingOfficialsTable:AddAuth")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFreePrjAuthorisingOfficials:j_idt21"))).click();
        switchToDefault();
        Thread.sleep(3000);
        driver.switchTo().frame(1);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(500);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
        Thread.sleep(1000);
        switchToDefault();
        switchToBoFrame();
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreePrjAuthorisingOfficials:NIDOrPassport")).sendKeys("32355241");
        Thread.sleep(2000);
        driver.findElement(By.id("VatFreePrjAuthorisingOfficials:Ok")).click();
        switchToDefault();
        wait(50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+arg0+"']"))).isDisplayed();
    }

    @Then("Add attachment for VAT free project application")
    public void addAttachmentForVATFreeProjectApplication() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFreeProjectApplication:attachmentTable:AddAttachent")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AttachmentDetails:DocType\"]/div[3]"))).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(800);
        driver.findElement(By.id("AttachmentDetails:DocumentName")).sendKeys("Attachment");
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\" + File.separator + "test.png";
        Thread.sleep(300);
        driver.findElement(By.id("AttachmentDetails:AttachmentPath_input")).sendKeys(path);
        Thread.sleep(300);
        driver.findElement(By.id("AttachmentDetails:Ok")).click();
        switchToDefault();
        wait(50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Agreement with Malawi Government']"))).isDisplayed();
    }

    @Then("Enter qualifications for certifier")
    public void enterQualificationsForCertifier() throws InterruptedException {
        Thread.sleep(400);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"VatFreeProjectApplication:BOQCByQualifications\"]"))).sendKeys("Erwin Rommel");
    }

    @Then("Enter details of person making application")
    public void enterDetailsOfPersonMakingApplication() throws InterruptedException {
        Thread.sleep(400);
        driver.findElement(By.id("VatFreeProjectApplication:PersonName")).sendKeys("Maxipain");
        Thread.sleep(400);
        driver.findElement(By.id("VatFreeProjectApplication:PersonDesig")).sendKeys("SE");
        Thread.sleep(400);
        driver.findElement(By.id("VatFreeProjectApplication:PersonDate_input")).sendKeys(Keys.ENTER);
    }

    @Then("Submit application")
    public void submitApplication() throws InterruptedException {
        Thread.sleep(800);
        driver.findElement(By.xpath("//button[span='Submit']")).click();
    }


    @Then("Obtain reference number {string}")
    public void obtainReferenceNumber(String arg0) {
        String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + arg0 + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - VF00000047
        sharedatastep.Ref = FullMessage.substring(42);
        System.out.println(sharedatastep.Ref);
    }

    @And("click on Queues")
    public void clickOnQueues() {
        driver.findElement(By.xpath("//*[text()='Queues']")).click();
    }

    @And("^pick the case$")
    public void pick_the_case() throws Throwable {
        WebElement pickButton = wait(60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Pick ']")));
        Actions actions = new Actions(driver);
        actions.doubleClick(pickButton).perform();
    }

    @And("^click pick button dropdown$")
    public void click_pick_button_dropdown() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement assignDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moreCommands")));
        assignDropdown.click();

        WebElement pickButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("queueitem|NoRelationship|HomePageGrid|tbg.queueitem.HomepageGrid.Pick")));
        pickButton.click();
    }

    @And("enters VAT FREE reference number in search results")
    public void entersVATFREEReferenceNumberInSearchResults() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));

        search.clear();
        Thread.sleep(1000);

        //search.sendKeys("*IN/000036357/2022");
        search.sendKeys("*"+sharedatastep.Ref);
        Thread.sleep(5000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @Then("VAT FREE status should be {string}")
    public void vatFREEStatusShouldBe(String Status) throws Throwable {
        switch_to_frame1();
        Thread.sleep(3000);
        String text = wait(200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + Status + "']"))).getText();
        Assert.assertEquals(Status, text);
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
    }

    @And("wait for plan to load {string}")
    public void waitForPlanToLoad(String arg0) {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WebResource_VATFreeProjectApplicationAngular")));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + arg0 + "']")));
    }

    @Then("Enter tech officer notes {string}")
    public void enterTechOfficerNotes(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_vatFreeApplicantForm\"]/div[3]/tb-input-text-area/div/div[2]/div/textarea")).sendKeys(arg0);
    }

    @Then("Select status as send for authorization")
    public void selectStatusAsSendForAuthorization() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_technicalofficerapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @And("Click on Save button")
    public void clickOnSaveButton() throws InterruptedException {
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_vatfreeprojectapplication|NoRelationship|Form|Mscrm.Form.tbg_vatfreeprojectapplication.Save")).click();
    }

    @Then("Enter deputy commissioner notes and recommendations")
    public void enterDeputyCommissionerNotesAndRecommendations() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_vatFreeApplicantForm\"]/div[4]/tb-input-text-area[1]/div/div[2]/div/textarea")).sendKeys("Notes");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_vatFreeApplicantForm\"]/div[4]/tb-input-text-area[2]/div/div[2]/div/textarea")).sendKeys("Recommendations");
    }

    @Then("Select status as send for approval")
    public void selectStatusAsSendForApproval() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_dctechnicalapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }
}



