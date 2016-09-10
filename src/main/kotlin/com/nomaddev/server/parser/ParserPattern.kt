package com.nomaddev.server.parser

import com.nomaddev.server.manga.entity.*

interface ParserPattern {

    fun parse(url: String): Episode
}
