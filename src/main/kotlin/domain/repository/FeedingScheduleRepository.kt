package org.example.domain.repository

import org.example.domain.model.FeedingSchedule
import java.util.*

interface FeedingScheduleRepository {
    fun save(schedule: FeedingSchedule): FeedingSchedule

    fun findById(id: UUID): FeedingSchedule?

    fun findByAnimalId(animalId: UUID): List<FeedingSchedule>

    fun deleteById(id: UUID)

    fun findAll(): List<FeedingSchedule>
}