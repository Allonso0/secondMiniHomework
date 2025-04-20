package org.example.domain.event

import java.time.LocalDateTime
import java.util.UUID

data class AnimalMovedEvent(
    val animalID: UUID,
    val startEnclosureID: UUID?,
    val endEnclosureID: UUID,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)