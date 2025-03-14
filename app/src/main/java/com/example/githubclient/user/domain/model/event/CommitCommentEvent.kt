package com.example.githubclient.user.domain.model.event

data class CommitCommentEvent(
    val id: String,
    val time: String,
    val commitId: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "comment on commit $commitId in $repoName"
}
