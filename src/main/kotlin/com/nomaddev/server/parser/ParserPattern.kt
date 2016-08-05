package com.nomaddev.server.parser

import com.nomaddev.server.episode.entity.*

interface ParserPattern {

    fun parse(url: String): Episode
}
