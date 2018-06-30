package com.nomaddev.config.db

import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.nomaddev.config.util.DbConfigUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = arrayOf("com.nomaddev.server.repository"))
@PropertySource("classpath:application.properties")
open class MongoDbConfig @Autowired constructor(env: Environment): AbstractMongoConfiguration() {

    private val prop = DbConfigUtil.copyMongoDbProperties(env)

    override fun mongo(): MongoClient {
        val credentials = MongoCredential.createCredential(prop.user, prop.name, prop.pass)
        val addr = ServerAddress(prop.host, prop.port)
        return MongoClient(addr, arrayListOf(credentials))
    }

    override fun getDatabaseName(): String = prop.name
}