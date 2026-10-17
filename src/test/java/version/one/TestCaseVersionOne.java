package version.one;

import io.restassured.http.Method;
import org.testng.annotations.Test;
import ua.com.api.conditions.Condition;
import ua.com.provider.TestDataProvider;

import java.util.Collection;

public class TestCaseVersionOne extends BaseVersionOne {
    @Test(dataProvider = "BASE_API_TEST_ONE", dataProviderClass = TestDataProvider.class)
    public void initTest1(Collection<Condition> condition, String testCaseDescription) {
        assureRequest
                .setDescription(testCaseDescription)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .sendRequest(Method.GET)
                .shouldHave(condition);
    }
}
