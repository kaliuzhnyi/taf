package io.taf.utils.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import io.taf.entity.Entity;
import io.taf.utils.ListDataViewUtils;
import io.taf.utils.MessageProvider;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public class GridUtils {

    public final String COLUMN_ID_DELIMITER = "-";
    public final String COLUMN_ID_PREFIX = "column";

    @Nonnull
    public <T extends Entity<? extends Serializable>> Grid<T> createBasedOnEntity(@Nonnull Class<T> cls) {

        var grid = new Grid<T>();

        ListDataViewUtils.getDisplayableFields(cls).forEach(field ->
                createColumn(grid, field, MessageProvider.getMessageProviderValueFunctional()));

        return grid;
    }

    @Nonnull
    public <T> Grid.Column<T> createColumn(@Nonnull Grid<T> grid, @Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(GridColumnConfig.class);

        var column = grid.addColumn(entity -> {
            field.setAccessible(true);
            try {
                return field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        resolveColumnId(column, field, annotation.id());
        resolveFieldKey(column, field, annotation.key());
        column.setHeader(textResolver.apply(annotation.header()));
        column.setFooter(textResolver.apply(annotation.footer()));
        column.setSortable(annotation.sortable());
        column.setResizable(annotation.resizable());
        column.setAutoWidth(annotation.autoWidth());
        resolveFlexGrow(column, annotation.flexGrow());
        column.setFrozen(annotation.frozen());
        column.setFrozenToEnd(annotation.frozenToEnd());

        return column;
    }


    /**
     * Resolves the ID to be used for a column component in the UI. If a default value is provided
     * and is non-empty, it will be used as the ID. Otherwise, the ID is constructed using a predefined
     * prefix, a delimiter, and the field's name converted to lower case.
     *
     * @param component    The component implementing {@link Component} whose width is to be adjusted.
     * @param field        The field for which the ID is being resolved. Must not be null.
     * @param defaultValue The default ID value to use if provided. Can be null, in which case the ID
     *                     will be generated based on the field's name.
     */
    private void resolveColumnId(@Nonnull Component component, @Nonnull Field field, @Nullable String defaultValue) {
        var id = Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> COLUMN_ID_PREFIX.concat(COLUMN_ID_DELIMITER).concat(field.getName().toLowerCase()));
        component.setId(id);
    }

    private <T> void resolveFieldKey(@Nonnull Grid.Column<T> column, @Nonnull Field field, @Nullable String defaultValue) {
        var key = Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> field.getName().toLowerCase());
        column.setKey(key);
    }

    private <T> void resolveFlexGrow(@Nonnull Grid.Column<T> column, @Nullable Integer defaultValue) {
        Optional.ofNullable(defaultValue)
                .filter(v -> v > 0)
                .ifPresent(column::setFlexGrow);
    }

}
