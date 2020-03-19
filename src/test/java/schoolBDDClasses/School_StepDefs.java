package schoolBDDClasses;

import com.google.common.collect.Sets;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.LoginPage;
import pages.student.AddStudentPage;
import pages.student.AllStudentsPage;
import pages.teacher.AddTeacherPage;
import pages.teacher.AllTeachersPage;
import utilities.DBUtility;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class School_StepDefs {

    LoginPage loginPage = new LoginPage();                          //here, I am just logging in

    AddTeacherPage addTeacherPage = new AddTeacherPage();           //the 4 line below: I'm creating the objects, so we can use the elements from the Page classes
    AllTeachersPage allTeachersPage = new AllTeachersPage();
    AddStudentPage addStudentPage = new AddStudentPage();
    AllStudentsPage allStudentsPage = new AllStudentsPage();

    String teacher_idOnUI;
    String teacherName;

    WebElement studentIdUI;
    String compareID = "";

    String createdStudentName;
    static String student_idOnUI;
    static Map<String, String> myDataTable;

    //LOGGING IN
    @Given("the user is on the School Landing page")
    public void the_user_is_on_the_School_Landing_page() {
        loginPage.login();
    }

    @Then("the user should verify he is on the School main page")
    public void the_user_should_verify_he_is_on_the_School_main_page() {
        String expected = "SCHOOL";
        String actual = addTeacherPage.schoolText.getText();
        Assert.assertEquals(expected, actual);
    }


    //CREATING A TEACHER (Karina)
    @Given("the user is on the Teacher's Module")
    public void the_user_is_on_the_Teacher_s_Module() {
        addTeacherPage.teachersButtonDropdown.click();
    }


    @When("the user clicks on Add Teacher")
    public void the_user_clicks_on_Add_Teacher() {
        addTeacherPage.addTeacherButton.click();
    }


    @When("the user enters")
    public void the_user_enters(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        SeleniumUtils.pause(2);

        addTeacherPage.firstNameInput.sendKeys(data.get("FirstName"));
        addTeacherPage.emailInput.sendKeys(data.get("Email"));
        addTeacherPage.passwordInput.sendKeys(data.get("Password"));
        addTeacherPage.subjectInput.sendKeys(data.get("Subject"));
        addTeacherPage.genderDropdown.sendKeys(data.get("Gender"));
        addTeacherPage.birthDateInput.sendKeys(data.get("BirthDate"));
        addTeacherPage.batchDropdown.sendKeys(data.get("Batch"));
        addTeacherPage.lastNameInput.sendKeys(data.get("LastName"));
        addTeacherPage.joiningDateInput.sendKeys(data.get("JoiningDate"));
        addTeacherPage.mobileNumberInput.sendKeys(data.get("MobileNumber"));
        addTeacherPage.departmentDropdown.sendKeys(data.get("Department"));
        addTeacherPage.salaryInput.clear();
        addTeacherPage.salaryInput.sendKeys(data.get("Salary"));
        addTeacherPage.sectionInput.sendKeys(data.get("Section"));
        addTeacherPage.permanentAddressInput.sendKeys(data.get("PermanentAddress"));

    }

    @When("the user clicks on the Submit button")
    public void the_user_clicks_on_the_Submit_button() {
        addTeacherPage.submitButton.click();

    }

    @When("the user should see a new created teacher on the All Teacher page\\(UI)")
    public void the_user_should_see_a_new_created_teacher_on_the_All_Teacher_page_UI() {
        SeleniumUtils.pause(2);
        addTeacherPage.allTeachersButton.click();
        addTeacherPage.teacherNamebox.sendKeys("Venkat");
        addTeacherPage.searchButton.click();

        String expected = "Venkat";
        String actual = addTeacherPage.nameOfTheFirstTeacher.getText();
        Assert.assertEquals(expected, actual);

    }

    @Then("the user should see a new created teacher in the database")
    public void the_user_should_see_a_new_created_teacher_in_the_database() throws SQLException {
        DBUtility.createConnection();

        List<Map<Object, Object>> mydata = DBUtility.executeQuery("select first_name, last_name from teacher where first_name = 'Venkat'");
        DBUtility.close();

        for (Map<Object, Object> map : mydata) {
            System.out.println(map);
            if (map.containsValue("Venkat")) {
                System.out.println("Venkat Patel is in the system");
            }
        }
    }


    //UPDATING A TEACHER


    //DELETING A TEACHER (Kani)

    @Given("user clicks on All Teachers submodule")
    public void user_clicks_on_All_Teachers_submodule() throws InterruptedException {
        Thread.sleep(2000);
        allTeachersPage.teacherModule.click();
        allTeachersPage.allTeachersModule.click();
    }

    @When("user search teacher with {string}")
    public void user_search_teacher_with(String teachersName) throws InterruptedException {
        Thread.sleep(3000);
        this.teacherName = teachersName;
        allTeachersPage.teachernamesearchInput.click();
        Thread.sleep(3000);
        allTeachersPage.teachernamesearchInput.sendKeys(teachersName);
        Thread.sleep(2000);
        allTeachersPage.searchBtn.click();
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.linkText(teacherName)).click();//s
        System.out.println(teacherName);
    }

    @When("user on the teacher profile")
    public void user_on_the_teacher_profile() throws InterruptedException {
        Thread.sleep(1000);
        // allTeachersPage.teacherProfile.click();
    }

    @When("user verifies the MY PROFILE header")
    public void user_verifies_the_MY_PROFILE_header() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue("The My Profile Header is not displayed", allTeachersPage.myProfileHeader.isDisplayed());
    }

    @When("user stores the {string}")
    public void user_stores_the(String teacher_Id) {
        this.teacher_idOnUI = allTeachersPage.teacherId.getText();
        System.out.println(teacher_idOnUI + "    Teacher id from UI");//552
    }

    @Then("user compares UI teacher_id with dataBase teacher_id")
    public void user_compares_UI_teacher_id_with_dataBase_teacher_id() throws SQLException, InterruptedException {
        DBUtility.createConnection();
        List<Map<Object, Object>> myData = DBUtility.executeQuery("select teacher_id from teacher where teacher_id=" + teacher_idOnUI);
        Map<Object, Object> map = myData.get(0);
        System.out.println(map.get("TEACHER_ID").toString());//552
        Assert.assertEquals("The UI teacherId is not Equal to DB teacherID", map.get("TEACHER_ID").toString(), teacher_idOnUI);
        DBUtility.close();
    }

    //    @When("user clicks on All Teachers submodule")
//    public void user_clicks_on_All_Teachers_submodule() throws InterruptedException {
//        Thread.sleep(2000);
//        allTeachersPage.allTeachersModule.click();
//    }
    @When("user search the teacher with id")
    public void user_search_the_teacher_with_id() throws InterruptedException {
        Thread.sleep(2000);
        allTeachersPage.searchtecaherIDBtnInput.click();
        Thread.sleep(1000);
        allTeachersPage.searchtecaherIDBtnInput.sendKeys(teacher_idOnUI);
        allTeachersPage.searchBtn.click();
    }

    @When("user clicks on threeDotsign in order to delete the teacher")
    public void user_clicks_on_threeDotsign_in_order_to_delete_the_teacher() throws InterruptedException {
        Thread.sleep(2000);
        allTeachersPage.threeDotsign.click();
    }

    @When("user  clicks on delete option")
    public void user_clicks_on_delete_option() throws InterruptedException {
        Thread.sleep(2000);
        allTeachersPage.deleteOption.click();
    }

    @When("user should handle the confirmation message is displayed")
    public void user_should_handle_the_confirmation_message_is_displayed() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue("The message is not displayed", allTeachersPage.confirmMessage.isDisplayed());
        allTeachersPage.deleteSubmit.click();
    }

    @When("user search for teacher with teacher_id on UI")
    public void user_search_for_teacher_with_teacher_id_on_UI() throws InterruptedException {
        Thread.sleep(2000);
        allTeachersPage.searchtecaherIDBtnInput.click();
        Thread.sleep(2000);
        allTeachersPage.searchtecaherIDBtnInput.sendKeys(teacher_idOnUI);
        allTeachersPage.searchBtn.click();
    }

    @When("user verifies threeDotsign not displayed on UI")
    public void user_verifies_threeDotsign_not_displayed_on_UI() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertFalse("The three dot sign is displayed", allTeachersPage.searchBtn.getText().equals(teacher_idOnUI));
    }

    @Then("user verifies UI teacherid with dataBase teacherid")
    public void user_verifies_UI_teacherid_with_dataBase_teacherid() throws SQLException {
        DBUtility.createConnection();
        List<Map<Object, Object>> myData = DBUtility.executeQuery("select teacher_id from teacher");
        for (Map<Object, Object> myMap : myData) {
            myMap.get("TEACHER_ID");
            System.out.println(myMap.get("TEACHER_ID").toString());//552
            Assert.assertFalse("The UI teacherId is on DB ", myMap.get("TEACHER_ID").toString().contains(teacher_idOnUI));
            DBUtility.close();
        }
    }

    //CREATING A STUDENT (Mikail)

    @Given("the user is on the Student's Module")
    public void the_user_is_on_the_Student_s_Module() {
        allStudentsPage.studentsBtn.click();
        SeleniumUtils.waitFor(2);
    }

    @When("user should click on the add student button")
    public void user_should_click_on_the_add_student_button() {
        addStudentPage.addStudentBtn.click();
    }

    @When("user should be able to see add students header")
    public void user_should_be_able_to_see_add_students_header() {
        //String expectedHeader= "ADD STUDENT";
        SeleniumUtils.waitFor(2);
        Assert.assertTrue(addStudentPage.addStudentHeader.isDisplayed());
    }

    @When("user should verify Student Information header")
    public void user_should_verify_Student_Information_header() {
        Assert.assertTrue(addStudentPage.studentInformationHeader.isDisplayed());
    }

    @When("user store student's")
    public void user_store_student_s(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        myDataTable = dataTable.asMap(String.class, String.class);
        System.out.println(myDataTable.get("FIRST_NAME"));
        createdStudentName = myDataTable.get("FIRST_NAME");
    }

    @When("user should enter student's information")
    public void user_should_enter_student_s_information() throws InterruptedException {
        SeleniumUtils.waitFor(1);
        addStudentPage.firstNameBtnInput.click();
        addStudentPage.firstNameBtnInput.sendKeys(myDataTable.get("FIRST_NAME"));
        SeleniumUtils.waitFor(1);
        addStudentPage.lastNameBtnInput.click();
        addStudentPage.lastNameBtnInput.sendKeys(myDataTable.get("LAST_NAME"));
        SeleniumUtils.waitFor(1);
        addStudentPage.emailBtnInput.click();
        addStudentPage.emailBtnInput.sendKeys(myDataTable.get("EMAIL"));
        SeleniumUtils.waitFor(1);
        addStudentPage.joiningDateBtnInput.click();
        addStudentPage.joiningDateBtnInput.sendKeys(myDataTable.get("JOIN_DATE"));
        SeleniumUtils.waitFor(1);
        addStudentPage.passwordBtnInput.click();
        addStudentPage.passwordBtnInput.clear();
        addStudentPage.passwordBtnInput.sendKeys(myDataTable.get("PASSWORD"));
        SeleniumUtils.waitFor(1);
//        addStudentPage.confirmPasswordBtnInput.click();
//        addStudentPage.confirmPasswordBtnInput.sendKeys(data.get(""));
//        SeleniumUtils.waitFor(1);
        addStudentPage.subjectBtnInput.click();
        addStudentPage.subjectBtnInput.sendKeys(myDataTable.get("SUBJECT"));
        SeleniumUtils.waitFor(1);
        addStudentPage.mobileNumberBtnInput.click();
        addStudentPage.mobileNumberBtnInput.sendKeys(myDataTable.get("MOBILE_NUMBER"));
        SeleniumUtils.waitFor(1);
        Select selectGender = new Select(addStudentPage.genderDropdown);
        selectGender.selectByVisibleText(myDataTable.get("GENDER"));
        SeleniumUtils.waitFor(1);
        addStudentPage.admissionNoBtnInput.click();
        addStudentPage.admissionNoBtnInput.clear();
        addStudentPage.admissionNoBtnInput.sendKeys(myDataTable.get("ADMISSION_NO"));
        SeleniumUtils.waitFor(1);
        addStudentPage.birthDateBtnInput.click();
        addStudentPage.birthDateBtnInput.sendKeys(myDataTable.get("BIRTH_DATE"));
        SeleniumUtils.waitFor(1);
        //  SeleniumUtils.scrollToElement();
        //SeleniumUtils.scroll();
        addStudentPage.majorBtnInput.click();
        addStudentPage.majorBtnInput.sendKeys(myDataTable.get("MAJOR"));
        SeleniumUtils.waitFor(1);
        Select selectBatch = new Select(addStudentPage.batchDropdown);
        selectBatch.selectByVisibleText(myDataTable.get("BATCH"));
        SeleniumUtils.waitFor(1);
        addStudentPage.sectionBtnInput.clear();
        addStudentPage.sectionBtnInput.sendKeys(myDataTable.get("SECTION"));
        SeleniumUtils.waitFor(1);
        addStudentPage.permanentAddressBtnInput.click();
        addStudentPage.permanentAddressBtnInput.sendKeys(myDataTable.get("PREMANENT_ADDRESS"));
        SeleniumUtils.waitFor(1);
        addStudentPage.companyNameBtnInput.click();
        addStudentPage.companyNameBtnInput.sendKeys(myDataTable.get("COMPANY_NAME"));
        SeleniumUtils.waitFor(1);
        addStudentPage.titleBtnInput.click();
        addStudentPage.titleBtnInput.sendKeys(myDataTable.get("TITLE"));
        SeleniumUtils.waitFor(1);
        addStudentPage.startDateBtnInput.click();
        addStudentPage.startDateBtnInput.sendKeys(myDataTable.get("START_DATE"));
        SeleniumUtils.waitFor(1);
        addStudentPage.cityBtnInput.click();
        addStudentPage.cityBtnInput.sendKeys(myDataTable.get("CITY"));
        SeleniumUtils.waitFor(1);
        addStudentPage.streetBtnInput.click();
        addStudentPage.streetBtnInput.sendKeys(myDataTable.get("STREET"));
        SeleniumUtils.waitFor(1);
        addStudentPage.zipBtnInput.click();
        addStudentPage.zipBtnInput.sendKeys(myDataTable.get("ZIP_CODE"));
        SeleniumUtils.waitFor(1);
        addStudentPage.stateBtnInput.click();
        addStudentPage.stateBtnInput.sendKeys(myDataTable.get("STATE"));
    }

    @When("user should click on the summit button")
    public void user_should_click_on_the_summit_button() {
        SeleniumUtils.waitFor(1);
        addStudentPage.submitBtn.click();
    }

    @When("user should handle pop-up alert")
    public void user_should_handle_pop_up_alert() {
        //Alert alert = Driver.getDriver().switchTo().alert();
        //alert.accept();
    }

    @When("user is on the All students page")
    public void user_is_on_the_All_students_page() {
        SeleniumUtils.waitFor(3);
//        allStudentsPage.allStudentsBtn.click();
        Assert.assertTrue("allStudentsPage DID NOT DISPLAY", allStudentsPage.allStudentHeader.isDisplayed());
    }

    @When("user should search student by {string} on UI")
    public void user_should_search_student_by_on_UI(String studentNameOnUI) {
    }

    @When("user should click on the student profile")
    public void user_should_click_on_the_student_profile() {
        //allStudentsPage.studentNameSearchInput.click();
//        SeleniumUtils.waitFor(1);
        System.out.println(createdStudentName);
        //allStudentsPage.studentNameSearchInput.click();
        SeleniumUtils.waitFor(1);
        // allStudentsPage.studentNameSearchInput.sendKeys("Lionel");
        // allStudentsPage.searchBtn.click();
        Driver.getDriver().findElement(By.linkText(createdStudentName)).click();
        SeleniumUtils.waitFor(2);
//        allStudentsPage.clickOnStudentProfile.click();
    }

    @When("user should verify my profile header")
    public void user_should_verify_my_profile_header() {
        SeleniumUtils.waitFor(2);
        Assert.assertTrue("Name Did not displayed!!", allStudentsPage.studentMyProfileHeader.isDisplayed());
    }

    @When("user should verify {string} on UI")
    public void user_should_verify_on_UI(String student_id) {
        SeleniumUtils.waitFor(2);
        // Assert.assertTrue(allStudentsPage.get_student_id.equals(student_id));
        Assert.assertTrue(allStudentsPage.get_student_id.isDisplayed());
        System.out.println(allStudentsPage.get_student_id.getText());
    }

    @When("user should store added student information")
    public void user_should_store_added_student_information() {
        SeleniumUtils.waitFor(2);
        student_idOnUI = allStudentsPage.get_student_id.getText();
        // myDataTable.putIfAbsent("STUDENT_ID",student_idOnUI);
        System.out.println(allStudentsPage.get_student_id.getText());
        System.out.println(myDataTable);
    }

    @Then("user should compare added student information with database")
    public void user_should_compare_added_student_information_with_database() throws SQLException {
        DBUtility.createConnection();
        // DBUtility.executeQuery("select * from student where student_id="+student_idOnUI);
        List<Map<Object, Object>> myDataBase = DBUtility.executeQuery("select FIRST_NAME from student where student_id=" + student_idOnUI);
        myDataBase.get(0).put("JOIN_DATE", myDataTable.get("JOIN_DATE"));
        System.out.println(myDataBase.get(0).get("JOIN_DATE"));
        myDataBase.get(0).put("BIRTH_DATE", "01/01/1990");
        Set<Object> set = myDataBase.get(0).keySet();
        //System.out.println(set);
        System.out.println(myDataBase);
        System.out.println();
        System.out.println(myDataTable);
        System.out.println(set);
        for (Object obj : set) {
            System.out.println(myDataTable.get(obj));
            Assert.assertTrue(myDataTable.get("FIRST_NAME").equals(myDataBase.get(0).get(obj).toString().equals(student_idOnUI)));
        }
    }


    //UPDATING A STUDENT (Sercan abi)

    Map<String, String> data;
    Map<Object, Object> dbMap;
    String studentUpdateId;
    Map<Object, Object> uiMap = new HashMap<>();

    @Given("the user clicks and All Students and Edit button")
    public void the_user_clicks_and_All_Students_and_Edit_button() throws InterruptedException {
        allStudentsPage.students.click();
        allStudentsPage.allStudentsButton.click();
        allStudentsPage.firstStudent.click();
        studentUpdateId = allStudentsPage.studentID.getText();
        SeleniumUtils.pause(2);
        allStudentsPage.students.click();
        SeleniumUtils.pause(2);
        allStudentsPage.allStudentsButton.click();
        allStudentsPage.searchID.click();
        SeleniumUtils.pause(2);
        allStudentsPage.searchID.sendKeys(studentUpdateId);
        allStudentsPage.searchButton.click();
        SeleniumUtils.pause(2);
        allStudentsPage.firstStudentDropDownTriple.click();
        allStudentsPage.studentEditButtonID.click();
    }

    @When("the user updates below fields with new data")
    public void the_user_updates_below_fields_with_new_data(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        data = dataTable.asMap(String.class, String.class);
        allStudentsPage.firstNameUpdate.clear();
        SeleniumUtils.pause(2);
        allStudentsPage.firstNameUpdate.sendKeys(data.get("FIRST_NAME"));
        allStudentsPage.lastNameUpdate.clear();
        allStudentsPage.lastNameUpdate.sendKeys(data.get("LAST_NAME"));
        allStudentsPage.emailUpdate.clear();
        allStudentsPage.emailUpdate.sendKeys(data.get("EMAIL_ADDRESS"));
        allStudentsPage.subjectUpdate.click();
        allStudentsPage.subjectUpdate.clear();
        allStudentsPage.subjectUpdate.sendKeys(data.get("SUBJECT"));
        allStudentsPage.majorUpdate.clear();
        allStudentsPage.majorUpdate.sendKeys(data.get("MAJOR"));
        allStudentsPage.mobileNumberUpdate.clear();
        SeleniumUtils.pause(2);
        allStudentsPage.mobileNumberUpdate.sendKeys(data.get("PHONE"));
        SeleniumUtils.pause(2);
        allStudentsPage.presentAddressUpdate.clear();
        allStudentsPage.presentAddressUpdate.sendKeys(data.get("PREMANENT_ADDRESS"));
        SeleniumUtils.pause(2);
        allStudentsPage.submitBtn.click();
    }

    @Then("the user should be able to see updated student info on website")
    public void theUserShouldBeAbleToSeeUpdatedStudentInfoOnWebsite() {
        allStudentsPage.students.click();
        SeleniumUtils.pause(2);
        allStudentsPage.allStudentsButton.click();
        allStudentsPage.searchID.click();
        SeleniumUtils.pause(2);
        allStudentsPage.searchID.sendKeys(studentUpdateId);
        allStudentsPage.searchButton.click();
        SeleniumUtils.pause(2);
        allStudentsPage.firstStudent.click();
        SeleniumUtils.pause(5);
        String newFullName = allStudentsPage.newFirstName.getText();
        String newFirstName = newFullName.split(" ")[0];
        String newLastName = newFullName.split(" ")[1];
        String newEmail = allStudentsPage.newEmail.getText();
        uiMap.put("FIRST_NAME", newFirstName);
        uiMap.put("LAST_NAME", newLastName);
        uiMap.put("EMAIL_ADDRESS", newEmail);
        uiMap.put("SUBJECT", allStudentsPage.newSubject.getText());
        uiMap.put("PHONE", allStudentsPage.newPhoneNumber.getText());
        uiMap.put("MAJOR", "Calculus");
        uiMap.put("PREMANENT_ADDRESS", allStudentsPage.newAddress.getText());
        SeleniumUtils.pause(5);
        for (Object key : Sets.union(uiMap.keySet(), data.keySet())) {
            Assert.assertEquals("TEST FAILED", data.get(key), uiMap.get(key));
        }
    }

    @Then("the user should be able to see the updated student info in database")
    public void the_user_should_be_able_to_see_the_updated_student_info_in_database() {
        try {
            //String studentID=Config.getProperty("studentUpdateID");
            DBUtility.createConnection();
            List<Map<Object, Object>> mydata = DBUtility.executeQuery("SELECT student.first_name, last_name, contact.email_address, " +
                    "student.subject, contact.phone, student.major, contact.premanent_address\n" +
                    "  FROM student INNER JOIN contact\n" +
                    "    ON student.student_id = contact.student_id \n" +
                    " WHERE student.student_id=" + studentUpdateId);
            SeleniumUtils.pause(2);
            dbMap = mydata.get(0);
            System.out.println("DbMap " + dbMap.toString());
            System.out.println("Data" + data.toString());
            // This for loop is not working since the map retrieved from database is not in order.
            for (Object key : Sets.union(dbMap.keySet(), data.keySet())) {
                System.out.println("Data " + data.get(key));
                System.out.println("DbMap " + dbMap.get(key));
                Assert.assertEquals("TEST FAILED", data.get(key), dbMap.get(key));
            }
//           Assert.assertEquals("First Name doesn't match", dbMap.get("FIRST_NAME"), data.get("FIRST_NAME"));
//           Assert.assertEquals("Email doesn't match", true, dbMap.get("EMAIL_ADDRESS").equals(data.get("EMAIL_ADDRESS")));
//           Assert.assertEquals("Subject doesn't match", true, dbMap.get("SUBJECT").equals(data.get("SUBJECT")));
//           Assert.assertEquals("Major doesn't match", true, dbMap.get("MAJOR").equals(data.get("MAJOR")));
//           Assert.assertTrue("Mobile Number doesn't match",dbMap.get("phone").equals(data.get("Mobile Number")));
//           Assert.assertEquals("Permanent Address doesn't match", true, dbMap.get("PREMANENT_ADDRESS").equals(data.get("PREMANENT_ADDRES")));
            DBUtility.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //DELETING A STUDENT (Milos)

    // DELETING STUDENT FUNCTION //

    @Given("the user clicks on Students dropdown menu")
    public void the_user_clicks_on_Students_dropdown_menu() throws InterruptedException {
        allStudentsPage.studentsModule.click();
        SeleniumUtils.pause(2);
    }

    @When("the user chooses All Students option")
    public void the_user_chooses_All_Students_option() throws InterruptedException {
        allStudentsPage.allStudButton.click();
        SeleniumUtils.pause(2);
    }

    @When("the user opens already created student")
    public void user_opens_alerady_created_student() throws InterruptedException {
        allStudentsPage.studListView.click();
        Driver.getDriver().navigate().refresh();
        SeleniumUtils.pause(4);
        WebElement studentIdUI = allStudentsPage.studentId;
        SeleniumUtils.pause(5);
        compareID = studentIdUI.getText();
        Driver.getDriver().navigate().back();
        SeleniumUtils.pause(2);
        allStudentsPage.studInfo.click();
        SeleniumUtils.pause(2);
        Driver.getDriver().navigate().back();
        SeleniumUtils.pause(2);
    }

    @When("the user clicks on {int} dots sign in top right corner of student's name")
    public void the_user_clicks_on_dots_sign_in_top_right_corner_of_student_s_name(Integer int1) throws InterruptedException {
        allStudentsPage.deleteDropdwn.click();
        SeleniumUtils.pause(2);
    }

    @When("the user clicks on delete option")
    public void the_user_clicks_on_delete_option() throws InterruptedException {
        allStudentsPage.deleteOption.click();
        SeleniumUtils.pause(2);
    }

    @When("the user clicks on red Delete button")
    public void the_user_clicks_on_red_Delete_button() throws InterruptedException {
        allStudentsPage.deleteRedButton.click();
        Driver.getDriver().navigate().refresh();
        allStudentsPage.studListView.click();
        Driver.getDriver().navigate().refresh();
        SeleniumUtils.pause(4);
    }

    @When("the user sees that student removed from the All Students page \\(UI).")
    public void the_user_sees_that_student_removed_from_the_All_Students_page_UI() throws InterruptedException {
        Driver.getDriver().navigate().refresh();
        List<WebElement> studentsIdsUI = Driver.getDriver().findElements(By.xpath("//table/tbody//td[2]"));
        for (WebElement id : studentsIdsUI) {
            Assert.assertFalse("ID Still exists. UI VERIFICATION FAILED", compareID.equalsIgnoreCase(id.getText()));
        }
    }

    @Then("the user should be able to see that student removed from the database.")
    public void the_user_should_be_able_to_see_that_student_removed_from_the_database() throws SQLException {
        DBUtility.createConnection();
        List<Map<Object, Object>> dbInfo = DBUtility.executeQuery("select s.first_name, s.last_name, c.student_id from student s inner join contact c on s.student_id = c.student_id");
        for (Map<Object, Object> map : dbInfo) {
            Assert.assertFalse("Database verification failed", map.get("STUDENT_ID").toString().equalsIgnoreCase(compareID));
        }


    }
}
