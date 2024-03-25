package io.taf.app;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.taf.layouts.DefaultAppLayout;
import io.taf.views.common.DefaultWelcomeView;

@PageTitle("Main")
@Route(value = "", layout = DefaultAppLayout.class)
public class WelcomeView extends DefaultWelcomeView {
}
