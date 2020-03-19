package apiModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import utilities.Config;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Student extends CrudObject {


    private List<Student> students;
    private int studentId;
    private String firstName;
    private String lastName;
    private int batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;

    private Contact contact;

    private Company company;



    public List<Student> getStudentList(String uri) throws IOException  {

        ObjectMapper mapper=new ObjectMapper();
        Response response=RestAssured.get(uri);
        Student student=mapper.readValue(response.asString(), Student.class);
        List <Student> studentList=student.getStudents();
        return studentList;
    }




}


