package com.example.githubclient.user.domain.model.event

data class GollumEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Updated the wiki pages of $repoName"
}
