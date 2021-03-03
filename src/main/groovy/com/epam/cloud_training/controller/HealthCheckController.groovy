package com.epam.cloud_training.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static com.epam.cloud_training.constants.Constants.I18N.DEFAULT_LOCALE

@RestController
@RequestMapping ('/api')
class HealthCheckController {

    public static final String MESSAGE = 'hello.message'

    @Autowired
    ApplicationContext context
    @Autowired
    Environment env

    @GetMapping('/status')
    String status(){
        "I feel fine"
    }

    @GetMapping('/hello')
    String getProperty(){
        def locale = new Locale(env.getProperty(DEFAULT_LOCALE))
        context.getMessage(MESSAGE, null, locale)
    }

}
