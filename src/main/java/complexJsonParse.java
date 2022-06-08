import Utils.bodyData;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(bodyData.CoursePrice());
        //.size() only works for arrays
        int count = js.getInt("courses.size()");
        String firstCourseName = js.getString("courses[0].title");
        String titles;
        int prices;
        int totalPrice;
        int copies;
        System.out.println("titles | prices | copies");
        for(int i = 0; i<count; i++){
            titles = js.getString("courses[" + i + "].title" );
            prices = js.getInt("courses[" + i + "].price" );
            copies = js.getInt("courses[" + i + "].copies" );
            totalPrice = prices * copies;
            System.out.println(titles + " | " + prices + " | " + copies);
            System.out.println("totalPrice");
            System.out.println(totalPrice);
        }
        System.out.println(count);
        System.out.println(firstCourseName);

    }
}
