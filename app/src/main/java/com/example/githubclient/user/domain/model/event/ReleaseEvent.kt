package com.example.githubclient.user.domain.model.event

data class ReleaseEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Published a release in $repoName"
}
