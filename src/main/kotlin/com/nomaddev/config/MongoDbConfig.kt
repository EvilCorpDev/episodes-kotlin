package com.nomaddev.config

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = arrayOf("com.nomaddev.server.repository"))
@PropertySource("classpath:application.properties")
open class MongoDbConfig: AbstractMongoConfiguration() {

    @Value("\${mongodb.host}") lateinit var mongoHost: String
    @Value("\${mongodb.name}") lateinit var mongoDbName: String
    @Value("\${mongodb.port}") lateinit var mongoPort: String
    @Value("\${mongodb.user}") lateinit var mongoUser: String
    @Value("\${mongodb.pass}") lateinit var mongoPass: String

    override fun mongo(): MongoClient {
        val credentials = MongoCredential.createCredential(mongoUser, mongoDbName, mongoPass.toCharArray())
        val addr = ServerAddress(mongoHost, mongoPort.toInt())
        return MongoClient(addr, arrayListOf(credentials))
    }

    override fun getDatabaseName() = mongoDbName
}