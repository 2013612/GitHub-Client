package com.example.githubclient.user.domain.model.event

data class CreateEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
    val ref: String,
    val refType: GitRefType,
) : UserEvent() {
    override fun getEventDesc(): String =
        when (refType) {
            GitRefType.Branch -> "create branch $ref in $repoName"
            GitRefType.Tag -> "create tag $ref in $repoName"
            GitRefType.Repository -> "create repository $repoName"
        }
}
