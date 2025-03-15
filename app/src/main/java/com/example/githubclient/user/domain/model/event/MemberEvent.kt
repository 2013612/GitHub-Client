package com.example.githubclient.user.domain.model.event

data class MemberEvent(
    override val id: String,
    override val isoDateTime: String,
    val memberName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventDesc(): String = "add member $memberName to $repoName"
}
