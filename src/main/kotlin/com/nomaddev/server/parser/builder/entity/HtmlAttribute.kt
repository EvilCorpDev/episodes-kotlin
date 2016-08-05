package com.nomaddev.server.parser.builder.entity

import org.apache.commons.lang3.StringUtils

class HtmlAttribute(val attrName: String, val attrVal: String) {

    fun isEmpty()  = StringUtils.isEmpty(attrName) || StringUtils.isEmpty(attrVal)
}