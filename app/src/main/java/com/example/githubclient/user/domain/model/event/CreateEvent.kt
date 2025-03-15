package com.example.githubclient.user.domain.model.event

import com.example.githubclient.R
import com.example.githubclient.common.presentation.utils.UiText

data class CreateEvent(
    override val id: String,
    override val isoDateTime: String,
    val repoName: String,
    val ref: String,
    val refType: GitRefType,
) : UserEvent() {
    override fun getEventDesc(): UiText =
        when (refType) {
            GitRefType.Branch -> UiText.StringResource(R.string.create_branch_event_desc, arrayOf(ref, repoName))
            GitRefType.Tag -> UiText.StringResource(R.string.create_tag_event_desc, arrayOf(ref, repoName))
            GitRefType.Repository ->
                UiText.StringResource(
                    R.string.create_repository_event_desc,
                    arrayOf(repoName),
                )
        }
}
