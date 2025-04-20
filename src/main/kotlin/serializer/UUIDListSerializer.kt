package org.example.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

object UUIDListSerializer : KSerializer<List<UUID>> {
    private val serializer = ListSerializer(UUIDSerializer)
    override val descriptor: SerialDescriptor = serializer.descriptor
    override fun serialize(encoder: Encoder, value: List<UUID>) = serializer.serialize(encoder, value)
    override fun deserialize(decoder: Decoder): List<UUID> = serializer.deserialize(decoder)
}