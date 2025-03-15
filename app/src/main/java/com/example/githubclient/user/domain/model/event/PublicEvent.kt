package com.example.githubclient.user.domain.model.event

data class PublicEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Set $repoName to public"
}
