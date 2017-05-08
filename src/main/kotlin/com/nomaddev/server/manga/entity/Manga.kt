package com.nomaddev.server.manga.entity

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.nomaddev.config.serializer.JsonMangaSerializer
import java.time.LocalDateTime

@JsonSerialize(using = JsonMangaSerializer::class)
class Manga(val id: String? = null, val episode: Double = 0.0, val title: String = "", val img: String = "",
            val url: String = "", val updateTime: LocalDateTime? = null, val isNew: Boolean = false)