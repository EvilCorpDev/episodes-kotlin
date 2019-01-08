package com.nomaddev.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
@EnableWebMvc
@ComponentScan("com.nomaddev")
open class ServerRootConfig : WebMvcConfigurerAdapter() {

    val MAX_UPLOAD_SIZE = 8000000L

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }

    @Bean
    open fun multipartResolver(): CommonsMultipartResolver {
        val multipartResolver = CommonsMultipartResolver()
        multipartResolver.setDefaultEncoding("utf-8")
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE)
        return multipartResolver
    }
}
