package StepDefinitions;


import Utils.BaseClass;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@RunWith(Cucumber.class)
public class stepDefinitions extends BaseClass  {
    public Properties Pro;
    public Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public Actions actions;



    public static sharedatastep sharedata;
    public String ReferenceNumber = "MANP/000002433/2020";
    Faker faker = new Faker();


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

    @And("Click on Vat Free Projects > Maintain Vat Free Project")
    public void clickOnVatFreeProjectsMaintainVatFreeProject() {
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='VAT Free Projects']"))).click();
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Maintain VAT Free Project']"))).click();
    }

    @And("Click on Vat Free Projects > Create Vat five tax certificate application")
    public void clickOnVatFreeProjectsCreateVatTaxCertificateApplication() {
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='VAT Free Projects']"))).click();
        wait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Create VAT 5 Tax Certificate Application']"))).click();
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
        sharedatastep.ProjectName = faker.company().name();
        System.out.println("Project name: "+sharedatastep.ProjectName);
        driver.findElement(By.id("VatFreeProjectApplication:ProjectName")).sendKeys(sharedatastep.ProjectName);
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
        driver.findElement(By.id("VatFreeItem:Quantity_input")).sendKeys("1000");
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
        Thread.sleep(3000);
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

    @Then("Update attachment for VAT free project application")
    public void updateAttachmentForVATFreeProjectApplication() throws InterruptedException {
        Thread.sleep(1000);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFreeProject:attachmentTable:Editttachent"))).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AttachmentDetails:DocType\"]/div[3]"))).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//li[text()='Contract Documents']")).click();
        Thread.sleep(800);
        driver.findElement(By.id("AttachmentDetails:DocumentName")).sendKeys("Attachment");
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\" + File.separator + "test.png";
        Thread.sleep(300);
        driver.findElement(By.id("AttachmentDetails:AttachmentPath_input")).sendKeys(path);
        Thread.sleep(300);
        driver.findElement(By.id("AttachmentDetails:Ok")).click();
        switchToDefault();
        wait(50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Contract Documents']"))).isDisplayed();
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
        Thread.sleep(3000);

        search.sendKeys("*VF00000077");
//        search.sendKeys("*"+sharedatastep.Ref);
        Thread.sleep(5000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @And("click checkbox in case number")
    public void clickCheckboxInCaseNumber() throws InterruptedException {
        Thread.sleep(4000);
        WebElement pickCheckBox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        Actions actions = new Actions(driver);
        actions.click(pickCheckBox).perform();
        driver.switchTo().defaultContent();
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

    @And("wait for plan to load for vat five {string}")
    public void waitForPlanToLoadVatFive(String arg0) {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WebResource_TaxpayerServiceApplicationAngular")));

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


    @Then("Enter CG notes")
    public void enterCGNotes() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_vatFreeApplicantForm\"]/div[5]/tb-input-text-area/div/div[2]/div/textarea")).sendKeys("Notes");
        Thread.sleep(1000);
    }

    @Then("Enter officer recommendation remarks")
    public void enterOfficerRecommendationRemarks() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/trips-app/div/app-taxpayer-services/div/app-vat5-certificate/div/form/div/div[2]/tb-input-text-area/div/div[2]/div/textarea")).sendKeys("Notes and recommendations");
        Thread.sleep(1000);
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

    @Then("Select status as approved")
    public void selectStatusAsApproved() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_commissionergeneralapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Then("Validate certificate application")
    public void validateCertificateApplication() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_revenueofficerapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Then("Approve vat five certificate application")
    public void approveVatFiveCertificateApplication() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_supervisorapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Then("Select status as approved and enter expiry date")
    public void selectStatusAsApprovedAndEnterDate() throws Throwable {
        driver.switchTo().defaultContent();

        switch_to_frame1();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_commissionergeneralapproval']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
        driver.findElement(By.id("header_process_tbg_expirydate")).click();
        Thread.sleep(500);
        driver.findElement(By.id("header_process_tbg_expirydate_iDateInput")).sendKeys(tomorrowsDate());

    }

    @Then("Enter project name and search")
    public void enterProjectNameAndSearch() throws InterruptedException {
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:ProjectName"))).sendKeys(sharedatastep.ProjectName);
//        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:ProjectName"))).sendKeys("Kreiger Group");
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[span='Search']")).click();
        wait(60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='"+sharedatastep.ProjectName+"']")));
//        wait(60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Kreiger Group']")));
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[span='Edit']")).click();
    }

    @Then("Select reason for edit")
    public void selectReasonForEdit() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"VatFreeProject:UpdateReason\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }


    @Then("Enter purchaser details with tin {string}")
    public void enterPurchaserDetailsWithTin(String arg0) throws InterruptedException {
        wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFiveTaxCertificateApplication:findPurchaser"))).click();
        switchToBoFrame();
        wait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(300);
        driver.findElement(By.xpath("//button[span='Search']")).click();
        switchToDefault();
        Thread.sleep(2000);
    }

    @Then("Enter supplier details for VAT certificate {string}")
    public void enterSupplierDetailsForVATCertificate(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:findSupplier")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(500);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
        Thread.sleep(1000);
        switchToDefault();
        Thread.sleep(3000);
    }


    @Then("Obtain project id")
    public void obtainProjectId() throws InterruptedException {
        WebElement idField = wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFreeProject:ProjectId")));
        Thread.sleep(1000);
        sharedatastep.projectId = idField.getAttribute("value");
        System.out.println("Project ID="+sharedatastep.projectId);
    }

    @Then("Enter Vat free project number")
    public void enterVatFreeProjectNumber() {
        driver.findElement(By.id("VatFiveTaxCertificateApplication:VatFreeProjectNo")).sendKeys(sharedatastep.projectId);
//        driver.findElement(By.id("VatFiveTaxCertificateApplication:VatFreeProjectNo")).sendKeys("VATF/00000011/2022");
    }

    @Then("Select bill of quantities as goods for vat five")
    public void selectBillOfQuantitiesAsGoodsForVatFive() {
        driver.findElement(By.xpath("//*[@id=\"VatFiveTaxCertificateApplication:Goods\"]/div[2]")).click();
    }

    @Then("Click add under bill of quantities for vat five")
    public void clickAddUnderBillOfQuantitiesForVatFive() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:goodsAndServicesTable:AddGS")).click();

    }

    @Then("Enter Vat free goods and services details for vat five")
    public void enterVatFreeGoodsAndServicesDetailsForVatFive() throws InterruptedException {
        switchToBoFrame();
        Thread.sleep(300);
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("VatFreeGoodsAndServices:ProformaInvoice_input"))).sendKeys(String.valueOf("3000000"));
        Thread.sleep(300);
        driver.findElement(By.id("VatFreeGoodsAndServices:Date_input")).click();
        driver.findElement(By.id("VatFreeGoodsAndServices:Date_input")).sendKeys(Keys.ENTER);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeGoodsAndServices:DescOfGoods\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeGoodsAndServices:Code\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"VatFreeGoodsAndServices:Unit\"]/div[3]")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeGoodsAndServices:UnitPrice_input")).sendKeys("5000");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeGoodsAndServices:Quantity_input")).sendKeys("30");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeGoodsAndServices:Value_input")).sendKeys("2000000");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeGoodsAndServices:VatToBeExempted_input")).sendKeys("0");
        Thread.sleep(500);
        driver.findElement(By.id("VatFreeGoodsAndServices:Ok")).click();
        switchToDefault();
        wait(35).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Cement']"))).isDisplayed();
    }


    @Then("Enter procedue code number")
    public void enterProcedueCodeNumber() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:GoodsEligibleEntryUnderCustoms_input")).sendKeys("46");
    }

    @Then("Enter exclusive use for")
    public void enterExclusiveUseFor() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:ForExclusiveUse")).sendKeys("Malawi");
    }

    @Then("Enter place of use")
    public void enterPlaceOfUse() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:Placeofuse")).sendKeys("Malawi");
    }

    @Then("Enter physical address")
    public void enterPhysicalAddress() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:PhysicalAddress")).sendKeys("Malawi");
    }

    @Then("Enter amount paid")
    public void enterAmountPaid() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:AmountPaid_input")).sendKeys("50000");
    }

    @Then("Enter receipt number")
    public void enterReceiptNumber() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:RecieptNumber")).sendKeys(getRandom(5));
    }

    @Then("Select bills of quantities certified for vat five")
    public void selectBillsOfQuantitiesCertifiedForVatFive() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"VatFiveTaxCertificateApplication:BillsofQuantityCertified\"]/div[3]")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//li[text()='Yes']")).click();
    }

    @Then("Select bills of quantities certified by {string}")
    public void selectBillsOfQuantitiesCertifiedBy(String arg0) throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:findTin")).click();
        switchToBoFrame();
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(arg0);
        Thread.sleep(100);
        driver.findElement(By.xpath("//button[span='Search']")).click();
        Thread.sleep(3000);
        switchToDefault();
        driver.findElement(By.id("VatFiveTaxCertificateApplication:Qualifications")).sendKeys("Accountant");
    }

    @Then("Add attachment details for vat five")
    public void addAttachmentDetailsForVatFive() throws InterruptedException {
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"VatFiveTaxCertificateApplication:DocumentType\"]/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//li[text()='National ID or Passport of Authorising']")).click();
        Thread.sleep(300);
        String path = System.getProperty("user.dir") + File.separator + "src\\test\\resources\\" + File.separator + "test.png";
        driver.findElement(By.id("VatFiveTaxCertificateApplication:AttachmentPath_input")).sendKeys(path);
        Thread.sleep(300);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:DocReference")).sendKeys(getRandom(5));
        Thread.sleep(300);
    }

    @Then("Enter details of person authorizing application")
    public void enterDetailsOfPersonAuthorizingApplication() throws InterruptedException {
        driver.findElement(By.id("VatFiveTaxCertificateApplication:FullName")).sendKeys("Mr John Kimathi");
        Thread.sleep(200);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:Designation")).sendKeys("Software Engineer");
        Thread.sleep(200);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:IdentificationNumber")).sendKeys(getRandom(8));
    }

    @Then("Enter details of person making application for vat five")
    public void enterDetailsOfPersonMakingApplicationForVatFive() throws InterruptedException {
        Thread.sleep(200);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:PersonName")).sendKeys("Margie Wambui");
        Thread.sleep(200);
        driver.findElement(By.id("VatFiveTaxCertificateApplication:PersonDesig")).sendKeys("Doctor");
        Thread.sleep(200);
        WebElement date = driver.findElement(By.id("VatFiveTaxCertificateApplication:PersonDate_input"));
        date.click();
        date.sendKeys(Keys.ENTER);
        Thread.sleep(100);
        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(200);
    }

    @Then("Submit vat five project application")
    public void submitVatFiveProjectApplication() {
        driver.findElement(By.xpath("//button[span='Submit']")).click();
    }

    @Then("Obtain reference number for VAT FIVE {string}")
    public void obtainReferenceNumberForVATFIVE(String arg0) {
        String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + arg0 + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - MRA/BAL/CR/004741
        sharedatastep.VatFiveRef = FullMessage.substring(42);
        System.out.println(sharedatastep.VatFiveRef);
    }

    @And("enters VAT FIVE certificate reference number in search results")
    public void entersVATFIVECertificateReferenceNumberInSearchResults() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));

        search.clear();
        Thread.sleep(1000);

//        search.sendKeys("*VF00000061");
        search.sendKeys("*"+sharedatastep.VatFiveRef);
        Thread.sleep(5000);
        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @Then("Click cancel project")
    public void clickCancelProject() {
        driver.findElement(By.xpath("//button[span='Cancel Project']")).click();
    }

    @Then("Select cancellation reason as {string}")
    public void selectCancellationReasonAs(String arg0) throws InterruptedException {
        wait(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"VatFreeProject:CancelReason\"]/div[3]"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//li[text()='"+arg0+"']")).click();
    }
}



