package ua.com.provider;

import static ua.com.provider.ValidationProvider.responseValidationOne;
import static ua.com.provider.ValidationProvider.responseValidationTwo;

public class TestDataProvider {
    @org.testng.annotations.DataProvider(name = "BASE_API_TEST")
    public static Object[][] provideBaseData() {
        return new Object[][]{
                {responseValidationOne(), "BASE_API_TEST_1"},
                {responseValidationTwo(), "BASE_API_TEST_2"}
        };
    }
}
