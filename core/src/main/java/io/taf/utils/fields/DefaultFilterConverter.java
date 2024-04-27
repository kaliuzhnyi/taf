package io.taf.utils.fields;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.SerializablePredicate;

public class DefaultFilterConverter implements SerializableFunction<String, SerializablePredicate<Object>> {
    @Override
    public SerializablePredicate<Object> apply(String textFilter) {
        return item -> {
            var r = item != null && item.toString().toLowerCase().contains(textFilter.toLowerCase());
            return r;
        };
    }
}
