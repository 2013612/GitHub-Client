package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class PullRequestEvent(
    override val id: String,
    override val isoDateTime: String,
    val action: PullRequestAction,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        UiText.StringResource(R.string.pull_request_event_desc, arrayOf(pullRequestActionToString(), repoName))

    private fun pullRequestActionToString(): UiText =
        when (action) {
            PullRequestAction.Assigned -> UiText.StringResource(R.string.assigned)
            PullRequestAction.Unassigned -> UiText.StringResource(R.string.unassigned)
            PullRequestAction.ReviewRequested -> UiText.StringResource(R.string.requested_review)
            PullRequestAction.ReviewRequestRemoved -> UiText.StringResource(R.string.removed_review_request)
            PullRequestAction.Labeled -> UiText.StringResource(R.string.labeled)
            PullRequestAction.Unlabeled -> UiText.StringResource(R.string.unlabeled)
            PullRequestAction.Opened -> UiText.StringResource(R.string.opened)
            PullRequestAction.Edited -> UiText.StringResource(R.string.edited)
            PullRequestAction.Closed -> UiText.StringResource(R.string.closed)
            PullRequestAction.Reopened -> UiText.StringResource(R.string.reopened)
            PullRequestAction.Synchronize -> UiText.StringResource(R.string.synchronize)
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
