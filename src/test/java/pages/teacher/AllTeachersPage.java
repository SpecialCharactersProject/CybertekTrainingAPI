package pages.teacher;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AllTeachersPage {

    public AllTeachersPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //DELETING A TEACHER (Kani)
    @FindBy(linkText = "Teachers")
    public WebElement teacherModule;
    @FindBy(xpath = "//ul[@class='list-unstyled']/li[1]")
    public WebElement allTeachersModule;
    @FindBy(xpath = "//a[@class='action-icon dropdown-toggle']")
    public WebElement threeDotsign;
    @FindBy(xpath = "(//a[@data-toggle='modal'])[1]")
    public WebElement deleteOption;
    @FindBy(xpath = "(//div[@class='modal-body card-box']/p)")
    public WebElement confirmMessage;
    @FindBy(xpath = "(//button[@type='submit'])")
    public WebElement deleteSubmit;
    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement teachernamesearchInput;
    @FindBy(xpath = "(//div[@class='col-sm-6 col-md-3']/a)")
    public WebElement searchBtn;
    @FindBy(linkText = "R")
    public WebElement teacherProfile;
    @FindBy(xpath = "(//*[contains(text(), 'Profile')])[5]")
    public WebElement myProfileHeader;
    @FindBy(xpath = "(//span[@class='text']/a)[1]")
    public WebElement teacherId;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement searchtecaherIDBtnInput;
}
