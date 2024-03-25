package io.taf.views.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class BaseComposite<T extends Component>
        extends Composite<T> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private final Map<String, Component> components = new HashMap<>();


    protected <C extends Component> void addComponent(@Nonnull String componentId, @Nonnull Supplier<C> supplier) {
        addComponent(componentId, supplier.get());
    }

    protected <C extends Component> void addComponent(@Nonnull String componentId, @Nonnull C component) {
        getComponents().put(componentId, component);
    }


    @Nonnull
    protected <C extends Component> C getComponent(@Nonnull String componentId, @Nonnull Class<C> cls, @Nonnull Supplier<C> supplier) {
        return getComponent(componentId, cls, supplier.get());
    }

    @Nonnull
    protected <C extends Component> C getComponent(@Nonnull String componentId, @Nonnull Class<C> cls, @Nonnull C component) {
        return findComponent(componentId, cls)
                .orElseGet(() -> {
                    addComponent(componentId, component);
                    return component;
                });
    }

    @Nonnull
    protected <C extends Component> C getComponent(@Nonnull String componentId, @Nonnull Class<C> cls) throws NoSuchElementException {
        return findComponent(componentId, cls).orElseThrow();
    }

    @Nonnull
    protected Component getComponent(@Nonnull String componentId) throws NoSuchElementException {
        return findComponent(componentId).orElseThrow();
    }


    @Nonnull
    protected <C extends Component> Optional<C> findComponent(@Nonnull String componentId, @Nonnull Class<C> cls) {
        return findComponent(componentId).map(cls::cast);
    }

    @Nonnull
    protected Optional<Component> findComponent(@Nonnull String componentId) {
        return Optional.ofNullable(getComponents().get(componentId));
    }

}
