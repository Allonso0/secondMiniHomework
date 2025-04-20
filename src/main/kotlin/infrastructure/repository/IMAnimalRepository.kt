package org.example.infrastructure.repository

import org.example.domain.model.Animal
import org.example.domain.repository.AnimalRepository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class IMAnimalRepository : AnimalRepository {
    private val animals = ConcurrentHashMap<UUID, Animal>()

    override fun save(animal: Animal): Animal {
        animals[animal.id] = animal
        return animal
    }

    override fun findById(id: UUID): Animal? {
        return animals[id]
    }

    override fun deleteById(id: UUID) {
        animals.remove(id)
    }

    override fun findAll(): List<Animal> {
        return animals.values.toList()
    }
}