package files;

public class bodyData {

    public static String AddPlace() {
        return "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String UpdateAddress(String place_id, String newAddress){
        return "{\r\n" +
                "\"place_id\":\""+place_id+"\",\r\n" +
                "\"address\":\""+newAddress+"\",\r\n" +
                "\"key\":\"qaclick123\"\r\n" +
                "}";
    }
}