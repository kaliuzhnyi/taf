package io.taf.app.sections;

import io.taf.sections.AbstractSection;
import io.taf.sections.SectionComponent;
import org.springframework.core.annotation.Order;

@SectionComponent(title = "${section.title.documents}", description = "${section.description.documents}")
@Order(20)
public class DocumentsSection extends AbstractSection {
}
