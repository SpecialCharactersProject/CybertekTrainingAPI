package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

public LoginPage(){
    PageFactory.initElements(Driver.getDriver(),this);

}


public void login() {
    Driver.getDriver().get("http://cybertektraining.com/");
}

}
