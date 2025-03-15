package com.example.githubclient.common.presentation.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    fun format(
        isoDateTime: String,
        formatter: DateTimeFormatter,
    ): String =
        Instant
            .parse(isoDateTime)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .toJavaLocalDateTime()
            .format(formatter)
}
