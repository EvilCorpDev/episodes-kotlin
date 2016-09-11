package com.nomaddev.server.services

import com.nomaddev.server.manga.entity.Manga
import com.nomaddev.server.parser.MangaParserPattern
import com.nomaddev.server.repository.MangaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class EpisodesService @Autowired constructor(val mangaRepo: MangaRepository, val parser: MangaParserPattern){

    fun saveManga(url: String): Manga {
        val episode = parser.getFullInfo(url)
        val manga = Manga(episode = episode.episode, title = episode.title, img = episode.img, url = url,
                updateTime = ZonedDateTime.now())
        return mangaRepo.save(manga)
    }
}