package Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

public class CommonMethods {

    public Response getApiCall(String accessToken, HashMap<String,String> header, String uri)
    {
        Response response=null;
        response = RestAssured.with().contentType("application/json")
                .given()
                .body(header).log().all()
                .header("Authorization", accessToken)
                .request().get(uri);
        response.then().statusCode(200);
        response.prettyPrint();
        return response;
    }

    public Response postApiCall(String accessToken, HashMap<String,String> body, String uri)
    {
        Response response=null;
        response = RestAssured.with().contentType("application/json")
                .given()
                .header("Authorization", accessToken)
                .body(body).log().all()
                .request().post(uri);
        response.then().statusCode(201);
        response.prettyPrint();

        return response;
    }
    public Response deleteAPiCall(String accessToken,String ID)
    {
        Response response=null;
        response = RestAssured.with().contentType("application/json")
                .given()
                .header("Authorization", accessToken)
                .request().delete("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/innovatorserver"+"/server/odata/Part('" +ID+"')");
        response.then().statusCode(204);
        response.prettyPrint();
        return response;
    }
    public Response deleteCAD(String accessToken,String ID)
    {
        Response response=null;
        response = RestAssured.with().contentType("application/json")
                .given()
                .header("Authorization", accessToken)
                .request().delete("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/innovatorserver"+"/server/odata/CAD('" +ID+"')");
        response.then().statusCode(204);
        response.prettyPrint();
        return response;
    }

    public Response methodCall(String accessToken, String uri)
    {
        Response response=null;
        response = RestAssured.with().contentType("application/json")
                .given()
                .header("Authorization", accessToken)
                .request().post(uri);
        response.then().statusCode(200);
        response.prettyPrint();

        return response;
    }


}
