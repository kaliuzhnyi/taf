package io.taf.utils;

import io.taf.entity.Entity;
import io.taf.views.list.DisplayOnListDataView;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

@UtilityClass
public class ListDataViewUtils {

    public <ENTITY extends Entity<? extends Serializable>> List<Field> getDisplayableFields(Class<ENTITY> cls) {
        return EntityUtils.getAnnotatedFields(cls, DisplayOnListDataView.class).stream()
                .sorted(Comparator.comparingInt(field -> field.getAnnotation(DisplayOnListDataView.class).order()))
                .toList();
    }

}
