package com.nomaddev.server.parser.impl

import com.nomaddev.server.episode.entity.Episode
import com.nomaddev.server.episode.enum.SupportedSites
import com.nomaddev.server.exception.UnsupportedSiteException
import com.nomaddev.server.parser.ParserPattern
import com.nomaddev.server.parser.builder.ParserBuilder
import com.nomaddev.server.parser.builder.entity.HtmlChain
import com.nomaddev.server.parser.builder.entity.HtmlChainElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class JsoupParser @Autowired constructor(val applicationContext: ApplicationContext) : ParserPattern {

    override fun parse(url: String): Episode {
        val builder = getCorrectParserBuilder(url)
        val doc: Document = Jsoup.connect(url).get()
        return Episode(getEpisode(builder.episode, doc), getImg(builder.img, doc), getTitle(builder.title, doc))
    }

    private fun getTitle(builder: HtmlChain, doc: Document): String {
        //doc.select()
        return ""
    }

    private fun getImg(builder: HtmlChain, doc: Document): String {
        return ""
    }

    private fun getEpisode(builder: HtmlChain, doc: Document): Int {
        return 0
    }

    private fun getCorrectParserBuilder(url: String): ParserBuilder {
        return when (url) {
            SupportedSites.READMANGA_TODAY.name -> applicationContext.getBean("readmangaTodayParser") as ParserBuilder
            else -> throw UnsupportedSiteException("We doesn't support site that you currently trying to get info")
        }
    }
}