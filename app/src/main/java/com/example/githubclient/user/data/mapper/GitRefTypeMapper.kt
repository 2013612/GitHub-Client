package com.example.githubclient.user.data.mapper

import com.example.githubclient.user.data.model.event.RemoteGitRefType
import com.example.githubclient.user.domain.model.event.GitRefType

fun RemoteGitRefType.toGitRefType(): GitRefType =
    when (this) {
        RemoteGitRefType.Branch -> GitRefType.Branch
        RemoteGitRefType.Tag -> GitRefType.Tag
        RemoteGitRefType.Repository -> GitRefType.Repository
    }
