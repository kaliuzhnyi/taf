package io.taf.utils.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configures a column in a Vaadin Grid. This annotation can be applied to fields to specify
 * how the field should be represented as a column in the grid, including its header, footer,
 * sorting capabilities, and other attributes.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GridColumnConfig {

    /**
     * Specifies the ID of the column.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setId(String)}
     *
     * @return The column ID.
     */
    String id() default "";

    /**
     * Specifies a unique key for the column that can be used for identifying the column
     * in grid configurations and event handling.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setKey(String)}
     *
     * @return The column key.
     */
    String key() default "";

    /**
     * Specifies the header text for the column. This text is displayed in the column header.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setHeader(String)}
     *
     * @return The header text for the column.
     */
    String header() default "";

    /**
     * Specifies the footer text for the column. This text is displayed in the column footer.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setFooter(String)}
     *
     * @return The footer text for the column.
     */
    String footer() default "";

    /**
     * Indicates whether the column should be sortable by the user. Default is true.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setSortable(boolean)}
     *
     * @return True if the column is sortable, false otherwise.
     */
    boolean sortable() default true;

    /**
     * Indicates whether the column can be resized by the user. Default is true.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setResizable(boolean)}
     *
     * @return True if the column is resizable, false otherwise.
     */
    boolean resizable() default true;

    /**
     * Indicates whether the column width should be automatically set based on the content.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setAutoWidth(boolean)}
     * Default is true.
     *
     * @return True if the column width is set automatically, false otherwise.
     */
    boolean autoWidth() default true;

    /**
     * Specifies the flex grow ratio for the column. The flex grow ratio determines how much
     * the column will grow relative to other columns in the grid when there is extra space available.
     * A default value of -1 indicates that the default flex grow setting should not be used.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setFlexGrow(int)}
     *
     * @return The flex grow ratio for the column.
     */
    int flexGrow() default -1;

    /**
     * Indicates whether the column should be frozen (fixed position) on the start (left) side
     * of the grid. Default is false.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setFrozen(boolean)}
     *
     * @return True if the column is frozen, false otherwise.
     */
    boolean frozen() default false;

    /**
     * Indicates whether the column should be frozen to the end (right) side of the grid.
     * This property takes effect only if the grid supports freezing columns to the end.
     * Default is false.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.grid.Grid.Column#setFrozenToEnd(boolean)}
     *
     * @return True if the column is frozen to the end, false otherwise.
     */
    boolean frozenToEnd() default false;

}
