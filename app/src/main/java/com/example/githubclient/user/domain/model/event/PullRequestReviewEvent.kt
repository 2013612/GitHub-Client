package com.example.githubclient.user.domain.model.event

data class PullRequestReviewEvent(
    val id: String,
    val time: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "Reviewed a pull request in $repoName"
}
