package com.example.githubclient.user.domain.model.event

data class PushEvent(
    val id: String,
    val time: String,
    val commitCount: Int,
    val ref: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "Pushed $commitCount commits to $ref in $repoName"
}
