package com.nomaddev.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Zakhar_Kliap on 07-Jul-16.
 */
@Configuration
@ComponentScan("com.mkyong.helloworld.service")
class ServerRootConfig {
}