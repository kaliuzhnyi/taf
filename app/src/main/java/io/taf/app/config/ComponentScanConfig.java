package io.taf.app.config;

import io.taf.app.AppPackageMarker;
import io.taf.config.DefaultComponentScanConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = {AppPackageMarker.class})
@Import(DefaultComponentScanConfig.class)
@SuppressWarnings("unused")
public class ComponentScanConfig {
}