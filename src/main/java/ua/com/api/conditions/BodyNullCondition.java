package ua.com.api.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;

public class BodyNullCondition implements Condition {
    private final String jsonPath;
    private static final Logger log = LoggerFactory.getLogger(BodyNullCondition.class);

    public BodyNullCondition(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public void check(Response response, SoftAssert softAssert) {
        Object actualValue = response.jsonPath().get(jsonPath);

        if (actualValue == null) {
            log.info("✅ Validation passed: JSON field '{}' does not exist or is null", jsonPath);
            return;
        }
        log.error("❌ Validation failed: JSON field '{}' must not exist, but actual value is [{}]", jsonPath, actualValue);
        softAssert.fail(String.format("JSON field '%s' must not exist, but actual value is [%s]", jsonPath, actualValue));
    }
}
