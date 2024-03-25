package io.taf.utils;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

@Component
public class MessageProvider implements MessageSourceAware {

    public static final String VALUE_START = "${";
    public static final String VALUE_END = "}";

    @Delegate
    @Getter(onMethod_ = {@Nonnull})
    private MessageSource messageSource;

    @Override
    public void setMessageSource(@Nonnull MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Messages

    public String getMessage(@Nonnull String code) {
        return getMessage(code, null);
    }

    public String getMessage(@Nonnull String code, @Nullable String defaultMessage) {
        return getMessage(code, null, defaultMessage, LocaleContextHolder.getLocale());
    }

    // Values

    public String getValue(@Nonnull String code) {
        return getValue(code, null, null, null);
    }

    public String getValue(@Nonnull String code, @Nonnull String defaultMessage) {
        return getValue(code, null, defaultMessage, null);
    }

    public String getValue(@Nonnull String code, @Nullable Object[] args, @Nullable String defaultMessage) {

        if (code.isBlank()) {
            return defaultMessage;
        }

        if (code.startsWith(VALUE_START) && code.endsWith(VALUE_END)) {
            code = code.substring(VALUE_START.length(), code.length() - VALUE_END.length());
        }

        return getMessage(code, args, defaultMessage, null);
    }

    public String getValue(@Nonnull String code, @Nullable Object[] args, @Nullable String defaultMessage, @Nullable Locale locale) {

        if (code.isBlank()) {
            return defaultMessage;
        }

        return getMessage(defineCode(code), args, defaultMessage, defineLocale(locale));
    }

    // Others

    public String getApplicationName() {
        return getMessage("taf.application.name", "");
    }

    public String getApplicationWelcomeTitle() {
        return getMessage("taf.application.welcome.title", "");
    }

    public String getApplicationWelcomeText() {
        return getMessage("taf.application.welcome.text", "");
    }


    private String defineCode(@Nonnull String code) {
        if (code.startsWith(VALUE_START) && code.endsWith(VALUE_END)) {
            return code.substring(VALUE_START.length(), code.length() - VALUE_END.length());
        }
        return code;
    }

    private Locale defineLocale(@Nullable Locale locale) {
        return Optional.ofNullable(locale).orElseGet(LocaleContextHolder::getLocale);
    }


    public static MessageProvider getMessageProvider() {
        return ApplicationContextProvider.getApplicationContext().getBean(MessageProvider.class);
    }

    public static Function<String, String> getMessageProviderValueFunctional() {
        return s -> getMessageProvider().getValue(s);
    }

}
