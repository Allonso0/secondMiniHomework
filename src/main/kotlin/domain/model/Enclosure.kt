package org.example.domain.model

import kotlinx.serialization.Serializable
import org.example.serializer.UUIDSerializer
import java.util.*

@Serializable
data class Enclosure(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    val type: String,
    val size: Double,
    val maxCapacity: Int,
    val animals: MutableList<String> = mutableListOf()
) {
    fun addAnimal(animalId: UUID): Boolean {
        if (animals.size >= maxCapacity) {
            return false
        }

        animals.add(animalId.toString())
        return true
    }

    fun removeAnimal(animalId: UUID): Boolean {
        return animals.remove(animalId.toString())
    }

    fun clean(): String {
        return "Вольер $id был почищен!"
    }

    fun currentCount(): Int {
        return animals.size
    }

    fun animals(): List<UUID> {
        return animals.map { UUID.fromString(it) }
    }
}