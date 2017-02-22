package com.nomaddev.server.controller

import com.nomaddev.server.manga.entity.Manga
import com.nomaddev.server.services.EpisodesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EpisodesController @Autowired constructor(val episodesService: EpisodesService) {

    @RequestMapping(value = "/episodes/last/{title}", method = arrayOf(RequestMethod.GET))
    fun getLastEpisode(@PathVariable("title") mangaTitle: String): Manga {
        return episodesService.updateMangaEpisode(mangaTitle.replace("_", " "))
    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.GET))
    fun listAllEpisodes() = episodesService.listAllMangas()

    @RequestMapping(value = "/episodes/{username}", method = arrayOf(RequestMethod.GET))
    fun listMyEpisodes(@PathVariable("username") username: String) {

    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.POST))
    fun addNewManga(@RequestParam("mangaUrl") mangaUrl: String): Manga {
        return episodesService.saveNewManga(mangaUrl)
    }
}