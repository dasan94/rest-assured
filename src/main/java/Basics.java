import files.bodyData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

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

        JsonPath js = new JsonPath(response); //Parsing Json
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
        given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "3880efd06f247214e60f940f392f0e11")
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all()
                .statusCode(200)
                .body("address", equalTo("Summer Walk, Africa"))
                .body("name", equalTo("Frontline house"))
                .body("phone_number", equalTo("(+91) 983 893 3937"))
                .body("types", equalTo("shoe park,shop"))
                .body("website", equalTo("http://google.com"))
                .body("language", equalTo("French-IN"))
                .body("accuracy", equalTo("50"));
    }
}
