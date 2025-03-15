package com.example.githubclient.user.domain.model.event

data class PullRequestReviewThreadEvent(
    override val id: String,
    override val isoDateTime: String,
    val isResolved: Boolean,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "${getIsResolvedString()} a pull request review thread in $repoName"

    private fun getIsResolvedString(): String = if (isResolved) "Resolved" else "Unresolved"
}
