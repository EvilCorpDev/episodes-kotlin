package com.nomaddev.server.manga.entity

import java.time.ZonedDateTime

class Manga(val id: String? = null, val episode: Double, val title: String, val img: String, val url: String,
            val updateTime: ZonedDateTime)