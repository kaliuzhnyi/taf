package io.taf.app.sections;

import io.taf.sections.Section;
import io.taf.sections.SectionComponent;
import jakarta.annotation.Nonnull;
import org.springframework.core.annotation.Order;

@SectionComponent
@Order(30)
public class AdminSection implements Section {

    @Nonnull
    @Override
    public String getTitle() {
        return "Admin";
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "Admin Section Description";
    }

}
