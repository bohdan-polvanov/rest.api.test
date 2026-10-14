import io.restassured.http.Method;
import org.testng.annotations.Test;
import ua.com.api.AssureRequest;

import static ua.com.api.AssureRequest.initApiTest;

public class ApiTest {

    private AssureRequest assureRequest = initApiTest().disableSslValidation();

    @Test
    public void initTest1() {
        assureRequest
                .setDescription("First Test Case #1")
                .setUrl("http://localhost:3000")
                .setPath("order/pay")
                .addHeader("Content-Type", "application/json")
                .sendRequest(Method.GET)
                .checkStatusCode(201)
                .assertAll();
    }

    @Test
    public void initTest2() {
        assureRequest
                .setDescription("Second Test Case #2")
                .setUrl("http://localhost:3000")
                .setPath("order/pay")
                .addHeader("Content-Type", "application/json")
                .sendRequest(Method.GET)
                .checkStatusCode(200)
                .assertAll();
    }
}
