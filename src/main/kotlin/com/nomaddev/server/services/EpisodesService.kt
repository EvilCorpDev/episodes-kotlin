package com.nomaddev.server.services

import com.nomaddev.server.manga.entity.Manga
import com.nomaddev.server.parser.MangaParserPattern
import com.nomaddev.server.repository.MangaOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
open class EpisodesService @Autowired constructor(val mangaOps: MangaOperations, val parser: MangaParserPattern){

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
}