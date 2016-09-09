package com.nomaddev.config

import com.nomaddev.server.episode.enum.*
import com.nomaddev.server.parser.builder.*
import com.nomaddev.server.parser.builder.entity.*
import com.nomaddev.server.parser.builder.entity.TagEntity.*
import org.springframework.context.annotation.*

@Configuration
open class ParserConfigs {

    @Bean
    open fun readMangaTodayParser() = ParserBuilder(SupportedSites.READMANGA_TODAY,
                title = SelectorChain(ExecuteAction("html", METHOD)).append(".panel-heading").append("h1"),
                img = SelectorChain(ExecuteAction("src", ATTRIBUTE)).append(".img-responsive"),
                episode = SelectorChain(ExecuteAction("href", ATTRIBUTE)).append(".chp_lst").append("a"))

    @Bean
    open fun readMangaMeParser() = ParserBuilder(SupportedSites.READMANGA_ME,
            title = SelectorChain(ExecuteAction("html", METHOD)).append(".name"),
            img = SelectorChain(ExecuteAction("src", ATTRIBUTE)).append(".picture-fotorama").append("img"),
            episode = SelectorChain(ExecuteAction("href", ATTRIBUTE)).append(".chapters-link").append("a"))

    @Bean
    open fun mangaFoxParser() = ParserBuilder(SupportedSites.MANGA_FOX,
            title = SelectorChain(ExecuteAction("html", METHOD)).append("#title").append("h1"),
            img = SelectorChain(ExecuteAction("src", ATTRIBUTE)).append(".cover").append("img"),
            episode = SelectorChain(ExecuteAction("href", ATTRIBUTE)).append(".chlist").append("a.tips"))

    @Bean
    open fun mangaReader() = ParserBuilder(SupportedSites.MANGA_READER,
            title = SelectorChain(ExecuteAction("html", METHOD)).append("#mangaproperties").append("h1"),
            img = SelectorChain(ExecuteAction("src", ATTRIBUTE)).append("#mangaimg").append("img"),
            episode = SelectorChain(ExecuteAction("href", ATTRIBUTE)).append("#latestchapters"))
}