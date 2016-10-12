package com.nomaddev.server.scheduler

import com.nomaddev.server.repository.MangaOperations
import com.nomaddev.server.services.EpisodesService

class UpdateMangaJob(val mangaOps: MangaOperations, val episodesService: EpisodesService): Runnable {

    override fun run() {
        val allManga = mangaOps.findAllManga()
        allManga.forEach { it -> episodesService.updateMangaEpisode(it)}
    }

}