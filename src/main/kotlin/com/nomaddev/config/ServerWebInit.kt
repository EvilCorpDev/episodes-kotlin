package com.nomaddev.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class ServerWebInit : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getRootConfigClasses(): Array<out Class<*>> {
        return arrayOf(ServerRootConfig::class.java)
    }

    override fun getServletMappings(): Array<out String> {
        return arrayOf("/")
    }

    override fun getServletConfigClasses(): Array<out Class<*>> {
        return arrayOf(ServerRootConfig::class.java)
    }
}