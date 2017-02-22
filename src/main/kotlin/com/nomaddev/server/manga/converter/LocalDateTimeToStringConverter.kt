package com.nomaddev.server.manga.converter

import org.springframework.core.convert.converter.Converter
import java.time.LocalDateTime

class LocalDateTimeToStringConverter: Converter<LocalDateTime, String> {
    override fun convert(source: LocalDateTime?): String {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}