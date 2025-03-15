package com.example.githubclient.user.domain.model.event

import com.example.githubclient.common.presentation.utils.DateTimeUtil
import com.example.githubclient.common.presentation.utils.UiText
import java.time.format.DateTimeFormatter

sealed class UserEvent {
    abstract val id: String

    abstract val isoDateTime: String

    abstract fun getEventDesc(): UiText

    fun getEventDate(): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return DateTimeUtil.format(isoDateTime, dateFormat)
    }

    fun getEventTime(): String {
        val timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss")

        return DateTimeUtil.format(isoDateTime, timeFormat)
    }
}

enum class GitRefType {
    Branch,
    Tag,
    Repository,
}
