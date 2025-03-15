package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class PullRequestReviewThreadEvent(
    override val id: String,
    override val isoDateTime: String,
    val isResolved: Boolean,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        UiText.StringResource(
            R.string.pull_request_review_thread_event_desc,
            arrayOf(
                getIsResolvedString(),
                repoName,
            ),
        )

    private fun getIsResolvedString(): UiText =
        if (isResolved) {
            UiText.StringResource(R.string.resolved)
        } else {
            UiText.StringResource(
                R.string.unresolved,
            )
        }
}
