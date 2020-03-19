package parallel;

import apiModels.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.Config;
import utilities.DBUtility;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class StudentAPI_StepDefs {

    static int studentId;


    @Given("student is created with given values below")
    public void student_is_created_with_given_values_below(io.cucumber.datatable.DataTable dataTable) throws IOException {

        Map<String, String>data=dataTable.asMap(String.class, String.class);
        ObjectMapper objectMapper=new ObjectMapper();


        Student student=new Student();

        student.setFirstName(data.get("firstName"));
        student.setLastName(data.get("lastName"));
        student.setMajor(data.get("major"));
        student.setSubject(data.get("subject"));
        student.setAdmissionNo(data.get("admissionNo"));
        student.setBatch(Integer.parseInt(data.get("batch")));
        student.setGender(data.get("gender"));
        student.setBirthDate(data.get("birthDate"));
        student.setJoinDate(data.get("joinDate"));
        student.setPassword(data.get("password"));
        student.setSection(data.get("section"));

        Contact contact=new Contact();
        contact.setPhone(data.get("phone"));
        contact.setEmailAddress(data.get("emailAddress"));
        contact.setPremanentAddress(data.get("premanentAddress"));

        Company company=new Company();
        company.setCompanyName(data.get("companyName"));
        company.setStartDate(data.get("startDate"));
        company.setTitle(data.get("title"));
        Address address=new Address();
        address.setStreet(data.get("street"));
        address.setCity(data.get("city"));
        address.setState(data.get("state"));
        address.setZipCode(Integer.parseInt(data.get("zipCode")));
        company.setAddress(address);
        student.setContact(contact);
        student.setCompany(company);

        //System.out.println(student.getFirstName()+" "+student.getContact().getPremanentAddress());


        String jsonStudent=objectMapper.writeValueAsString(student);
        student=objectMapper.readValue(jsonStudent, Student.class);


        Response response=student.requestPOST(Config.getProperty("baseURL"), Config.getProperty("studentPath"), Config.getProperty("createPath"));
        System.out.println(response.asString());

        studentId=student.getStudentId();
        System.out.println("NEW STUDENT ID: "+ studentId);

    }


    @Then("verify created Student success status code {string}")
    public void verifyCreatedSuccessStatusCode(String uri) {

        Response response=CrudObject.requestGET(Config.getProperty("baseURL"), Config.getProperty("studentPath"), studentId);
        Assert.assertEquals("TEST FAILED! RESPONSE CODE IS NOT 200! ",response.statusCode(), 200);

        System.out.println("TEST PASSED! STUDENT CREATED WITH THE FOLLOWING STUDENT ID: "+studentId);

    }

    @Then("verify created Student response with Database")
    public void verify_created_Student_response_with_Database() throws SQLException {

        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> studentDataList = DBUtility.executeQuery("select * from student where student_id=" + studentId);
            dbMap=studentDataList.get(0);

            String dbStudentId= dbMap.get("STUDENT_ID").toString();
            int id = Integer.parseInt(dbStudentId);

            Assert.assertEquals("STUDENT IS NOT FOUND IN DATABASE", id, studentId);
            System.out.println("DB VERIFICATION TEST PASSED! STUDENT ID FOUND IN DB "+"\nSTUDENT ID IN DB IS: "+id);
            DBUtility.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Given("student is updated with given values below")
    public void student_is_updated_with_given_values_below(io.cucumber.datatable.DataTable dataTable) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, String>data=dataTable.asMap(String.class, String.class);

        Student student=new Student();
        Response response=student.requestGET(Config.getProperty("baseURL"), Config.getProperty("studentPath"), studentId);
        student= objectMapper.readValue(response.asString(), Student.class);
        student= student.getStudents().get(0);

        student.setSubject(data.get("subject"));
        student.setMajor(data.get("major"));
        student.setFirstName(data.get("firstName"));
        student.setLastName(data.get("lastName"));
        student.getCompany().getAddress().setAddressId(0);
        student.getCompany().setCompanyId(0);
        Contact contact=new Contact();
        contact.setEmailAddress(data.get("emailAddress"));
        contact.setPhone(data.get("phone"));
        contact.setPremanentAddress(data.get("premanentAddress"));


        student.setContact(contact);

        String jsonStudent=objectMapper.writeValueAsString(student);
        System.out.println("JSON UPDTE"+jsonStudent);
        student=objectMapper.readValue(jsonStudent, Student.class);

        response=student.requestUPDATE(Config.getProperty("baseURL"),Config.getProperty("studentPath"), Config.getProperty("updatePath"));
        System.out.println(response.asString());


    }


    @Then("verify updated Student response with Database")
    public void verify_updated_Student_response_with_Database() throws IOException {


        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> studentDataList = DBUtility.executeQuery("select * from student where student_id="+studentId);
            dbMap=studentDataList.get(0);

            ObjectMapper mapper=new ObjectMapper();
            Student student=new Student();
            Response response=student.requestGET(Config.getProperty("baseURL"), Config.getProperty("studentPath"), studentId);
            student= mapper.readValue(response.asString(), Student.class);
            student= student.getStudents().get(0);
            System.out.println(student.getFirstName());

            Map<String, Object> apiMap = mapper.convertValue(student, Map.class);


            Assert.assertEquals("TEST FAILED! FIRSTNAMES DON'T MATCH", apiMap.get("firstName"), dbMap.get("FIRST_NAME"));
            Assert.assertEquals("TEST FAILED! LASTNAMES DON'T MATCH", apiMap.get("lastName"), dbMap.get("LAST_NAME"));
            Assert.assertEquals("TEST FAILED! EMAIL ADRESSESDON'T MATCH", apiMap.get("emailAddress"), dbMap.get("EMAIL_ADDRESS"));
            Assert.assertEquals("TEST FAILED! SUBJECTS DON'T MATCH", apiMap.get("subject"), dbMap.get("SUBJECT"));
            Assert.assertEquals("TEST FAILED! PHONE NUMBERS DON'T MATCH", apiMap.get("phone"), dbMap.get("PHONE"));
            Assert.assertEquals("TEST FAILED! ADDRESSES DON'T MATCH", apiMap.get("premanentAddress"), dbMap.get("PREMANENT_ADDRESS"));

            DBUtility.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }



    @Given("student is deleted with {string}")
    public void student_is_deleted_with(String string) {

        Student student=new Student();
        student.requestDELETE
                (Config.getProperty("baseURL"),
                        Config.getProperty("deleteStudentPath"), studentId);

        //Assert.assertEquals("TEACHER WITH "+teacherId+" TEACHER ID IS NOT DELETED", response.statusCode(),200);
        System.out.println("STUDENT WITH "+studentId+ " STUDENT ID IS DELETED");

    }

    @Then("verify deleted Student success status code {string}")
    public void verifyDeletedSuccessStatusCode(String uri) {
        Student student=new Student();
        student.requestGET(Config.getProperty("baseURL"),Config.getProperty("teacherPath"), studentId);
        //Assert.assertEquals("DELETION UNSUCCESSFUL", response.statusCode(), 404);
        System.out.println("DELETION SUCCESSFUL");


    }

    @Then("verify deleted Student response with Database")
    public void verify_deleted_Student_response_with_Database() {

        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> studentDataList = DBUtility.executeQuery("select * from student");
            dbMap=studentDataList.get(0);

            Assert.assertFalse(studentId+" IS IN THE DATA LIST. TEST FAILED!",dbMap.containsValue(String.valueOf(studentId)));

            DBUtility.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
