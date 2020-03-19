package utilities;

import apiModels.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIRunner {

    private static Response responseHandler;
    private static Response response;
    private static ObjectMapper mapper = new ObjectMapper();



    public static void runPOST(String url, Teacher requestBody){

        response = RestAssured.given().
                contentType(ContentType.JSON).body(requestBody).when().post(url);
        System.out.println("STATUS: "+response.statusCode());

    }
    public static void runUPDATE(String url, Teacher requestBody){

        response = RestAssured.given().
                contentType(ContentType.JSON).body(requestBody).when().put(url);
        System.out.println("STATUS: "+response.statusCode());

    }



//    public static Object newObjectFromMap(Map<String, String> aMap)
//    {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<HashMap<String, String>> mapRef = new TypeReference<HashMap<String, String>>() {
//            };
//            ObjectWriter ow = mapper.writerFor(mapRef);
//            TypeReference<Student> studentRef = new TypeReference<Student>() {
//            };
//            return mapper.readValue(mapper.writeValueAsSt(aMap), studentRefring);
//        } catch (Exception ex) {
//            return null;
////        }
//
//    }

}








