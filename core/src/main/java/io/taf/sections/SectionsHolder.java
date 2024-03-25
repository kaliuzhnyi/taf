package io.taf.sections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SectionsHolder {

    @Autowired
    private final List<Section> sections = new ArrayList<>();

    public List<Section> getSections() {
        sections.sort(OrderComparator.INSTANCE);
        return sections;
    }

}
