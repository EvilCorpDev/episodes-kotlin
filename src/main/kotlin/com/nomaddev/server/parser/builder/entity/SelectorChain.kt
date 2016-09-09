package com.nomaddev.server.parser.builder.entity

class SelectorChain (val attr: ExecuteAction) {

    private val selectors: MutableList<Selector> = arrayListOf()

    fun append(selector: String): SelectorChain {
        selectors.add(Selector(selector))
        return this
    }

    fun constructSelector(): String {
        var selectorsStr: String = ""
        for(selector: Selector in selectors) {
            selectorsStr += selector.value + " "
        }
        return selectorsStr
    }
}