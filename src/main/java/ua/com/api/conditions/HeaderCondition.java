package ua.com.api.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class HeaderCondition implements Condition {
    private final String headerName;
    private final String expectedValue;
    private static final Logger log = LoggerFactory.getLogger(HeaderCondition.class);

    public HeaderCondition(String headerName, String expectedValue) {
        this.headerName = headerName;
        this.expectedValue = expectedValue;
    }

    @Override
    public void check(Response response, SoftAssert softAssert) {
        try {
            response.then().header(headerName, equalTo(expectedValue));
            log.info("✅ Validation passed: header '{}' equals '{}'", headerName, expectedValue);
        } catch (AssertionError error) {
            String actualValue = response.header(headerName);
            log.error("❌ Validation failed: header '{}' — expected [{}], actual [{}]", headerName, expectedValue, actualValue);
            softAssert.fail(String.format("Header '%s' mismatch: expected [%s], actual [%s]", headerName, expectedValue, actualValue));
        }
    }
}
