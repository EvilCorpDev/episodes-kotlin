package com.nomaddev.server.parser.builder.entity

import com.nomaddev.server.exception.EmptyHtmlAttributes
import org.apache.commons.lang3.StringUtils

class HtmlChain(val actionChain: MutableList<HtmlChainElement> = arrayListOf()) {

    fun appendAction(tag: String = "", attr: HtmlAttribute? = null,
                     parent: HtmlChainElement? = null, isEndAction: Boolean = false): HtmlChain {
        if(StringUtils.isEmpty(tag) && !checkAttributes(attr)) {
            return this
        }
        actionChain.add(HtmlChainElement(tag, attr, parent, isEndAction))
        return this
    }

    private fun checkAttributes(attr: HtmlAttribute?): Boolean {
        if(attr == null) {
            return false
        }
        if (attr.isEmpty()) throw EmptyHtmlAttributes("Some attributes is empty")
        return true
    }
}