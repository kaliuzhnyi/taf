package io.taf.utils;

import io.taf.entity.Entity;
import io.taf.views.item.DisplayOnItemDataView;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

@UtilityClass
@SuppressWarnings("unused")
public class ItemDataViewUtils {

    public <ENTITY extends Entity<? extends Serializable>> List<Field> getDisplayableFields(Class<ENTITY> cls) {
        return EntityUtils.getAnnotatedFields(cls, DisplayOnItemDataView.class).stream()
                .sorted(Comparator.comparingInt(field -> field.getAnnotation(DisplayOnItemDataView.class).order()))
                .toList();
    }

}
