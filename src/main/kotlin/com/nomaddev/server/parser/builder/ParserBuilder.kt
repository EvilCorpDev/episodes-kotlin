package com.nomaddev.server.parser.builder

import com.nomaddev.server.episode.enum.*
import com.nomaddev.server.parser.builder.entity.HtmlChain

class ParserBuilder(val site: SupportedSites, val title: HtmlChain, val img: HtmlChain, val episode: HtmlChain)