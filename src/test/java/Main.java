import io.restassured.http.Method;
import ua.com.api.AssureRequest;

import static ua.com.api.AssureRequest.initApiTest;


void main() {
    AssureRequest request = initApiTest("some test case").disableSslValidation();

    request
            .setUrl("http://localhost:3000")
            .setPath("order/pay")
            .addHeader("Content-Type", "application/json")
            .sendRequest(Method.GET);
}