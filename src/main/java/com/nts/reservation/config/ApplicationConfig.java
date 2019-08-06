package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.nts.reservation" })
@Import({ DbConfig.class, WebMvcContextConfiguration.class })
public class ApplicationConfig {

}
