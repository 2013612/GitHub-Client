package com.example.githubclient.user.domain.model.event

data class DeleteEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
    val ref: String,
    val refType: GitRefType,
) : UserEvent() {
    override fun getEventDesc(): String =
        when (refType) {
            GitRefType.Branch -> "delete branch $ref in $repoName"
            GitRefType.Tag -> "delete tag $ref in $repoName"
            GitRefType.Repository -> ""
        }
}
