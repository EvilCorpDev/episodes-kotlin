package com.nomaddev.server.scheduler

import com.nomaddev.server.repository.MangaOperations
import com.nomaddev.server.services.EpisodesService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class MangaUpdateScheduler @Autowired constructor(val mangaOps: MangaOperations,
                                                  val episodesService: EpisodesService) {
    val LOGGER = LoggerFactory.getLogger(MangaUpdateScheduler::class.java)

    @Value("\${cron.updatemanga.expression}") lateinit var expression: String

    @PostConstruct
    fun startJob() {
        schedule(expression)
    }

    fun schedule(expression: String) {
        LOGGER.debug("tick")
        val updateMangaJob = UpdateMangaJob(mangaOps, episodesService)
        val scheduler = ConcurrentTaskScheduler()
        scheduler.schedule(updateMangaJob, CronTrigger(expression))
    }
}