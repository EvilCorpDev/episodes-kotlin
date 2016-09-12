package com.nomaddev.server.manga.entity

import java.time.LocalDateTime

class Manga(val id: String? = null, val episode: Double = 0.0, val title: String = "", val img: String = "",
            val url: String = "", val updateTime: LocalDateTime? = null)