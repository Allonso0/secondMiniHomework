package org.example.application.service

import org.example.domain.event.FeedingTimeEvent
import org.example.domain.repository.AnimalRepository
import org.example.domain.repository.FeedingScheduleRepository
import java.time.LocalDateTime
import java.util.*

class FeedingOrganizationService (
    private val animalRepository: AnimalRepository,
    private val feedingScheduleRepository: FeedingScheduleRepository
) {
    fun feedAnimal(animalID: UUID): FeedingTimeEvent {
        val animal = animalRepository.findById(animalID)
            ?: throw IllegalArgumentException("Животное с ID $animalID не найдено!")

        val schedule = feedingScheduleRepository.findByAnimalId(animal.id)
            .firstOrNull() ?: throw IllegalStateException("Для данного животного не было найдено расписаний кормления!")

        return FeedingTimeEvent(
            scheduleID = schedule.id,
            animalID = animal.id,
            foodGiven = schedule.foodType.toString(),
            timestamp = LocalDateTime.now()
        )
    }
}