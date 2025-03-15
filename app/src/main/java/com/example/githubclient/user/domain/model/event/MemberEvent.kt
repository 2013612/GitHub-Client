package com.example.githubclient.user.domain.model.event

data class MemberEvent(
    val id: String,
    val time: String,
    val memberName: String,
    val repoName: String,
) : UserEvent() {
    override fun getEventId(): String = id

    override fun getEventTime(): String = time

    override fun getEventDesc(): String = "add member $memberName to $repoName"
}
