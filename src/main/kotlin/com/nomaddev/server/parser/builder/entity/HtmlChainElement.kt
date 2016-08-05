package com.nomaddev.server.parser.builder.entity

data class HtmlChainElement(val tag: String = "", val attr: HtmlAttribute? = null,
                            val parent: HtmlChainElement? = null, val isEndElement: Boolean = false)