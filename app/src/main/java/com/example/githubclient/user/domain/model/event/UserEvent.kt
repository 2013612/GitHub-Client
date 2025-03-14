package com.example.githubclient.user.domain.model.event

sealed class UserEvent {
    abstract fun getEventId(): String

    abstract fun getEventTime(): String

    abstract fun getEventDesc(): String
}

enum class GitRefType {
    Branch,
    Tag,
    Repository,
}
