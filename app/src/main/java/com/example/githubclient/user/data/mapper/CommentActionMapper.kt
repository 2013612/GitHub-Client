package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.RemoteCommentAction
import com.example.githubclient.user.domain.model.event.CommentAction

fun RemoteCommentAction.toCommentAction(): CommentAction =
    when (this) {
        RemoteCommentAction.Created -> CommentAction.Created
        RemoteCommentAction.Edited -> CommentAction.Edited
        RemoteCommentAction.Deleted -> CommentAction.Deleted
    }
