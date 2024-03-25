package io.taf.app.sections;

import io.taf.sections.AbstractSection;
import io.taf.sections.SectionComponent;
import org.springframework.core.annotation.Order;

@SectionComponent(title = "${section.title.catalogs}", description = "${section.description.catalogs}")
@Order(10)
public class CatalogsSection extends AbstractSection {
}
