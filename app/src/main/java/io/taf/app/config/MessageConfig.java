package io.taf.app.config;

import io.taf.config.DefaultMessageConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Import(DefaultMessageConfig.class)
@SuppressWarnings("unused")
public class MessageConfig implements InitializingBean {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        var messageSource = (ReloadableResourceBundleMessageSource) this.messageSource;
        messageSource.addBasenames("classpath:app_messages");
    }
}
