package com.example.githubclient.user.domain.model.event

data class GollumEvent(
    val id: String,
    val time: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "Updated the wiki pages of $repoName"
}
