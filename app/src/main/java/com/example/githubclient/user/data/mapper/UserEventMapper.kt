package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.RemotePublicUserEvent
import com.example.githubclient.user.data.model.event.payload.RemoteCommitCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteCreateEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteDeleteEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteForkEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteGollumEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteIssueCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteIssuesEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteMemberEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePublicEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewCommentEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewThreadAction
import com.example.githubclient.user.data.model.event.payload.RemotePullRequestReviewThreadEventPayload
import com.example.githubclient.user.data.model.event.payload.RemotePushEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteReleaseEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteSponsorshipEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteUnknownEventPayload
import com.example.githubclient.user.data.model.event.payload.RemoteWatchEventPayload
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
import com.example.githubclient.user.domain.model.event.ReleaseEvent
import com.example.githubclient.user.domain.model.event.SponsorshipEvent
import com.example.githubclient.user.domain.model.event.UserEvent
import com.example.githubclient.user.domain.model.event.WatchEvent

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

        RemoteReleaseEventPayload ->
            ReleaseEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )

        RemoteSponsorshipEventPayload ->
            SponsorshipEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )

        RemoteWatchEventPayload ->
            WatchEvent(
                id = id,
                time = createdAt,
                repoName = repo.name,
            )

        RemoteUnknownEventPayload -> null
    }
