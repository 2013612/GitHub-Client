package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class CommitCommentEvent(
    override val id: String,
    override val isoDateTime: String,
    val commitId: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText = UiText.StringResource(R.string.commit_comment_event_desc, arrayOf(commitId, repoName))
}
