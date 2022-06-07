import Utils.ReusableMethods;
import Utils.bodyData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {

        //Create new Place
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                .log().all()
                .queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(bodyData.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .body("scope", equalTo("APP"))
                .extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        String place_id = js.getString("place_id");
        String newAddress = "Summer Walk, Africa";

        //Update address place
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(bodyData.UpdateAddress(place_id, newAddress))
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "3880efd06f247214e60f940f392f0e11")
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all()
                .statusCode(200).extract().asString();

        JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);

        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
    }
}
