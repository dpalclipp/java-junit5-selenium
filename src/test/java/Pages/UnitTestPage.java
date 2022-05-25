package Pages;


import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class UnitTestPage  {
    public static Response response;
    public static String accessToken="Bearer ";

@Step("Sending request for the access token")
    public static void sendTokenRequest()
    {
        try {
            System.out.println("I am here");
            RestAssured.urlEncodingEnabled = false;
            response = RestAssured.with().contentType("application/x-www-form-urlencoded")
                    .given().filter(new AllureRestAssured())
                    .formParam("grant_type", "password")
                    .formParam("scope", "Innovator")
                    .formParam("client_id", "IOMApp")
                    .formParam("username", "admin")
                    .formParam("password", "49041530ca9c9b9b3ff86759fe211bca")
                    .formParam("database", "InnovatorSolutions12")

                    .request().post("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/InnovatorServer/oauthserver/connect/token");
            response.then().statusCode(200);
            response.prettyPrint();
            accessToken = accessToken + response.getBody().jsonPath().get("access_token");
        }catch(Exception e){e.printStackTrace();}
    }
    public static String returnAccesstoken()
    {
        return accessToken;
    }

   @Step("Sending request to get all the details of a specific part")
    public static void sendPartDetailsRequest(String itemNumber)
    {

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("item_number",itemNumber);
        response = RestAssured.with().contentType(ContentType.JSON)
                .given().filter(new AllureRestAssured())
                .header("Authorization", accessToken)
                .body(map).log().all()
                .request().get("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/innovatorserver/server/odata/Part");
        response.prettyPrint();

    }
@Step("Validating details of a particular Part.")
    public static void validatePartDetails(String name)
    {
        Assert.assertTrue((response.getBody().jsonPath().get("value.name").toString()).contains(name));
    }
@Step("Creating a new part using all the required data.")
    public static void createPartApi(String itemNum, String name)
    {
        System.out.println("AccessToken: "+accessToken);
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("item_number",itemNum);
        map.put("name",name);
        response = RestAssured.with().contentType("application/json")
                .given().filter(new AllureRestAssured())
                .header("Authorization", accessToken)
                .body(map).log().all()
                .request().post("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/innovatorserver/server/odata/Part");
       // response.then().statusCode(201);
        response.prettyPrint();
        sendPartDetailsRequest(itemNum);
        validatePartDetails(name);
    }
@Step("Deleting a part from the application using partID")
    public static void deletePartapi()
    {
        String partId=response.getBody().jsonPath().get("value.id").toString().trim().substring(1,33).trim();
        response = RestAssured.with().contentType("application/json")
                .given().filter(new AllureRestAssured())
                .header("Authorization", accessToken)
                .request().delete("https://app.clipp-dev.eu-west-1.aws.pmicloud.biz/innovatorserver/server/odata/Part('" +partId+"')");
        response.then().statusCode(204);
        response.prettyPrint();

    }

}
