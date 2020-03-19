package apiModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Student extends CrudObject {

    // All Student Information

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







}
