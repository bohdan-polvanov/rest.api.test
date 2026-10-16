import java.util.Collection;
import io.restassured.http.Method;
import org.testng.annotations.Test;
import ua.com.api.conditions.Condition;
import ua.com.provider.TestDataProvider;

public class ApiTest extends BaseApiTest{
    @Test(dataProvider = "BASE_API_TEST", dataProviderClass = TestDataProvider.class)
    public void initTest1(Collection<Condition> condition, String testCaseDescription) {
        assureRequest
                .setDescription(testCaseDescription)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .sendRequest(Method.GET)
                .shouldHave(condition);
    }
}
