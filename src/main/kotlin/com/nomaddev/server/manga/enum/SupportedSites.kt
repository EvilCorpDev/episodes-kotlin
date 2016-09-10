package com.nomaddev.server.manga.enum

enum class SupportedSites(val url: String, val beanName: String) {

    READMANGA_TODAY("http://www.readmanga.today", "readMangaTodayParser"),
    READMANGA_ME("http://readmanga.me", "readMangaMeParser"),
    MANGA_FOX("http://mangafox.me", "mangaFoxParser"),
    MANGA_READER("http://www.mangareader.net", "mangaReader")
}