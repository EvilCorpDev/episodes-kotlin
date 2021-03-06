package com.nomaddev.server.services

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.nomaddev.server.manga.entity.Manga
import com.nomaddev.server.parser.MangaParserPattern
import com.nomaddev.server.repository.MangaOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileReader
import java.time.LocalDateTime
import java.util.*

@Service
open class EpisodesService @Autowired constructor(val mangaOps: MangaOperations, val parser: MangaParserPattern) {

    fun saveNewManga(url: String): Manga {
        val episode = parser.getFullInfo(url)
        val manga = Manga(episode = episode.episode, title = episode.title, img = episode.img, url = url,
                updateTime = LocalDateTime.now(), isNew = true)
        mangaOps.saveManga(manga)
        return manga
    }

    fun updateMangaEpisode(title: String): Manga {
        val mangaByTitle = mangaOps.findOneByTitle(title)
        val newEpisode = parser.getLastEpisode(mangaByTitle.url)
        mangaOps.updateEpisodeByTitle(newEpisode.episode, title)
        return Manga(episode = newEpisode.episode, title = mangaByTitle.title, img = mangaByTitle.img,
                url = mangaByTitle.url, updateTime = LocalDateTime.now(), isNew = true)
    }

    fun updateMangaEpisode(manga: Manga): Manga {
        val newEpisode = parser.getLastEpisode(manga.url)
        mangaOps.updateEpisodeByTitle(newEpisode.episode, manga.title)
        return Manga(episode = newEpisode.episode, title = manga.title, img = manga.img,
                url = manga.url, updateTime = LocalDateTime.now(), isNew = true)
    }

    fun listAllMangas() = mangaOps.findAllManga()

    fun listNewManga() = mangaOps.findNewManga()

    fun markAsRead(title: String) = mangaOps.markAsReadByTitle(title)

    fun importMangaFromJson(uploaded: MultipartFile) {
        val file = File(UUID.randomUUID().toString())
        uploaded.transferTo(file)
        val parser = JsonParser()
        val gson = Gson()
        val jsonObject = parser.parse(FileReader(file)).asJsonObject
        if (jsonObject.has("results")) {
            val resultsArray = jsonObject.getAsJsonArray("results")
            val mangas = resultsArray
                    .map { it.asJsonObject.get("jsonDoc").asString.replace("'\\\"", "\"") }
                    .map { gson.fromJson(it, Manga::class.java) }
            mangas.forEach { item -> saveNewManga(item.url) }
        }
    }
}