package com.example.githubclient.user.domain.model.event

data class CreateEvent(
    val id: String,
    val time: String,
    val repoName: String,
    val ref: String,
    val refType: GitRefType,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String =
        when (refType) {
            GitRefType.Branch -> "create branch $ref in $repoName"
            GitRefType.Tag -> "create tag $ref in $repoName"
            GitRefType.Repository -> "create repository $repoName"
        }
}
