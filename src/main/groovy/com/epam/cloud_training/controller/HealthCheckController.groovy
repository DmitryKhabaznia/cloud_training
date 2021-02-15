package com.epam.cloud_training.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping ('/api')
class HealthCheckController {

    @GetMapping('/status')
    String status(){
        "I feel fine"
    }

}
