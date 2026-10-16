package ua.com.api;

import org.slf4j.Logger;
import java.util.Collection;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import ua.com.api.conditions.Condition;
import io.restassured.response.Response;

public class AssureResponse {
    protected final Response response;
    protected final SoftAssert softAssert;
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

    private void logResponseBody() {
        String responseBody = response.getBody().toString();
        if (responseBody != null && !responseBody.trim().isEmpty()) {
            log.info("📦 Response Body:\n{}", responseBody);
        } else {
            log.info("📦 Response Body: [Empty]");
        }
    }

    public AssureResponse shouldHave(Condition condition) {
        condition.check(response, softAssert);
        return assertAll();
    }

    public AssureResponse shouldHave(Collection<Condition> conditions) {
        for (Condition condition : conditions) {
            condition.check(response, softAssert);
        }
        return assertAll();
    }

    private AssureResponse assertAll() {
        softAssert.assertAll();
        return this;
    }

    public Response getResponseBody() {
        return this.response;
    }
}