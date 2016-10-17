package com.nomaddev.server.manga.entity

class EpisodesUser(val username: String = "", val email: String = "", val passHash: String = "",
                   val mangaList: List<String> = arrayListOf())