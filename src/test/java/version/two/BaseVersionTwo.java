package version.two;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;

public class BaseVersionTwo implements ITest {

    protected ThreadLocal<String> testName = new ThreadLocal<>();

    @Override
    public String getTestName() {
        return testName.get();
    }

    @BeforeMethod
    public void setupTestName(java.lang.reflect.Method method, Object[] testData) {
        if (testData != null && testData.length > 2) {
            testName.set(String.valueOf(testData[2]));
        } else {
            testName.set(method.getName());
        }
    }
}
