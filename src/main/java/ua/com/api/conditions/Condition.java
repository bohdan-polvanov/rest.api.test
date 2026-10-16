package ua.com.api.conditions;

import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;

public interface Condition {
    void check(Response response, SoftAssert softAssert);
}
