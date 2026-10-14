package ua.com.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class AssureResponse {
    private final Response response;
    private final SoftAssert softAssert;
    private static final Logger log = LoggerFactory.getLogger(AssureResponse.class);

    public AssureResponse(Response response) {
        this.response = response;
        this.softAssert = new SoftAssert();
        logResponseStatus();
        logResponseBody();
    }

    private void logResponseStatus() {
        int statusCode = response.getStatusCode();

        if (statusCode >= 200 && statusCode < 300) {
            log.info("✅ Request executed successfully. Status code: {}", statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            log.warn("⚠️ Client Error. The request was invalid or unauthorized. Status code: {}", statusCode);
        } else if (statusCode >= 500) {
            log.error("❌ Server Error. Something went wrong on the server side. Status code: {}", statusCode);
        } else {
            log.info("ℹ️ Request completed. Status code: {}", statusCode);
        }
    }

    public AssureResponse checkStatusCode(int expectedCode) {
        try {
            response.then().statusCode(expectedCode);
            log.info("✅ Validation passed: Status code is {}", expectedCode);
        } catch (AssertionError e) {
            int actualCode = response.statusCode();
            log.error("❌ Validation failed! Status code - Expected: [{}], but Actual: [{}]", expectedCode, actualCode);
            // Записуємо помилку в TestNG, але код іде далі!
            softAssert.fail(String.format("Status code mismatch: Expected [%s] but found [%s]", expectedCode, actualCode));
        }
        return this;
    }

    public AssureResponse checkHeader(String headerName, String expectedValue) {
        try {
            response.then().header(headerName, expectedValue);
            log.info("✅ Validation passed: Header '{}' equals '{}'", headerName, expectedValue);
        } catch (AssertionError e) {
            String actualValue = response.header(headerName);
            log.error("❌ Validation failed! Header '{}' - Expected: [{}], but Actual: [{}]", headerName, expectedValue, actualValue);
            softAssert.fail(String.format("Header mismatch: '%s' expected [%s] but found [%s]", headerName, expectedValue, actualValue));
        }
        return this;
    }

    public AssureResponse checkJsonField(String jsonPath, Object expectedValue) {
        try {
            response.then().body(jsonPath, equalTo(expectedValue));
            log.info("✅ Validation passed: JSON field '{}' equals '{}'", jsonPath, expectedValue);
        } catch (AssertionError e) {
            Object actualValue = response.jsonPath().get(jsonPath);
            log.error("❌ Validation failed! JSON field '{}' - Expected: [{}], but Actual: [{}]", jsonPath, expectedValue, actualValue);
            softAssert.fail(String.format("JSON field mismatch: '%s' expected [%s] but found [%s]", jsonPath, expectedValue, actualValue));
        }
        return this;
    }

    public void assertAll() {
        softAssert.assertAll();
    }

    private void logResponseBody() {
        String responseBody = response.getBody().asPrettyString();
        if (responseBody != null && !responseBody.trim().isEmpty()) {
            log.info("📦 Response Body:\n{}", responseBody);
        } else {
            log.info("📦 Response Body: [Empty]");
        }
    }

    public Response getResponseBody() {
        return this.response;
    }
}