package ua.com.strategy.validation;

import ua.com.api.conditions.Condition;

import java.util.Collection;

public interface ValidationTest {
    Collection<Condition> getConditions();
}
