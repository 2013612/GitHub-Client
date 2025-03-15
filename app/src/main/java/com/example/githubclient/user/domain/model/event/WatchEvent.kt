package com.example.githubclient.user.domain.model.event

data class WatchEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Stared $repoName"
}
