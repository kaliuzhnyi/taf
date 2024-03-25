package io.taf.views.common;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.taf.utils.MessageProvider;
import io.taf.views.common.AbstractView;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultWelcomeView extends AbstractView {

    public static final String WELCOME_COMPONENT_ID = "welcome-block";

    @Autowired
    private MessageProvider messageProvider;

    @Nonnull
    @Override
    protected HorizontalLayout initBodyPanel() {
        var bodyPanel = super.initBodyPanel();
        bodyPanel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        bodyPanel.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        bodyPanel.add(getComponent(WELCOME_COMPONENT_ID, Div.class, initWelcomeBlock()));
        return bodyPanel;
    }

    @Nonnull
    protected Div initWelcomeBlock() {

        var title = new Text(messageProvider.getApplicationWelcomeTitle());
        var text = new Text(messageProvider.getApplicationWelcomeText());

        var titleElement = new Paragraph();
        titleElement.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.FontWeight.BOLD);
        titleElement.add(title);

        var textElement = new Paragraph();
        titleElement.addClassNames(LumoUtility.FontSize.MEDIUM, LumoUtility.FontWeight.NORMAL);
        textElement.add(text);

        var div = new Div();
        div.setWidth(50, Unit.PERCENTAGE);
        div.add(titleElement, textElement);

        return div;
    }

}
