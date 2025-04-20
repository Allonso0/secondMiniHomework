package org.example.infrastructure.repository

import org.example.domain.model.FeedingSchedule
import org.example.domain.repository.FeedingScheduleRepository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class IMFeedingScheduleRepository : FeedingScheduleRepository {
    private val schedules = ConcurrentHashMap<UUID, FeedingSchedule>()

    override fun save(schedule: FeedingSchedule): FeedingSchedule {
        schedules[schedule.id] = schedule
        return schedule
    }

    override fun findById(id: UUID): FeedingSchedule? {
        return schedules[id]
    }

    override fun deleteById(id: UUID) {
        schedules.remove(id)
    }

    override fun findAll(): List<FeedingSchedule> {
        return schedules.values.toList()
    }

    override fun findByAnimalId(animalId: UUID): List<FeedingSchedule> {
        return schedules.values.filter { it.animalId == animalId }
    }
}