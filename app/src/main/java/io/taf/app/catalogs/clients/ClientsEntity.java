package io.taf.app.catalogs.clients;

import com.vaadin.flow.data.provider.ListDataProvider;
import io.taf.catalogs.AbstractCatalogEntity;
import io.taf.utils.fields.checkbox.CheckBoxConfig;
import io.taf.utils.fields.checkboxgroup.CheckBoxGroupConfig;
import io.taf.utils.fields.combobox.ComboBoxConfig;
import io.taf.utils.fields.datapicker.DatePickerConfig;
import io.taf.utils.fields.datetimepicker.DateTimePickerConfig;
import io.taf.utils.fields.listbox.ListBoxConfig;
import io.taf.utils.fields.multiselectcombobox.MultiSelectComboBoxConfig;
import io.taf.utils.fields.multiselectlistbox.MultiSelectListBoxConfig;
import io.taf.utils.fields.numberfield.NumberFieldConfig;
import io.taf.utils.fields.radiobutton.RadioButtonGroupConfig;
import io.taf.utils.fields.select.SelectConfig;
import io.taf.utils.fields.textfield.TextFieldConfig;
import io.taf.utils.fields.timepicker.TimePickerConfig;
import io.taf.utils.grid.GridColumnConfig;
import io.taf.views.list.DisplayOnListDataView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "clients")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ClientsEntity
        extends AbstractCatalogEntity<Long> {

    @TextFieldConfig(label = "${field.title.name}", widthFull = true, clearButtonVisible = true)
    @DisplayOnListDataView
    @GridColumnConfig(header = "${field.title.name}")
    @Column(name = "name")
    private String name;

    @TextFieldConfig(label = "${field.title.surname}", widthFull = true, clearButtonVisible = true)
    @DisplayOnListDataView
    @GridColumnConfig(header = "${field.title.surname}")
    @Column(name = "surname")
    private String surname;

    @NumberFieldConfig(label = "${field.title.salary}", clearButtonVisible = true)
    @DisplayOnListDataView
    @GridColumnConfig(header = "${field.title.salary}")
    @Column(name = "salary")
    private Long salary;

    // test of creation fields

    @TimePickerConfig(
            label = "Time",
            defaultValue = "12:23"
    )
    @Transient
    private LocalTime localTime = LocalTime.now();

    @DatePickerConfig(
            label = "Date",
            defaultValue = "1991-01-23"
    )
    @Transient
    private LocalDate localDate = LocalDate.now();

    @DateTimePickerConfig(
            label = "Datetime",
            defaultValue = "1991-01-23T12:23"
    )
    @Transient
    private LocalDateTime localDateTime = LocalDateTime.now();

    @CheckBoxConfig(
            label = "Check box",
            defaultValue = true
    )
    @Transient
    private Boolean checkBox;

    @NumberFieldConfig(
            label = "Sum",
            max = 10000,
            min = -125,
            defaultValue = 123
    )
    @Transient
    private Long sum;

    @SelectConfig(
            label = "Type (select)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultValueProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private String typeSelect = "Bad";

    @ListBoxConfig(
            //label = "Type (select)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultValueProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private String typeListBox = "Bad";

    @ComboBoxConfig(
            label = "Type (combo box)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultValueProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private String typeComboBox = "Bad";

    @RadioButtonGroupConfig(
            label = "Type (radio button)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultValueProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private String typeRadioButton = "Bad";

    @MultiSelectComboBoxConfig(
            label = "Type (multiselect combo box)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultItemsProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private Set<String> typeMultiSelectComboBox = Set.of("Bad", "So-so");

    @CheckBoxGroupConfig(
            label = "Type (check box group)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultItemsProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private Set<String> typeCheckBoxGroup = Set.of("Bad", "So-so");

    @MultiSelectListBoxConfig(
            //label = "Type (check box group)",
            widthFull = true,
            defaultValueProvider = TypeSelectDefaultItemsProvider.class,
            dataProvider = TypeSelectDataProvider.class
    )
    @Transient
    private Set<String> typeMultiSelectListBox = Set.of("Bad", "So-so");

    public static class TypeSelectDataProvider extends ListDataProvider<String> {
        public TypeSelectDataProvider() {
            super(new LinkedList<>(List.of("Bad", "So-so", "Good", "Great", "Asshole")));
        }
    }

    public static class TypeSelectDefaultValueProvider implements Supplier<String> {
        @Override
        public String get() {
            return "Asshole";
        }
    }

    public static class TypeSelectDefaultItemsProvider implements Supplier<Set<String>> {
        @Override
        public Set<String> get() {
            return Set.of("Bad", "So-so");
        }
    }

}
