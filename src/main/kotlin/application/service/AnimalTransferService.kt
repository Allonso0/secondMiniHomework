package org.example.application.service

import org.example.domain.event.AnimalMovedEvent
import org.example.domain.repository.AnimalRepository
import org.example.domain.repository.EnclosureRepository
import java.util.*

class AnimalTransferService (
    private val animalRepository: AnimalRepository,
    private val enclosureRepository: EnclosureRepository
) {
    fun transfer(animalID: UUID, enclosureID: UUID): AnimalMovedEvent {
        val animal = animalRepository.findById(animalID)
            ?: throw IllegalArgumentException("Животное с ID $animalID не найдено!")

        if (enclosureRepository.findById(enclosureID) == null) {
            throw IllegalArgumentException("Вольер для перемещения $enclosureID не найден!")
        }

        val fromEnclosure = animal.enclosureID
        val updatedAnimal = animal.copy(enclosureID = enclosureID)
        animalRepository.save(updatedAnimal)

        return AnimalMovedEvent(
            animalID = updatedAnimal.id,
            startEnclosureID = fromEnclosure,
            endEnclosureID = enclosureID
        )
    }
}