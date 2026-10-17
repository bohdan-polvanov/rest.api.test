package ua.com.strategy.validation;

import ua.com.api.conditions.Condition;

import java.util.List;
import java.util.Collection;
import static ua.com.api.conditions.Conditions.*;
import static ua.com.api.conditions.Conditions.field;

public class SampleValidationOne implements ValidationTest {
    @Override
    public Collection<Condition> getConditions() {
        return List.of(
                statusCode(200),
                header("Content-Type", "application/json; charset=utf-8"),
                field("status", "SUCCESS"),
                field("transaction_details.method_used", "APPLE_PAY"),
                field("transaction_details.currency", "UAH")
        );
    }
}
