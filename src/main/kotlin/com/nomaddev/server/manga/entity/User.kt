package com.nomaddev.server.manga.entity

class User(val username: String, val email: String, val pass: String, val mangaList: List<String>, val token: String)