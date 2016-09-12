package com.nomaddev.server.repository

import com.nomaddev.server.manga.entity.Manga
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class MangaOperations @Autowired constructor(val mongoOps: MongoTemplate){

    fun findOneByTitle(title: String): Manga {
        val findQuery = Query().addCriteria(Criteria.where("title").`is`(title))
        return mongoOps.findOne(findQuery, Manga::class.java)
    }

    fun updateEpisodeByTitle(episode: Double, title: String) {
        val findQuery = Query().addCriteria(Criteria.where("title").`is`(title))
        val update = Update()
        update.set("episode", episode)
        mongoOps.updateFirst(findQuery, update, Manga::class.java)
    }

    fun saveManga(manga: Manga) {
        mongoOps.save(manga)
    }

}