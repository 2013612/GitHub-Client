package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class PullRequestReviewCommentEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText = UiText.StringResource(R.string.pull_request_review_comment_event_desc, arrayOf(repoName))
}
