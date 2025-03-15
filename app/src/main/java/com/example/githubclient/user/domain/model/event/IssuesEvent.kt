package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class IssuesEvent(
    override val id: String,
    override val isoDateTime: String,
    val action: IssueAction,
    val issueName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        UiText.StringResource(R.string.issues_event_desc, arrayOf(issueActionToUiText(), issueName, repoName))

    private fun issueActionToUiText(): UiText =
        when (action) {
            IssueAction.Opened -> UiText.StringResource(R.string.opened)
            IssueAction.Edited -> UiText.StringResource(R.string.edited)
            IssueAction.Closed -> UiText.StringResource(R.string.closed)
            IssueAction.Reopened -> UiText.StringResource(R.string.reopened)
            IssueAction.Assigned -> UiText.StringResource(R.string.assigned)
            IssueAction.Unassigned -> UiText.StringResource(R.string.unassigned)
            IssueAction.Labeled -> UiText.StringResource(R.string.labeled)
            IssueAction.Unlabeled -> UiText.StringResource(R.string.unlabeled)
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
