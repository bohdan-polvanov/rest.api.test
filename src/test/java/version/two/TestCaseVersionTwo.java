package version.two;

import org.testng.annotations.Test;
import ua.com.strategy.test.BaseTest;
import ua.com.provider.TestDataProvider;
import ua.com.strategy.validation.ValidationTest;

public class TestCaseVersionTwo extends BaseVersionTwo {
    @Test(dataProvider = "BASE_API_TEST_TWO", dataProviderClass = TestDataProvider.class)
    public void initTest1(BaseTest test, ValidationTest validation, String testCaseDescription) {
        test.run(validation);
    }
}
