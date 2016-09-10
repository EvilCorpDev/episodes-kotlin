package com.nomaddev.server.controller

import com.nomaddev.server.manga.entity.Episode
import com.nomaddev.server.parser.ParserPattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class EpisodesController @Autowired constructor(val parser: ParserPattern) {

    @RequestMapping(value = "/episodes/{title}", method = arrayOf(RequestMethod.GET))
    fun getLastEpisode(@PathVariable("title") managaUrl: String): Episode {
        return parser.parse(managaUrl)
    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.GET))
    fun listAllEpisodes() {

    }

    @RequestMapping(value = "/episodes/{username}", method = arrayOf(RequestMethod.GET))
    fun listMyEpisodes(@PathVariable("username") username: String) {

    }

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.POST))
    fun addNewManga(mangaUrl: String) {

    }
}