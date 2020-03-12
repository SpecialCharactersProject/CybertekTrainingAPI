package apiModels;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class RequestBody {

    // All Student Information

    private List<Student> students;
    private int studentId;
    private String firstName;
    private String lastName;
    int batch;
    private Date joinDate;
    private Date birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;
    private Contact contact;
    private Company company;



    // Teacher Information which are not included in Student Section
    private List<Teacher> teachers;
    private int teacherId;
    private String emailAddress;
    private String phone;
    private String department;
    private double salary;
    private String premanentAddress;

}
