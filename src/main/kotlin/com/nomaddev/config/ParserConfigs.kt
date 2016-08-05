package com.nomaddev.config

import com.nomaddev.server.episode.enum.*
import com.nomaddev.server.parser.builder.*
import com.nomaddev.server.parser.builder.entity.*
import org.springframework.context.annotation.*

@Configuration
class ParserConfigs {

    @Bean
    fun readmangaTodayParser(): ParserBuilder {
        return ParserBuilder(SupportedSites.READMANGA_TODAY,
            title = HtmlChain().appendAction(tag = "h1",
                 parent = HtmlChainElement(attr = HtmlAttribute("class", "panel-heading")))
                .appendAction(attr = HtmlAttribute("method", "html"), isEndAction = true),
            img = HtmlChain().appendAction(attr = HtmlAttribute("class", "img-responsive"))
                .appendAction(attr = HtmlAttribute("attribute", "src"), isEndAction = true),
            episode = HtmlChain().appendAction(attr = HtmlAttribute("class", "chp_lst"))
                .appendAction(tag = "a")
                .appendAction(attr = HtmlAttribute("attribute", "href"), isEndAction = true))
    }
}