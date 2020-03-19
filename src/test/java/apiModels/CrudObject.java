package apiModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CrudObject {

    public Response requestPOST(String baseUri, String classPath, String createPath){

        ObjectMapper mapper=new ObjectMapper();
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(this)
                    .baseUri(baseUri)
                    .basePath(classPath)
                .when()
                    .post(createPath);

        System.out.println("CREATE STATUS CODE : "+response.statusCode());
        if(response.getStatusCode()==200){
            try {
                mapper.readerForUpdating(this).readValue(response.asString());
            } catch (Exception e) {
                System.out.println("JSON string couldn't map!");
            }

        } else {
            System.out.println("POST REQUEST FAILED!");
        }

        return response;
    }

    public static Response requestGET(String baseUri, String classPathUri, int id){
        Response response = RestAssured
                .given()
                    .baseUri(baseUri)
                    .basePath(classPathUri)
                    .pathParam("id", id)
                .when()
                 .get("/{id}");
        System.out.println("GET STATUSCODE : "+response.statusCode());

        return response;
    }


    public Response requestUPDATE(String baseUri, String classPath, String updatePath) throws Exception{

        ObjectMapper objectMapper=new ObjectMapper();
        Response response=RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(this)
                    .baseUri(baseUri)
                    .basePath(classPath)
                .when()
                    .put(updatePath);
        System.out.println("UPDATE STATUSCODE: "+response.statusCode());
        return response;
    }

    public void requestDELETE(String baseUri, String deletePath, int id) {
        Response response = RestAssured
                .given()
                    .baseUri(baseUri)
                    .basePath(deletePath)
                    .pathParam("id", id)
                .when()
                    .delete("/{id}");
        System.out.println("DELETE STATUSCODE: " + response.statusCode());



    }
}
