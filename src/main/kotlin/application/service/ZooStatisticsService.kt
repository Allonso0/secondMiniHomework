package org.example.application.service

import org.example.domain.model.ZooStats
import org.example.domain.repository.AnimalRepository
import org.example.domain.repository.EnclosureRepository
import org.example.domain.repository.FeedingScheduleRepository

class ZooStatisticsService(
    private val animalRepository: AnimalRepository,
    private val enclosureRepository: EnclosureRepository,
    private val feedingScheduleRepository: FeedingScheduleRepository
) {
    fun totalAnimals(): Int {
        return animalRepository.findAll().size
    }

    fun totalEnclosures(): Int {
        return enclosureRepository.findAll().size
    }

    fun totalFeedingSchedules(): Int {
        return feedingScheduleRepository.findAll().size
    }

    fun animalsPerEnclosure(): Map<String, Int> {
        return animalRepository.findAll()
            .groupingBy { it.enclosureID?.toString() ?: "Без вольера" }
            .eachCount()
    }

    fun getStats() : ZooStats {
        return ZooStats(
            totalAnimals = totalAnimals(),
            totalEnclosures = totalEnclosures(),
            totalSchedules = totalFeedingSchedules(),
            animalsPerEnclosure = animalsPerEnclosure()
        )
    }
}