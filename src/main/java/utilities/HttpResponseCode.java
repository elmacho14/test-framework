package utilities;

import io.restassured.RestAssured;

public class HttpResponseCode {

    public static int httpResponseViaGet(String url) {
        return RestAssured.get(url).statusCode();
    }

    public static int httpResponseCodeViaPost(String url) {
        return RestAssured.post(url).statusCode();
    }
}
