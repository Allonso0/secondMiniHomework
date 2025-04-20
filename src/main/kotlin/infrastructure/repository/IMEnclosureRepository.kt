package org.example.infrastructure.repository

import org.example.domain.model.Enclosure
import org.example.domain.repository.EnclosureRepository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class IMEnclosureRepository : EnclosureRepository {
    private val enclosures = ConcurrentHashMap<UUID, Enclosure>()

    override fun save(enclosure: Enclosure): Enclosure {
        enclosures[enclosure.id] = enclosure
        return enclosure
    }

    override fun findById(id: UUID): Enclosure? {
        return enclosures[id]
    }

    override fun deleteById(id: UUID) {
        enclosures.remove(id)
    }

    override fun findAll(): List<Enclosure> {
        return enclosures.values.toList()
    }
}