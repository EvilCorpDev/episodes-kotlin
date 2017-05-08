package com.nomaddev.server.repository

import com.nomaddev.server.manga.entity.Manga
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
open class MangaOperations @Autowired constructor(val mongoOps: MongoTemplate) {

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

    fun markAsReadByTitle(title: String) {
        val findQuery = Query().addCriteria(Criteria.where("title").`is`(title))
        val update = Update()
        update.set("isNew", false)
        mongoOps.updateFirst(findQuery, update, Manga::class.java)
    }

    fun saveManga(manga: Manga) {
        mongoOps.save(manga)
    }

    fun findAllManga() = mongoOps.findAll(Manga::class.java)

    fun findNewManga(): List<Manga> {
        val query = Query.query(Criteria.where("isNew").`is`(true))
        return mongoOps.find(query, Manga::class.java)
    }

    fun delManga(title: String): Manga {
        val findQuery = Query().addCriteria(Criteria.where("title").`is`(title))
        return mongoOps.findAndRemove(findQuery, Manga::class.java)
    }

    fun findOneById(mangaId: String): Manga {
        return mongoOps.findById(mangaId, Manga::class.java)
    }

    fun updateEpisodeById(episode: Double, mangaId: String) {
        val findQuery = Query().addCriteria(Criteria.where("id").`is`(mangaId))
        val update = Update()
        update.set("episode", episode)
        mongoOps.updateFirst(findQuery, update, Manga::class.java)
    }

    fun markAsReadById(mangaId: String) {
        val findQuery = Query().addCriteria(Criteria.where("id").`is`(mangaId))
        val update = Update()
        update.set("isNew", false)
        mongoOps.updateFirst(findQuery, update, Manga::class.java)
    }

    fun delMangaById(mangaId: String): Manga {
        val findQuery = Query().addCriteria(Criteria.where("id").`is`(mangaId))
        return mongoOps.findAndRemove(findQuery, Manga::class.java)
    }
}