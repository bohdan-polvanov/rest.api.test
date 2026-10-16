package ua.com.api.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class BodyCondition implements Condition {
    private final String jsonPath;
    private final Object expectedValue;
    private static final Logger log = LoggerFactory.getLogger(BodyCondition.class);

    public BodyCondition(String jsonPath, Object expectedValue) {
        this.jsonPath = jsonPath;
        this.expectedValue = expectedValue;
    }

    @Override
    public void check(Response response, SoftAssert softAssert) {
        try {
            response.then().body(jsonPath, equalTo(expectedValue));
            log.info("✅ Validation passed: JSON field '{}' equals '{}'", jsonPath, expectedValue);
        } catch (AssertionError e) {
            Object actualValue = response.jsonPath().get(jsonPath);
            log.error("❌ Validation failed! JSON field '{}' - Expected: [{}], but Actual: [{}]", jsonPath, expectedValue, actualValue);
            softAssert.fail(String.format("JSON field mismatch: '%s' expected [%s] but found [%s]", jsonPath, expectedValue, actualValue));
        }
    }
}
