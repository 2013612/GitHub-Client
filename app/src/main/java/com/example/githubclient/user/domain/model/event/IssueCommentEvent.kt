package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class IssueCommentEvent(
    override val id: String,
    override val isoDateTime: String,
    val action: CommentAction,
    val issueName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        UiText.StringResource(
            R.string.issue_comment_event_desc,
            arrayOf(
                commentActionToUiText(),
                issueName,
                repoName,
            ),
        )

    private fun commentActionToUiText(): UiText =
        when (action) {
            CommentAction.Created -> UiText.StringResource(R.string.created)
            CommentAction.Edited -> UiText.StringResource(R.string.edited)
            CommentAction.Deleted -> UiText.StringResource(R.string.deleted)
        }
}
