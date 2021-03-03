package com.epam.cloud_training

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@SpringBootApplication
class CloudTrainingApplication {

	static void main(String[] args) {
		SpringApplication.run(CloudTrainingApplication, args)
	}

}
