package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.RemoteCommitCommentEventPayload
import com.example.githubclient.user.data.model.event.RemoteCreateEventPayload
import com.example.githubclient.user.data.model.event.RemoteDeleteEventPayload
import com.example.githubclient.user.data.model.event.RemoteForkEventPayload
import com.example.githubclient.user.data.model.event.RemoteGollumEventPayload
import com.example.githubclient.user.data.model.event.RemoteIssueCommentEventPayload
import com.example.githubclient.user.data.model.event.RemoteIssuesEventPayload
import com.example.githubclient.user.data.model.event.RemoteMemberEventPayload
import com.example.githubclient.user.data.model.event.RemotePublicEventPayload
import com.example.githubclient.user.data.model.event.RemotePublicUserEvent
import com.example.githubclient.user.data.model.event.RemotePullRequestEventPayload
import com.example.githubclient.user.data.model.event.RemotePullRequestReviewCommentEventPayload
import com.example.githubclient.user.data.model.event.RemotePullRequestReviewEventPayload
import com.example.githubclient.user.data.model.event.RemotePullRequestReviewThreadAction
import com.example.githubclient.user.data.model.event.RemotePullRequestReviewThreadEventPayload
import com.example.githubclient.user.data.model.event.RemotePushEventPayload
import com.example.githubclient.user.domain.model.event.CommitCommentEvent
import com.example.githubclient.user.domain.model.event.CreateEvent
import com.example.githubclient.user.domain.model.event.DeleteEvent
import com.example.githubclient.user.domain.model.event.ForkEvent
import com.example.githubclient.user.domain.model.event.GollumEvent
import com.example.githubclient.user.domain.model.event.IssueCommentEvent
import com.example.githubclient.user.domain.model.event.IssuesEvent
import com.example.githubclient.user.domain.model.event.MemberEvent
import com.example.githubclient.user.domain.model.event.PublicEvent
import com.example.githubclient.user.domain.model.event.PullRequestEvent
import com.example.githubclient.user.domain.model.event.PullRequestReviewCommentEvent
import com.example.githubclient.user.domain.model.event.PullRequestReviewEvent
import com.example.githubclient.user.domain.model.event.PullRequestReviewThreadEvent
import com.example.githubclient.user.domain.model.event.PushEvent
import com.example.githubclient.user.domain.model.event.UserEvent

fun RemotePublicUserEvent.toUserEvent(): UserEvent? =
    when (payload) {
        is RemoteCommitCommentEventPayload ->
            CommitCommentEvent(
                id = id,
                time = createdAt,
                commitId = payload.comment.commitId,
                repoName = repo.name,
            )
        is RemoteCreateEventPayload ->
            CreateEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
                ref = payload.ref ?: "",
                refType = payload.refType.toGitRefType(),
            )
        is RemoteDeleteEventPayload ->
            DeleteEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
                ref = payload.ref,
                refType = payload.refType.toGitRefType(),
            )
        is RemoteForkEventPayload ->
            ForkEvent(
                id = id,
                time = createdAt,
                repoName = payload.forkee.name,
            )
        is RemoteGollumEventPayload ->
            GollumEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )
        is RemoteIssueCommentEventPayload ->
            IssueCommentEvent(
                id = id,
                time = createdAt,
                action = payload.action.toCommentAction(),
                issueName = payload.issue.title,
                repoName = repo.name,
            )
        is RemoteIssuesEventPayload ->
            IssuesEvent(
                id = id,
                time = createdAt,
                action = payload.action.toIssueAction(),
                issueName = payload.issue.title,
                repoName = repo.name,
            )
        is RemoteMemberEventPayload ->
            MemberEvent(
                id = id,
                time = createdAt,
                memberName = payload.member.login,
                repoName = repo.name,
            )
        RemotePublicEventPayload ->
            PublicEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )
        is RemotePullRequestEventPayload ->
            PullRequestEvent(
                id = id,
                time = createdAt,
                action = payload.action.toPullRequestAction(),
                repoName = repo.name,
            )

        RemotePullRequestReviewEventPayload ->
            PullRequestReviewEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )

        RemotePullRequestReviewCommentEventPayload ->
            PullRequestReviewCommentEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )

        is RemotePullRequestReviewThreadEventPayload ->
            PullRequestReviewThreadEvent(
                id = id,
                time = createdAt,
                isResolved = payload.action == RemotePullRequestReviewThreadAction.Resolved,
                repoName = repo.name,
            )

        is RemotePushEventPayload ->
            PushEvent(
                id = id,
                time = createdAt,
                commitCount = payload.size,
                ref = payload.ref,
                repoName = repo.name,
            )
    }
