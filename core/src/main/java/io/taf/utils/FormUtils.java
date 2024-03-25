package io.taf.utils;

import com.vaadin.flow.component.Component;
import io.taf.entity.Entity;
import io.taf.utils.fields.FieldUtils;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@UtilityClass
@SuppressWarnings("unused")
public class FormUtils {

    public <ENTITY extends Entity<? extends Serializable>> List<Component> createFieldsBasedOnEntity(Class<ENTITY> cls) {
        return ItemDataViewUtils.getDisplayableFields(cls)
                .stream()
                .map(field -> FieldUtils.createField(field, s -> MessageProvider.getMessageProvider().getValue(s)))
                .flatMap(Optional::stream)
                .map(Component.class::cast)
                .toList();
    }

}
