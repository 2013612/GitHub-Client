package com.example.githubclient.user.domain.model.event

data class PullRequestReviewThreadEvent(
    val id: String,
    val time: String,
    val isResolved: Boolean,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "${getIsResolvedString()} a pull request review thread in $repoName"

    private fun getIsResolvedString(): String = if (isResolved) "Resolved" else "Unresolved"
}
