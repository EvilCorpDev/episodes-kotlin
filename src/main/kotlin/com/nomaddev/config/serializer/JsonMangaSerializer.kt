package com.nomaddev.config.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.nomaddev.server.manga.entity.Manga
import java.time.format.DateTimeFormatter

open class JsonMangaSerializer(t: Class<Manga>?) : StdSerializer<Manga>(t) {

    constructor() : this(null) {
    }

    val formatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun serialize(value: Manga?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeStartObject()
        gen?.writeStringField("id", value?.id)
        gen?.writeNumberField("episode", value?.episode!!.toDouble())
        gen?.writeStringField("title", value?.title)
        gen?.writeStringField("img", value?.img)
        gen?.writeStringField("url", value?.url)
        gen?.writeStringField("updateTime", formatter?.format(value?.updateTime))
        gen?.writeBooleanField("isNew", value?.isNew as Boolean)
        gen?.writeEndObject()
    }
}