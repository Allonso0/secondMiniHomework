package org.example.domain.model

import kotlinx.serialization.Serializable
import org.example.domain.valueobject.FoodType
import org.example.serializer.LocalTimeSerializer
import org.example.serializer.UUIDSerializer
import java.time.LocalTime
import java.util.*

@Serializable
data class FeedingSchedule(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    @Serializable(with = UUIDSerializer::class)
    val animalId: UUID,
    @Serializable(with = LocalTimeSerializer::class)
    var feedingTime: LocalTime,
    var foodType: FoodType,
    var isCompleted: Boolean = false
) {
    fun changeSchedule(newTime: LocalTime, newFoodType: FoodType) {
        feedingTime = newTime
        foodType = newFoodType
    }

    fun cancelExecution() {
        isCompleted = true
    }
}