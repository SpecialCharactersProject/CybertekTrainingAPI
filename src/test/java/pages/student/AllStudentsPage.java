package pages.student;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AllStudentsPage {
    public AllStudentsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //CREATING A STUDENT (Mikail)

    @FindBy(linkText = "Students")
    public WebElement studentsBtn;
    @FindBy(linkText = "All Students")
    public WebElement allStudentsBtn;
    @FindBy(linkText = "Search")
    public WebElement searchBtn;
    @FindBy(xpath = "(//div[@class='form-group custom-mt-form-group'])[2]")
    public WebElement studentNameSearchInput;
    @FindBy(xpath =  "//h5[@class='text-uppercase']")
    public WebElement studentMyProfileHeader;
    @FindBy(xpath = "//span[@class='text'][1]")
    public WebElement get_student_id;
    @FindBy(linkText = "R")
    public WebElement clickOnStudentProfile;
    @FindBy(xpath = "//h5")
    public WebElement allStudentHeader;



    //UPDATING STUDENT (Sercan abi)

    @FindBy(xpath = "(//span[@class='menu-arrow'])[2]")
    public WebElement students;
    //@FindBy(xpath = "//a[@href='all-students.html']")
    @FindBy(xpath="//a[contains(text(), 'All Students')]")
    public WebElement allStudentsButton;
    @FindBy(xpath = "(//a[(text()= 'R')])[1]")
    public WebElement firstStudent;
    @FindBy(xpath = "(//ul/li/span/a)[1]")
    public WebElement studentID;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement searchID;
    @FindBy(css="a[class='btn btn-success btn-block mt-4 mb-2']")
    public WebElement searchButton;
    @FindBy(xpath = "(//a[@href='#' and @data-toggle='dropdown'])[4] ")
    public WebElement firstStudentDropDownTriple;
    @FindBy(xpath = "(//a[contains(text(), 'Edit')])[5]")
    public WebElement studentEditButtonID;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement firstNameUpdate;
    @FindBy(xpath = "(//input[@type='text'])[5]")
    public WebElement lastNameUpdate;
    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement emailUpdate;
    @FindBy(xpath = "(//input[@type='text'])[3]")
    public WebElement subjectUpdate;
    @FindBy(xpath="(//input[@type='number'])[1]")
    public WebElement mobileNumberUpdate;
    @FindBy(xpath = "(//input[@type='text'])[8]")
    public WebElement majorUpdate;
    @FindBy(xpath = "//textarea[@placeholder='Present Address']")
    public WebElement presentAddressUpdate;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;
    @FindBy(css = ".user-name")
    public WebElement newFirstName;
    @FindBy(xpath = "//span[contains(text(), 'Phone:')]/following-sibling::span/a" )
    public WebElement newPhoneNumber;
    @FindBy(xpath = "//span[contains(text(), 'Email')]/following-sibling::span/a")
    public WebElement newEmail;
    @FindBy (xpath = "//span[contains(text(), 'Subject')]/following-sibling::span")
    public WebElement newSubject;
    @FindBy(xpath = "(//span[contains(text(), 'Address')]/following-sibling::span)[1]")
    public WebElement newAddress;

    //DELETING A STUDENT (Milos)
    @FindBy(xpath = "(//a[@href='#'])[5]")
    public WebElement studentsModule;
    @FindBy(linkText = "All Students")
    public WebElement allStudButton;
    @FindBy( linkText = "Add Student")
    public WebElement addStudentButton;
    @FindBy( xpath = "(//a[@class='action-icon dropdown-toggle'])[1]")
    public WebElement deleteDropdwn;
    @FindBy( xpath = "(//a[@data-target = '#delete_employee'])[1]")
    public WebElement deleteOption;
    @FindBy( xpath = "//button[@class = 'btn btn-danger']")
    public WebElement deleteRedButton;
    @FindBy( xpath = "(//table/tbody//td[2])[1]")
    public WebElement studentId;
    @FindBy( xpath = " //a[@class ='list-view btn btn-link']")
    public WebElement studListView;
    @FindBy( xpath = "(//div[@class='profile-widget'])[1]")
    public WebElement studInfo;

}
