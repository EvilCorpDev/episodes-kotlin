package com.nomaddev.server.controller

import com.nomaddev.server.manga.entity.Manga
import com.nomaddev.server.services.EpisodesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class EpisodesController @Autowired constructor(val episodesService: EpisodesService) {

    @RequestMapping(value = "/episodes/last/{title}", method = arrayOf(RequestMethod.GET))
    fun getLastEpisode(@PathVariable("title") mangaTitle: String): Manga {
        return episodesService.updateMangaEpisode(mangaTitle.replace("_", " "))
    }

    @RequestMapping(value = "/episodes/last/id/{id}", method = arrayOf(RequestMethod.GET))
    fun getLastEpisodeById(@PathVariable("id") mangaId: String): Manga {
        return episodesService.updateMangaEpisodeById(mangaId)
    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.GET))
    fun listAllEpisodes() = episodesService.listAllMangas()

    @RequestMapping(value = "/episodes/new", method = arrayOf(RequestMethod.GET))
    fun listNewEpisodes() = episodesService.listNewManga()

    @RequestMapping(value = "/episodes/title/{title}/read", method = arrayOf(RequestMethod.PUT))
    fun markAsRead(@PathVariable("title") mangaTitle: String) = episodesService.markAsRead(title = mangaTitle.replace("_", " "))

    @RequestMapping(value = "/episodes/id/{id}/read", method = arrayOf(RequestMethod.PUT))
    fun markAsReadById(@PathVariable("id") mangaId: String) = episodesService.markAsReadById(mangaId)

    @RequestMapping(value = "/episodes/{username}", method = arrayOf(RequestMethod.GET))
    fun listMyEpisodes(@PathVariable("username") username: String) {

    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.POST))
    fun addNewManga(@RequestParam("mangaUrl") mangaUrl: String): Manga {
        return episodesService.saveNewManga(mangaUrl)
    }

    @RequestMapping(value = "/upload", method = arrayOf(RequestMethod.POST))
    fun importMangaFromJson(@RequestParam("file") uploaded : MultipartFile) {
        episodesService.importMangaFromJson(uploaded)
    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.DELETE))
    fun delMangaByTitle(@RequestParam("title") mangaTitle: String) = episodesService.delManga(title = mangaTitle.replace("_", " "))

    @RequestMapping(value = "/episodes/id/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delMangaById(@PathVariable("id") mangaId: String) = episodesService.delMangaById(mangaId)
}