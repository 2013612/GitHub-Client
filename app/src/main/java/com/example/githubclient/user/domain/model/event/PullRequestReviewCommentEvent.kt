package com.example.githubclient.user.domain.model.event

data class PullRequestReviewCommentEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Added a comment to a pull request in $repoName"
}
