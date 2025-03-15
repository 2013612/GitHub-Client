package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.payload.RemoteIssueAction
import com.example.githubclient.user.domain.model.event.IssueAction

fun RemoteIssueAction.toIssueAction(): IssueAction =
    when (this) {
        RemoteIssueAction.Opened -> IssueAction.Opened
        RemoteIssueAction.Edited -> IssueAction.Edited
        RemoteIssueAction.Closed -> IssueAction.Closed
        RemoteIssueAction.Reopened -> IssueAction.Reopened
        RemoteIssueAction.Assigned -> IssueAction.Assigned
        RemoteIssueAction.Unassigned -> IssueAction.Unassigned
        RemoteIssueAction.Labeled -> IssueAction.Labeled
        RemoteIssueAction.Unlabeled -> IssueAction.Unlabeled
    }
