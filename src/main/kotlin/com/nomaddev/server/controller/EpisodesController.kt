package com.nomaddev.server.controller

import com.nomaddev.server.episode.entity.Episode
import com.nomaddev.server.parser.ParserPattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EpisodesController @Autowired constructor(val parser: ParserPattern) {

    @RequestMapping(value = "/episodes", method = arrayOf(RequestMethod.GET))
    fun getLastEpisode(@RequestParam("url") managaUrl: String): Episode {
        return parser.parse(managaUrl)
    }
}