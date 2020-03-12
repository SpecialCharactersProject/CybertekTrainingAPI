package utilities;

import apiModels.RequestBody;
import apiModels.ResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

public class APIRunner {

    private static ResponseHandler responseHandler;


    public static void runGET(String uri) {

        Response response = RestAssured.get(uri);
        System.out.println("STATUS " + response.statusCode());
        System.out.println(response.asString());
        ObjectMapper mapper = new ObjectMapper();

        try {
            responseHandler = mapper.readValue(response.asString(), ResponseHandler.class);
        } catch (IOException e) {
            System.out.println("JSON WAS NOT MAPPED PROPERLY");
        }

    }
    public static void runPOST(RequestBody body, String uri){
        Response response=RestAssured.given().contentType(ContentType.JSON).body(body).post(uri);
        System.out.println("STATUS " + response.statusCode());
        System.out.println(response.asString());
        ObjectMapper mapper = new ObjectMapper();

        try {
            responseHandler = mapper.readValue(response.asString(), ResponseHandler.class);
        } catch (IOException e) {
            System.out.println("JSON WAS NOT MAPPED PROPERLY");
        }


    }


    public static ResponseHandler getResponse(){

        if(responseHandler==null){
            System.out.println("Please run API first");
            throw new RuntimeException();
        }
        return responseHandler;
    }

    public static void runDelete(String uri, int id){
        Response response=RestAssured.given().contentType(ContentType.JSON).delete(uri+id);
        System.out.println("STATUS " + response.statusCode());

    }



}





