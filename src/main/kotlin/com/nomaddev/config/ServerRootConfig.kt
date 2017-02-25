package com.nomaddev.config

import com.nomaddev.server.manga.converter.LocalDateTimeToStringConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.Jsr310Converters
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan("com.nomaddev")
open class ServerRootConfig : WebMvcConfigurerAdapter() {

    val MAX_UPLOAD_SIZE = 8000000L

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }

    @Bean
    open fun dateToLocalDateTimeConverter() = Jsr310Converters.DateToLocalDateTimeConverter.INSTANCE

    @Bean
    open fun localDateTimeToDateConverter() = Jsr310Converters.LocalDateTimeToDateConverter.INSTANCE

    @Bean
    open fun localDateTimeToStringConveter() = LocalDateTimeToStringConverter()

    @Bean
    open fun bCryptEncoder() = BCryptPasswordEncoder()

    @Bean
    open fun multipartResolver(): CommonsMultipartResolver {
        val multipartResolver = CommonsMultipartResolver()
        multipartResolver.setDefaultEncoding("utf-8")
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE)
        return multipartResolver
    }
}