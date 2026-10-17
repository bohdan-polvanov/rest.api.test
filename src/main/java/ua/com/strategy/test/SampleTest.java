package ua.com.strategy.test;

import io.restassured.http.Method;
import ua.com.api.AssureRequest;
import ua.com.strategy.validation.ValidationTest;
import static ua.com.api.AssureRequest.initApiTest;

public class SampleTest implements BaseTest {
    private String path = "order/pay";
    private String url = "http://localhost:3000";

    private AssureRequest assureRequest = initApiTest()
            .disableSslValidation()
            .setUrl(url)
            .setPath(path)
            .addHeader("Content-Type", "application/json");

    @Override
    public void run(ValidationTest validation) {
        assureRequest
                .setDescription("SAMPLE_TEST_DESCRIPTION")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .sendRequest(Method.GET)
                .shouldHave(validation.getConditions());
    }
}
