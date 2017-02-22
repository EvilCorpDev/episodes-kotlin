package com.nomaddev.server.parser

import com.nomaddev.server.manga.entity.*

interface MangaParserPattern {

    fun getFullInfo(url: String): Episode

    fun getLastEpisode(url: String): Episode
}
