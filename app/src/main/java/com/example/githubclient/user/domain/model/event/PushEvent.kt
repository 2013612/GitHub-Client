package com.example.githubclient.user.domain.model.event

data class PushEvent(
    override val id: String,
    override val isoDateTime: String,
    val commitCount: Int,
    val ref: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Pushed $commitCount commits to $ref in $repoName"
}
