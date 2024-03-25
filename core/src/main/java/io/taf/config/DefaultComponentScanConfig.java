package io.taf.config;

import io.taf.TafPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {TafPackageMarker.class})
@SuppressWarnings("unused")
public class DefaultComponentScanConfig {
}
