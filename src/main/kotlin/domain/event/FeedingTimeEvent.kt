package org.example.domain.event

import java.time.LocalDateTime
import java.util.*

data class FeedingTimeEvent(
    val scheduleID: UUID,
    val animalID: UUID,
    val foodGiven: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)