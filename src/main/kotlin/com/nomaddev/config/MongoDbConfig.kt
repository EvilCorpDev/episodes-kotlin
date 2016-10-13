package com.nomaddev.config

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = arrayOf("com.nomaddev.server.repository"))
@PropertySource("classpath:application.properties")
open class MongoDbConfig @Autowired constructor(val env: Environment): AbstractMongoConfiguration() {

    val mongoHost = "mongodb.host"
    val mongoUser = "mongodb.host"
    val mongoPass = "mongodb.host"
    val mongoDbName = "mongodb.host"
    val mongoPort = "mongodb.host"

    override fun mongo(): MongoClient {
        val credentials = MongoCredential.createCredential(env.getProperty(mongoUser), env.getProperty(mongoDbName),
                env.getProperty(mongoPass).toCharArray())
        val addr = ServerAddress(env.getProperty(mongoHost), env.getProperty(mongoPort).toInt())
        return MongoClient(addr, arrayListOf(credentials))
    }

    override fun getDatabaseName() = env.getProperty(mongoDbName)
}