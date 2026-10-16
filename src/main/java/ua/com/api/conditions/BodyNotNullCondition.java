package ua.com.api.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.notNullValue;

public class BodyNotNullCondition implements Condition {
    private final String path;
    private static final Logger log = LoggerFactory.getLogger(BodyNotNullCondition.class);

    public BodyNotNullCondition(String path) {
        this.path = path;
    }

    @Override
    public void check(Response response, SoftAssert softAssert) {
        try {
            response.then().body(path, notNullValue());
            log.info("✅ Validation passed: JSON field '{}' is not null", path);
        } catch (AssertionError e) {
            Object actualValue = response.jsonPath().get(path);
            log.error("❌ Validation failed: JSON field '{}' must not be null. Actual: [{}]", path, actualValue);
            softAssert.fail(String.format("JSON field '%s' must not be null, but actual value is [%s]", path, actualValue));
        }
    }
}
