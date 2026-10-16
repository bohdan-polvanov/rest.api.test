package ua.com.provider;

import java.util.List;
import java.util.Collection;
import ua.com.api.conditions.Condition;
import static ua.com.api.conditions.Conditions.*;

public class ValidationProvider {
    public static Collection<Condition> responseValidationOne() {
        return List.of(
                statusCode(200),
                header("Content-Type", "application/json; charset=utf-8"),
                field("status", "SUCCESS"),
                field("transaction_details.method_used", "APPLE_PAY"),
                field("transaction_details.currency", "UAH")
        );
    }

    public static Collection<Condition> responseValidationTwo() {
        return List.of(
                statusCode(201),
                header("Content-Type", "application/json; charset=utf-8"),
                field("status", "SUCCESS3"),
                field("transaction_details.method_used", "APPLE_PAY3"),
                field("transaction_details.currency", "UAH4")
        );
    }
}
