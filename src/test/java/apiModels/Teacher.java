package apiModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import utilities.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Teacher extends CrudObject {

    private int teacherId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String joinDate;
    private String password;
    private String phone;
    private String subject;
    private String gender;
    private String department;
    private String birthDate;
    private double salary;
    private int batch;
    private String section;
    private String premanentAddress;
    private List<Teacher> teachers;


    public List<Teacher> getTeacherList(String uri) throws IOException  {

        ObjectMapper mapper=new ObjectMapper();
        Response response=RestAssured.get(uri);
        Teacher teacher=mapper.readValue(response.asString(), Teacher.class);
        List<Teacher>teacherList=teacher.getTeachers();
        return teacherList;
    }

    public void teacherUpdate(String uri, int teacherId) throws IOException{

        ObjectMapper objectMapper=new ObjectMapper();
        Response response=RestAssured.get(Config.getProperty("getTeacherUri")+teacherId);
        Teacher teachers=objectMapper.readValue(response.asString(), Teacher.class);
        Teacher teacher=teachers.getTeachers().get(0);
        response = RestAssured.given().contentType(ContentType.JSON).body(teacher)
                .when()
                .put("http://api.cybertektraining.com/teacher/update");


    }
}
