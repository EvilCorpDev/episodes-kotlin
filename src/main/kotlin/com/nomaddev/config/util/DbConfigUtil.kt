package com.nomaddev.config.util

import com.nomaddev.config.properties.DbProperties
import org.springframework.core.env.Environment

class DbConfigUtil {

    companion object {
        private val mongoHost = "mongodb.host"
        private val mongoUser = "mongodb.user"
        private val mongoPass = "mongodb.pass"
        private val mongoDbName = "mongodb.name"
        private val mongoPort = "mongodb.port"

        fun copyMongoDbProperties(env: Environment) = DbProperties(env.getProperty(mongoHost),
                env.getProperty(mongoPort).toInt(), env.getProperty(mongoDbName),
                env.getProperty(mongoUser), env.getProperty(mongoPass).toCharArray())
    }
}