package ua.com.api.conditions;

public class Conditions {
    public static StatusCodeCondition statusCode(int code){return new StatusCodeCondition(code);}

    public static BodyCondition field(String path, Object expectedValue) {return new BodyCondition(path, expectedValue);}

    public static BodyNotNullCondition fieldNotNull(String jsonPath) {return new BodyNotNullCondition(jsonPath);}

    public static BodyNullCondition fieldNull(String jsonPath) {return new BodyNullCondition(jsonPath);}

    public static HeaderCondition header(String path, String expectedValue) {return new HeaderCondition(path, expectedValue);}
}
