package com.nomaddev.server.repository

import com.nomaddev.server.manga.entity.Manga
import org.springframework.data.mongodb.repository.MongoRepository

interface  MangaRepository: MongoRepository<Manga, String> {
}