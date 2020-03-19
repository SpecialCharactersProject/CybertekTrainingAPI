package apiModels;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Config;
import utilities.DBUtility;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static io.restassured.RestAssured.*;

public class apiModelsTest {


@Test
    public void requestPOST() throws Exception{



    ObjectMapper mapper=new ObjectMapper();
    Response response= RestAssured.get(Config.getProperty("baseURL"),Config.getProperty("studentPath"), Config.getProperty("createPath"));
    Student student=mapper.readValue(response.asString(), Student.class);
    student.getStudents().get(0);
    System.out.println(student.getFirstName());


    //System.out.println(responseBody.getTeachers().get(0).getFirstName());
    //Assert.assertEquals(response.getStatusCode(), 200);



}

    @Test
    public void dbTest() throws SQLException {
    RestAssured baseURI;


    DBUtility.createConnection();

    List<Map<Object, Object>> teacherDataList = DBUtility.executeQuery("select * from teacher");
    Map<Object, Object> dbMap=teacherDataList.get(0);
    System.out.println(dbMap.get("teacher_id"));
    DBUtility.close();
}
    @Test
    public void pathParameterTest() throws Exception{

    int teacherId=2002;
    ObjectMapper objectMapper=new ObjectMapper();
    Response response=RestAssured.get("http://api.cybertektraining.com/teacher/"+teacherId);
    Teacher teachers=objectMapper.readValue(response.asString(), Teacher.class);
    Teacher firstTeacher=teachers.getTeachers().get(0);
    System.out.println(firstTeacher.getLastName());
    System.out.println(teachers.getTeachers().get(0).getFirstName());




//    response = RestAssured.given().contentType(ContentType.JSON).body(teachers)
//             .when()
//            .put("http://api.cybertektraining.com/teacher/update");



}

    @Test
    public void deleteTest() throws Exception{

    Response response = RestAssured
            .given()
                .baseUri("http://api.cybertektraining.com")
                .basePath("/teacher/delete")
            .pathParam("userid", 1961)
            .when()
                .delete("/{userid}");
    System.out.println(response.asString());
    System.out.println(response.statusCode());
}




}



