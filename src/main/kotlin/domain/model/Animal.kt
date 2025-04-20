package org.example.domain.model

import kotlinx.serialization.Serializable
import org.example.domain.valueobject.AnimalStatus
import org.example.domain.valueobject.FoodType
import org.example.domain.valueobject.Gender
import org.example.serializer.LocalDateSerializer
import org.example.serializer.UUIDSerializer
import java.time.LocalDate
import java.util.*

@Serializable
data class Animal(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    var species: String,
    var name: String,
    @Serializable(with = LocalDateSerializer::class)
    var birthDate: LocalDate,
    var gender: Gender,
    var favouriteFoodType: FoodType,
    var status: AnimalStatus = AnimalStatus.HEALTHY,
    @Serializable(with = UUIDSerializer::class)
    var enclosureID: UUID? = null
) {
    fun feed(): String {
        return "$name был покормлен. Он(-а) получил(-а) $favouriteFoodType :)"
    }

    fun heal() {
        status = AnimalStatus.HEALTHY
    }

    fun moveTo(enclosureID: UUID) {
        this.enclosureID = enclosureID
    }
}