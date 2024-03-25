package io.taf.config;

import org.apache.commons.compress.utils.CharsetNames;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
@SuppressWarnings("unused")
public class DefaultMessageConfig {

    @Bean
    public MessageSource messageSource() {
        var bean = new ReloadableResourceBundleMessageSource();
        bean.setDefaultEncoding(CharsetNames.UTF_8);
        bean.setBasenames("classpath:messages");
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        var bean = new SessionLocaleResolver();
        bean.setDefaultLocale(Locale.US);
        bean.setDefaultTimeZone(TimeZone.getDefault());
        return bean;
    }

}
