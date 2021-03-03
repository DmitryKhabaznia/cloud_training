package com.epam.cloud_training.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.core.env.Environment
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver

import static com.epam.cloud_training.constants.Constants.I18N.DEFAULT_LOCALE
import static com.epam.cloud_training.constants.Constants.I18N.ENCODING
import static com.epam.cloud_training.constants.Constants.I18N.BUNDLE_NAME

@Configuration
class I18nConfiguration {


    @Autowired
    Environment env

    @Bean
    LocaleResolver localeResolver() {
        new SessionLocaleResolver(defaultLocale: new Locale(env.getProperty(DEFAULT_LOCALE)))
    }

    @Bean
    MessageSource messageSource() {
        new ResourceBundleMessageSource(basename: BUNDLE_NAME, defaultEncoding: ENCODING, useCodeAsDefaultMessage: true)
    }
}
