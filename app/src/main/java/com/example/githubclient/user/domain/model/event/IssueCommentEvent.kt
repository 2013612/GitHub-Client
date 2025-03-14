package com.example.githubclient.user.domain.model.event

data class IssueCommentEvent(
    val id: String,
    val time: String,
    val action: CommentAction,
    val issueName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "${commentActionToString()} a comment on $issueName in $repoName"

    private fun commentActionToString(): String =
        when (action) {
            CommentAction.Created -> "Created"
            CommentAction.Edited -> "Edited"
            CommentAction.Deleted -> "Deleted"
        }
}
