package org.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ZooStats(
    val totalAnimals: Int,
    val totalEnclosures: Int,
    val totalSchedules: Int,
    val animalsPerEnclosure: Map<String, Int>
)