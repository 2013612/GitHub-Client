package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class PushEvent(
    override val id: String,
    override val isoDateTime: String,
    val commitCount: Int,
    val ref: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): UiText = UiText.StringResource(R.string.push_event_desc, arrayOf(commitCount, ref, repoName))
}
