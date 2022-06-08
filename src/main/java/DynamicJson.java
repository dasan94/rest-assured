import Utils.ReusableMethods;
import Utils.bodyData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, int aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(bodyData.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();
        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        //array = collection of elements
        //multidimentional array = collection of arrays
        return new Object[][]{
                {"qwer", 202206083},
                {"sadf", 202206084},
                {"zxc", 202206085}};
    }
}
