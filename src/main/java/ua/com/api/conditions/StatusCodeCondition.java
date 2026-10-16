package ua.com.api.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;

public class StatusCodeCondition implements Condition {
    private final int expectedCode;
    private static final Logger log = LoggerFactory.getLogger(BodyCondition.class);

    public StatusCodeCondition(int expectedCode) {
        this.expectedCode = expectedCode;
    }

    @Override
    public void check(Response response, SoftAssert softAssert) {
        try {
            response.then().statusCode(expectedCode);
            log.info("✅ Validation passed: Status code is {}", expectedCode);
        } catch (AssertionError e) {
            int actualCode = response.statusCode();
            log.error("❌ Validation failed! Status code - Expected: [{}], but Actual: [{}]", expectedCode, actualCode);
            softAssert.fail(String.format("Status code mismatch: Expected [%s] but found [%s]", expectedCode, actualCode));
        }
    }
}
