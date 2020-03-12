package pages.teacher;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AddTeacherPage {

    public AddTeacherPage () {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //CREATING A TEACHER (Karina)
    @FindBy (xpath = "//span[@class='text-uppercase']")
    public WebElement schoolText;
    @FindBy (xpath = "(//li[@class='submenu'])[1]")
    public WebElement teachersButtonDropdown;
    @FindBy (linkText = "All Teachers")
    public WebElement allTeachersButton;
    @FindBy (linkText = "Add Teacher")
    public WebElement addTeacherButton;
    @FindBy (xpath = "//label[contains(text(),'Firstname')]/preceding-sibling::*")
    public WebElement firstNameInput;
    @FindBy (xpath = "(//input[@type='text'])[2]")
    public WebElement emailInput;
    @FindBy (xpath = "(//input[@type='password'])[1]")
    public WebElement passwordInput;
    @FindBy (xpath = "(//input[@type='text'])[3]")
    public WebElement subjectInput;
    @FindBy (xpath = "(//div[@class='form-group custom-mt-form-group'])[5]//select")
    public WebElement genderDropdown;
    @FindBy (xpath = "(//input[@type='text'])[4]")
    public WebElement birthDateInput;
    @FindBy (xpath = "(//div[@class='form-group custom-mt-form-group'])[7]//select")
    public WebElement batchDropdown;
    @FindBy (xpath = "(//input[@type='text'])[5]")
    public WebElement lastNameInput;
    @FindBy (xpath = "(//input[@type='text'])[6]")
    public WebElement joiningDateInput;
    @FindBy (xpath = "//input[@type='number']")
    public WebElement mobileNumberInput;
    @FindBy (xpath = "(//div[@class='form-group custom-mt-form-group'])[12]/select")
    public WebElement departmentDropdown;
    @FindBy (xpath = "(//input[@type='text'])[7]")
    public WebElement salaryInput;
    @FindBy (xpath = "(//input[@type='text'])[8]")
    public WebElement sectionInput;
    @FindBy (css = "textarea#message")
    public WebElement permanentAddressInput;
    @FindBy (xpath = "//button[@type='submit']")
    public WebElement submitButton;
    @FindBy (xpath = "(//div[@class='form-group custom-mt-form-group']/input)[2]")
    public WebElement teacherNamebox;
    @FindBy (linkText = "SEARCH")
    public WebElement searchButton;
    @FindBy (xpath = "(//h4[@class='user-name m-t-10 m-b-0 text-ellipsis']/a)[1]")
    public WebElement nameOfTheFirstTeacher;











}
