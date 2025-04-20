package org.example.domain.repository

import org.example.domain.model.Enclosure
import java.util.*

interface EnclosureRepository {
    fun save(enclosure: Enclosure): Enclosure

    fun findById(id: UUID): Enclosure?

    fun deleteById(id: UUID)

    fun findAll(): List<Enclosure>
}