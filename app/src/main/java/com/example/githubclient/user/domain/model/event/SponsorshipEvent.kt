package com.example.githubclient.user.domain.model.event

data class SponsorshipEvent(
    val id: String,
    val time: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "Created a sponsorship in $repoName"
}
