package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class DeleteEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
    val ref: String,
    val refType: GitRefType,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        when (refType) {
            GitRefType.Branch -> UiText.StringResource(R.string.delete_branch_event_desc, arrayOf(ref, repoName))
            GitRefType.Tag -> UiText.StringResource(R.string.delete_tag_event_desc, arrayOf(ref, repoName))
            GitRefType.Repository -> UiText.DynamicString("")
        }
}
