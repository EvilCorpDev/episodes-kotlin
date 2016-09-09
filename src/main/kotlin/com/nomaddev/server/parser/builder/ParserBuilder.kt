package com.nomaddev.server.parser.builder

import com.nomaddev.server.episode.enum.SupportedSites
import com.nomaddev.server.parser.builder.entity.SelectorChain

class ParserBuilder(val site: SupportedSites, val title: SelectorChain, val img: SelectorChain,
                    val episode: SelectorChain)