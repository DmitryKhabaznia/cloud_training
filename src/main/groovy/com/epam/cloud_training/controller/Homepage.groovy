package com.epam.cloud_training.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Homepage {

    @GetMapping('/home')
    String getHomepage(){
        'homepage'
    }

}
