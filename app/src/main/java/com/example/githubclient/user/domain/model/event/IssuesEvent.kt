package com.example.githubclient.user.domain.model.event

data class IssuesEvent(
    val id: String,
    val time: String,
    val action: IssueAction,
    val issueName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "${issueActionToString()} a issue $issueName in $repoName"

    private fun issueActionToString(): String =
        when (action) {
            IssueAction.Opened -> "Opened"
            IssueAction.Edited -> "Edited"
            IssueAction.Closed -> "Closed"
            IssueAction.Reopened -> "Reopened"
            IssueAction.Assigned -> "Assigned"
            IssueAction.Unassigned -> "Unassigned"
            IssueAction.Labeled -> "Labeled"
            IssueAction.Unlabeled -> "Unlabeled"
        }
}

enum class IssueAction {
    Opened,
    Edited,
    Closed,
    Reopened,
    Assigned,
    Unassigned,
    Labeled,
    Unlabeled,
}
