package apiModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.List;

;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class Teacher extends CrudObject {


    private List<Teacher> teachers;
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
}
