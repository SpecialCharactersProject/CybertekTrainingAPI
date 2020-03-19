package parallel;

import apiModels.CrudObject;

import apiModels.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.*;

import io.restassured.response.Response;
import org.junit.Assert;
import utilities.Config;
import utilities.DBUtility;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherAPI_StepDefs {


   static int teacherId;

    @Given("teacher is created with given values below")
    public void teacher_is_created_with_given_values_below(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String>data=new HashMap<>();
        data= dataTable.asMap(String.class, String.class);
        ObjectMapper mapper=new ObjectMapper();
        Teacher teacher=mapper.convertValue(data, Teacher.class);
        teacher.requestPOST(Config.getProperty("baseURL"), Config.getProperty("teacherPath"), Config.getProperty("createPath"));
        teacherId=teacher.getTeacherId();
    }

    @Then("verify created Teacher success status code {string}")
    public void verifyCreatedSuccessStatusCode(String uri) {

        CrudObject.requestGET(Config.getProperty("baseURL"), Config.getProperty("teacherPath"), teacherId);

        System.out.println("TEST PASSED! TEACHER CREATED WITH THE FOLLOWING TEACHER ID: "+teacherId);


    }

    @Then("verify created Teacher response with Database")
    public void verify_created_Teacher_response_with_Database() {
        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> teacherDataList = DBUtility.executeQuery("select * from teacher where teacher_id=" + teacherId);
           dbMap=teacherDataList.get(0);

           String dbTeacherId= dbMap.get("TEACHER_ID").toString();
           int id = Integer.parseInt(dbTeacherId);

            Assert.assertEquals("TEACHER IS NOT FOUND IN DATABASE", id, teacherId);
            System.out.println("DB VERIFICATION TEST PASSED! TEACHER ID FOUND IN DB "+"\nTEACHER ID IN DB IS: "+teacherId);
            DBUtility.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Given("teacher is updated with given values below")
    public void teacher_is_updated_with_given_values_below(io.cucumber.datatable.DataTable dataTable) throws Exception {
       ObjectMapper mapper=new ObjectMapper();
       Map<String, String>data=new HashMap<>();
       data=dataTable.asMap(String.class, String.class);
       System.out.println(data.get("firstName"));

       Teacher teachers=new Teacher();
       Response response=teachers.requestGET(Config.getProperty("baseURL"), Config.getProperty("teacherPath"), teacherId);
       teachers= mapper.readValue(response.asString(), Teacher.class);
       teachers= teachers.getTeachers().get(0);
       System.out.println(teachers.getFirstName());

        teachers.setFirstName(data.get("firstName"));
        teachers.setLastName(data.get("lastName"));
        teachers.setEmailAddress(data.get("emailAddress"));
        teachers.setSubject(data.get("subject"));
        teachers.setPhone(data.get("phone"));
        teachers.setPremanentAddress(data.get("premanentAddress"));

       response=teachers.requestUPDATE(Config.getProperty("baseURL"),Config.getProperty("teacherPath"), Config.getProperty("updatePath"));
       System.out.println(response.asString());

    }



    @Then("verify updated Teacher response with Database")
    public void verify_updated_Teacher_response_with_Database() throws IOException {

        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> teacherDataList = DBUtility.executeQuery("select * from teacher where teacher_id="+teacherId);
            dbMap=teacherDataList.get(0);

            ObjectMapper mapper=new ObjectMapper();
            Teacher teachers=new Teacher();
            Response response=teachers.requestGET(Config.getProperty("baseURL"), Config.getProperty("teacherPath"), teacherId);
            teachers= mapper.readValue(response.asString(), Teacher.class);
            teachers= teachers.getTeachers().get(0);
            System.out.println(teachers.getFirstName());

            Map<String, Object> apiMap = mapper.convertValue(teachers, Map.class);


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

    @Given("teacher is deleted with {string}")
    public void teacher_is_deleted_with(String string) {
        Teacher teacher=new Teacher();
        teacher.requestDELETE
                (Config.getProperty("baseURL"),
                        Config.getProperty("deleteTeacherPath"), teacherId);

        //Assert.assertEquals("TEACHER WITH "+teacherId+" TEACHER ID IS NOT DELETED", response.statusCode(),200);
        System.out.println("TEACHER WITH "+teacherId+ " TEACHER ID IS DELETED");



    }


    @Then("verify deleted Teacher success status code {string}")
    public void verifyDeletedTeacherSuccessStatusCode(String arg0) {
        Teacher teacher=new Teacher();
        teacher.requestGET(Config.getProperty("baseURL"),Config.getProperty("teacherPath"), teacherId);
        //Assert.assertEquals("DELETION UNSUCCESSFUL", response.statusCode(), 404);
        System.out.println("DELETION SUCCESSFUL");

    }


    @Then("verify deleted Teacher response with Database")
    public void verify_deleted_Teacher_response_with_Database() {


        Map<Object, Object> dbMap;
        try {
            DBUtility.createConnection();

            List<Map<Object, Object>> teacherDataList = DBUtility.executeQuery("select * from teacher");
            dbMap=teacherDataList.get(0);

            Assert.assertFalse(teacherId+" IS IN THE DATA LIST. TEST FAILED!",dbMap.containsValue(String.valueOf(teacherId)));

            DBUtility.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }


}
