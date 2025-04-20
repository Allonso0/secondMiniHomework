package org.example.domain.repository

import org.example.domain.model.Animal
import java.util.*

interface AnimalRepository {
    fun save(animal: Animal): Animal

    fun findById(id: UUID): Animal?

    fun deleteById(id: UUID)

    fun findAll(): List<Animal>
}