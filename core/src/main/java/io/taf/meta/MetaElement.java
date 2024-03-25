package io.taf.meta;

import com.vaadin.flow.component.Component;
import io.taf.views.common.View;

public interface MetaElement<VIEW extends Component & View> extends MetaObject {

    Class<VIEW> getViewClass();

}
