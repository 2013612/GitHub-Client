package com.example.githubclient.common.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    data class DynamicString(
        val value: String,
    ) : UiText

    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf(),
    ) : UiText

    @Composable
    fun asString(): String =
        when (this) {
            is DynamicString -> value
            is StringResource -> {
                val resolvedArgs =
                    args
                        .map {
                            if (it is UiText) {
                                it.asString()
                            } else {
                                it
                            }
                        }.toTypedArray()
                stringResource(id = id, *resolvedArgs)
            }
        }

    fun asString(context: Context): String =
        when (this) {
            is DynamicString -> value
            is StringResource -> {
                val resolvedArgs =
                    args
                        .map {
                            if (it is UiText) {
                                it.asString(context)
                            } else {
                                it
                            }
                        }.toTypedArray()
                context.getString(id, *resolvedArgs)
            }
        }
}
