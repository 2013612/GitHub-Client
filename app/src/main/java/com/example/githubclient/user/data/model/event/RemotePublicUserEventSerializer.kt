package com.example.githubclient.user.data.model.event

import com.example.githubclient.user.data.model.event.payload.RemoteCommitCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteCreateEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteDeleteEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteForkEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteGollumEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteIssueCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteIssuesEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteMemberEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePublicEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewThreadEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePushEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteReleaseEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteSponsorshipEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteUnknownEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteUserEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteWatchEventPayload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

object RemotePublicUserEventSerializer : KSerializer<RemotePublicUserEvent> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("RemotePublicUserEvent") {
            element<String>("id")
            element<RemoteUserEventType>("type")
            element<RemoteActor>("actor")
            element<RemoteRepository>("repo")
            element<RemoteUserEventPayload>("payload")
            element<Boolean>("public")
            element<String>("created_at")
        }

    override fun deserialize(decoder: Decoder): RemotePublicUserEvent {
        try {
            require(decoder is JsonDecoder)
            val json = decoder.json
            val jsonObject = decoder.decodeJsonElement() as JsonObject

            val id = jsonObject["id"]!!.jsonPrimitive.content
            val type = json.decodeFromJsonElement(RemoteUserEventType.serializer(), jsonObject["type"]!!)
            val repo = json.decodeFromJsonElement(RemoteRepository.serializer(), jsonObject["repo"]!!)
            val payloadElement = jsonObject["payload"]!!
            val createdAt = jsonObject["created_at"]!!.jsonPrimitive.content

            val payload =
                when (type) {
                    RemoteUserEventType.PushEvent ->
                        json.decodeFromJsonElement(
                            RemotePushEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.CreateEvent ->
                        json.decodeFromJsonElement(
                            RemoteCreateEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.IssuesEvent ->
                        json.decodeFromJsonElement(
                            RemoteIssuesEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.PullRequestEvent ->
                        json.decodeFromJsonElement(
                            RemotePullRequestEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.WatchEvent ->
                        json.decodeFromJsonElement(
                            RemoteWatchEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.ForkEvent ->
                        json.decodeFromJsonElement(
                            RemoteForkEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.DeleteEvent ->
                        json.decodeFromJsonElement(
                            RemoteDeleteEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.PublicEvent ->
                        json.decodeFromJsonElement(
                            RemotePublicEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.IssueCommentEvent ->
                        json.decodeFromJsonElement(
                            RemoteIssueCommentEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.ReleaseEvent ->
                        json.decodeFromJsonElement(
                            RemoteReleaseEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.MemberEvent ->
                        json.decodeFromJsonElement(
                            RemoteMemberEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.GollumEvent ->
                        json.decodeFromJsonElement(
                            RemoteGollumEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.PullRequestReviewEvent ->
                        json.decodeFromJsonElement(
                            RemotePullRequestReviewEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.PullRequestReviewCommentEvent ->
                        json.decodeFromJsonElement(
                            RemotePullRequestReviewCommentEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.PullRequestReviewThreadEvent ->
                        json.decodeFromJsonElement(
                            RemotePullRequestReviewThreadEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.CommitCommentEvent ->
                        json.decodeFromJsonElement(
                            RemoteCommitCommentEventPayload.serializer(),
                            payloadElement,
                        )
                    RemoteUserEventType.SponsorshipEvent ->
                        json.decodeFromJsonElement(
                            RemoteSponsorshipEventPayload.serializer(),
                            payloadElement,
                        )

                    RemoteUserEventType.Unknown -> RemoteUnknownEventPayload
                }

            return RemotePublicUserEvent(
                id = id,
                type = type,
                repo = repo,
                payload = payload,
                createdAt = createdAt,
            )
        } catch (e: Exception) {
            println("Error deserializing RemotePublicUserEvent: ${e.message}")
            return RemotePublicUserEvent(
                id = "",
                type = RemoteUserEventType.Unknown,
                repo = RemoteRepository(0, "", ""),
                payload = RemoteUnknownEventPayload,
                createdAt = "",
            )
        }
    }

    override fun serialize(
        encoder: Encoder,
        value: RemotePublicUserEvent,
    ) {
        // implement when necessary
        throw UnsupportedOperationException("Serialization is not supported for RemotePublicUserEvent")
    }
}
