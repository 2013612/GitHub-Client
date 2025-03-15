package com.example.githubclient.user.domain.model.event

data class CommitCommentEvent(
    override val id: String,
    override val isoDateTime: String,
    val commitId: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "comment on commit $commitId in $repoName"
}
