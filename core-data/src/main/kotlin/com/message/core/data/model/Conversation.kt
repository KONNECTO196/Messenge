/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.model

data class Conversation(
    val threadId:           Long,
    val conversationId:     Long,
    val recipients:         List<Recipient>,
    val snippet:            String,
    val snippetType:        MessageType    = MessageType.SMS,
    val date:               Long,
    val unreadCount:        Int,
    val isPinned:           Boolean        = false,
    val isMuted:            Boolean        = false,
    val isArchived:         Boolean        = false,
    val isBlocked:          Boolean        = false,
    val draft:              String?        = null,
    val isLastMessageSent:  Boolean        = false,
    val lastMessageStatus:  MessageStatus  = MessageStatus.None,
    val simSubscriptionId:  Int            = -1,
)

val Conversation.isGroup:        Boolean get() = recipients.size > 1
val Conversation.isRead:         Boolean get() = unreadCount == 0
val Conversation.hasDraft:       Boolean get() = !draft.isNullOrBlank()
val Conversation.displayName:    String
    get() = when {
        recipients.isEmpty() -> "Unknown"
        recipients.size == 1 -> recipients.first().displayName
        else -> recipients.take(3).joinToString(", ") { it.displayName }
    }
val Conversation.displaySnippet: String
    get() = if (hasDraft) "Draft: $draft" else snippet

data class Recipient(
    val address:     String,
    val contactName: String? = null,
    val photoUri:    String? = null,
    val lookupKey:   String? = null,
) {
    val displayName: String get() = contactName ?: address
}