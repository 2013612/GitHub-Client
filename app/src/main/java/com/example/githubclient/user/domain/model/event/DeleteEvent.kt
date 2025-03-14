package com.example.githubclient.user.domain.model.event

data class DeleteEvent(
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
            GitRefType.Branch -> "delete branch $ref in $repoName"
            GitRefType.Tag -> "delete tag $ref in $repoName"
            GitRefType.Repository -> ""
        }
}
