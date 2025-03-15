package com.example.githubclient.user.domain.model.event

data class PullRequestReviewEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "Reviewed a pull request in $repoName"
}
