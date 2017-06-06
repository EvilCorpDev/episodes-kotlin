package com.nomaddev.server.parser.impl

import com.nomaddev.server.exception.UnsuportedTagEntity
import com.nomaddev.server.exception.UnsupportedSiteException
import com.nomaddev.server.manga.entity.Episode
import com.nomaddev.server.manga.enum.SupportedSites.*
import com.nomaddev.server.parser.MangaParserPattern
import com.nomaddev.server.parser.builder.ParserBuilder
import com.nomaddev.server.parser.builder.entity.SelectorChain
import com.nomaddev.server.parser.builder.entity.TagEntity.ATTRIBUTE
import com.nomaddev.server.parser.builder.entity.TagEntity.METHOD
import org.apache.commons.lang3.StringUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.util.ReflectionUtils
import java.net.URI

@Component
open class JsoupMangaParser @Autowired constructor(val applicationContext: ApplicationContext) : MangaParserPattern {

    private val PATH_SEPARATOR = '/'

    override fun getLastEpisode(url: String): Episode {
        val builder = getCorrectParserBuilder(url)
        val doc = downloadDoc(url)
        val episode = Episode(extractDigits(getElement(builder.episode, doc)), "", "")

        if (episode.episode < 0) {
            if (builder.alternativeEpisode != null) {
                val alternative = downloadDoc(url + PATH_SEPARATOR + 1)
                val newEpisode = Episode(extractDigits(getElement(builder.alternativeEpisode, alternative, 1)), "", "")
                if(newEpisode.episode > 0) {
                    return newEpisode
                }
            }
        }

        return episode
    }

    override fun getFullInfo(url: String): Episode {
        val builder = getCorrectParserBuilder(url)
        val doc = downloadDoc(url)
        return Episode(extractDigits(getElement(builder.episode, doc)), getElement(builder.img, doc).toString(),
                getElement(builder.title, doc).toString())
    }

    private fun getElement(builder: SelectorChain, doc: Document, index: Int = 0): Any {
        val elementArr = doc.select(builder.constructSelector())
        if (elementArr.size == 0) {
            return StringUtils.EMPTY
        }
        val element = elementArr[index]
        return when (builder.attr.type) {
            ATTRIBUTE -> element.attr(builder.attr.value)
            METHOD -> {
                val findMethod = ReflectionUtils.findMethod(element.javaClass, builder.attr.value)
                return ReflectionUtils.invokeMethod(findMethod, element)
            }
            else -> throw UnsuportedTagEntity("We doesn't support entity with type ${builder.attr.type} yet.")
        }
    }

    private fun extractDigits(str: Any): Double {
        if (StringUtils.isBlank(str as CharSequence?)) {
            return -1.0
        }
        var digitsString = str.toString()
        if (digitsString.last() == PATH_SEPARATOR) {
            digitsString = digitsString.dropLast(1)
        }
        if (digitsString.indexOf(PATH_SEPARATOR) != -1) {
            return digitsString.substring(digitsString.lastIndexOf(PATH_SEPARATOR))
                    .replace("\\D+".toRegex(), "").toDouble()
        } else {
            return digitsString.replace("\\D+".toRegex(), "").toDouble()
        }
    }

    private fun getCorrectParserBuilder(url: String): ParserBuilder {
        return when (URI.create(url).host) {
            URI.create(READMANGA_TODAY.url).host -> applicationContext.getBean(READMANGA_TODAY.beanName) as ParserBuilder
            URI.create(READMANGA_ME.url).host -> applicationContext.getBean(READMANGA_ME.beanName) as ParserBuilder
            URI.create(MANGA_FOX.url).host -> applicationContext.getBean(MANGA_FOX.beanName) as ParserBuilder
            URI.create(MANGA_READER.url).host -> applicationContext.getBean(MANGA_READER.beanName) as ParserBuilder
            else -> throw UnsupportedSiteException("We doesn't support site that you currently trying to get info")
        }
    }

    private fun downloadDoc(url: String): Document {
        return Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get()
    }
}