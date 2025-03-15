package com.example.githubclient.user.domain.model.event

data class PullRequestEvent(
    override val id: String,
    override val isoDateTime: String,
    val action: PullRequestAction,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "${pullRequestActionToString()} a pull request in $repoName"

    private fun pullRequestActionToString(): String =
        when (action) {
            PullRequestAction.Assigned -> "Assigned"
            PullRequestAction.Unassigned -> "Unassigned"
            PullRequestAction.ReviewRequested -> "Review requested"
            PullRequestAction.ReviewRequestRemoved -> "Review request removed"
            PullRequestAction.Labeled -> "Labeled"
            PullRequestAction.Unlabeled -> "Unlabeled"
            PullRequestAction.Opened -> "Opened"
            PullRequestAction.Edited -> "Edited"
            PullRequestAction.Closed -> "Closed"
            PullRequestAction.Reopened -> "Reopened"
            PullRequestAction.Synchronize -> "Synchronize"
        }
}

enum class PullRequestAction {
    Assigned,
    Unassigned,
    ReviewRequested,
    ReviewRequestRemoved,
    Labeled,
    Unlabeled,
    Opened,
    Edited,
    Closed,
    Reopened,
    Synchronize,
}
