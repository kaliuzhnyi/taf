package io.taf.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.taf.sections.SectionsHolder;
import io.taf.utils.MessageProvider;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.Optional;

@RequiredArgsConstructor
public class DefaultAppLayout extends AppLayout {

    @Autowired
    private final SectionsHolder sectionsHolder;

    @Autowired
    private final MessageProvider messageProvider;

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Component applicationTitle = initApplicationTitle();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Component applicationNavigation = initApplicationNavigation();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Component viewHeader = initViewHeader();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Component viewTitle = initViewTitle();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Component drawerToggle = initDrawerToggle();

    @PostConstruct
    public void init() {
        initDrawer();
        initNavbar();
        setPrimarySection(Section.DRAWER);
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        ((HasText) getViewTitle()).setText(getViewTitleText());
    }

    // Drawer

    protected void initDrawer() {
        addToDrawer(getApplicationTitle(), getApplicationNavigation());
    }

    protected Component initApplicationTitle() {

        var h1 = new H1(messageProvider.getApplicationName());
        h1.addClassNames(LumoUtility.FontSize.XLARGE);

        var header = new Header();
        header.addClassNames(LumoUtility.Margin.MEDIUM);
        header.add(h1);

//        var button = new Button();
//        button.setIcon(VaadinIcon.LINK.create());
//        button.addClickListener(event -> UI.getCurrent().navigate(""));

        var container = new HorizontalLayout();
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.add(header);

        return container;
    }

    @SuppressWarnings("unchecked")
    protected Component initApplicationNavigation() {

        var sideNav = new SideNav();
        sectionsHolder.getSections().stream()
                .filter(section -> section.isVisible())
                .forEach(section -> {
                    var item = new SideNavItem(section.getTitle());

                    Optional.ofNullable(section.getTooltip()).ifPresent(text ->
                            Tooltip.forComponent(item)
                                    .withText(text));

                    section.getContent().forEach(element -> {
                        var view = element.getViewClass();
                        var subItem = new SideNavItem(element.getTitle());
                        if (view.isAnnotationPresent(Route.class)) {
                            subItem.setPath(view);
                        }

                        Optional.ofNullable(element.getTooltip()).ifPresent(text ->
                                Tooltip.forComponent(subItem)
                                        .withText(text));

                        item.addItem(subItem);
                    });

                    sideNav.addItem(item);
                });

        var scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.setContent(sideNav);

        return scroller;
    }

    // Navbar

    protected void initNavbar() {
        addToNavbar(getViewHeader());
    }

    protected Component initViewHeader() {
        var viewHeader = new HorizontalLayout();
        viewHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        viewHeader.setSpacing(false);
        viewHeader.add(getDrawerToggle(), getViewTitle());
        return viewHeader;
    }

    protected Component initViewTitle() {
        var h2 = new H2(getViewTitleText());
        h2.addClassNames(LumoUtility.FontSize.MEDIUM);
        return h2;
    }

    protected String getViewTitleText() {
        return Optional.ofNullable(getContent())
                .map(Object::getClass)
                .map(cls -> cls.getAnnotation(PageTitle.class))
                .map(PageTitle::value)
                .orElse("");
    }

    protected Component initDrawerToggle() {
        var drawerToggle = new DrawerToggle();
        return drawerToggle;
    }

}
