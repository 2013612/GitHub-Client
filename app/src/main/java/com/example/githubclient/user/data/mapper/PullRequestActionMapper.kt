package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.payload.RemotePullRequestAction
import com.example.githubclient.user.domain.model.event.PullRequestAction

fun RemotePullRequestAction.toPullRequestAction() =
    when (this) {
        RemotePullRequestAction.Assigned -> PullRequestAction.Assigned
        RemotePullRequestAction.Unassigned -> PullRequestAction.Unassigned
        RemotePullRequestAction.ReviewRequested -> PullRequestAction.ReviewRequested
        RemotePullRequestAction.ReviewRequestRemoved -> PullRequestAction.ReviewRequestRemoved
        RemotePullRequestAction.Labeled -> PullRequestAction.Labeled
        RemotePullRequestAction.Unlabeled -> PullRequestAction.Unlabeled
        RemotePullRequestAction.Synchronize -> PullRequestAction.Synchronize
        RemotePullRequestAction.Opened -> PullRequestAction.Opened
        RemotePullRequestAction.Edited -> PullRequestAction.Edited
        RemotePullRequestAction.Closed -> PullRequestAction.Closed
        RemotePullRequestAction.Reopened -> PullRequestAction.Reopened
    }
