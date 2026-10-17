package ua.com.provider;

import ua.com.strategy.test.SampleTest;
import ua.com.strategy.validation.SampleValidationOne;
import static ua.com.provider.ValidationProvider.responseValidationOne;
import static ua.com.provider.ValidationProvider.responseValidationTwo;

public class TestDataProvider {
    @org.testng.annotations.DataProvider(name = "BASE_API_TEST_ONE")
    public static Object[][] provideBaseDataOne() {
        return new Object[][]{
                {responseValidationOne(), "BASE_API_TEST_ONE_PASSED"},
                {responseValidationTwo(), "BASE_API_TEST_ONE_FAILED"}
        };
    }

    @org.testng.annotations.DataProvider(name = "BASE_API_TEST_TWO")
    public static Object[][] provideBaseData() {
        return new Object[][]{
                {new SampleTest(), new SampleValidationOne(), "BASE_API_TEST_TWO"}
        };
    }
}
