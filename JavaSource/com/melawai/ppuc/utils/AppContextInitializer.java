package com.melawai.ppuc.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class AppContextInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadAllProperties("/some/path");
            PropertiesPropertySource ps = new PropertiesPropertySource("profile", props);
            applicationContext.getEnvironment().getPropertySources().addFirst(ps);
        } catch (IOException e) {
            // handle error
        }
    }
} 
