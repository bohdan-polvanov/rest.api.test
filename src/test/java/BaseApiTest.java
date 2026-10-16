import org.testng.ITest;
import ua.com.api.AssureRequest;
import org.testng.annotations.BeforeMethod;
import static ua.com.api.AssureRequest.initApiTest;


public class BaseApiTest implements ITest {
    protected String path = "order/pay";
    protected String url = "http://localhost:3000";

    protected ThreadLocal<String> testName = new ThreadLocal<>();

    protected AssureRequest assureRequest = initApiTest().disableSslValidation()
            .setUrl(url)
            .setPath(path)
            .addHeader("Content-Type", "application/json");

    @Override
    public String getTestName() {
        return testName.get();
    }

    @BeforeMethod
    public void setupTestName(java.lang.reflect.Method method, Object[] testData) {
        if (testData != null && testData.length > 1) {
            testName.set(String.valueOf(testData[1]));
        } else {
            testName.set(method.getName());
        }
    }
}
