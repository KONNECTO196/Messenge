/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.model

data class Message(
    val id:               Long,
    val threadId:         Long,
    val conversationId:   Long,
    val address:          String,
    val senderId:         String?,
    val body:             String?,
    val dateSent:         Long,
    val dateReceived:     Long,
    val isSent:           Boolean,
    val isRead:           Boolean,
    val status:           MessageStatus,
    val type:             MessageType,
    val protocol:         MessageProtocol = MessageProtocol.SMS,
    val participants:     List<String>    = emptyList(),
    val attachments:      List<Attachment> = emptyList(),
    val errorCode:        Int             = 0,
    val isStarred:        Boolean         = false,
    val subject:          String?         = null,
    val simSubscriptionId: Int            = -1,
    val serverMessageId:  String?         = null,
    val replyToMessageId: Long?           = null,
)

val Message.hasMedia:     Boolean get() = attachments.isNotEmpty()
val Message.isFailed:     Boolean get() = status == MessageStatus.Failed
val Message.isMms:        Boolean get() = type == MessageType.MMS
val Message.isRcs:        Boolean get() = type == MessageType.RCS
val Message.isGroupMessage: Boolean get() = participants.size > 1
val Message.displayText:  String
    get() = when {
        !body.isNullOrBlank()    -> body
        attachments.isNotEmpty() -> attachments.first().typeLabel
        else                     -> ""
    }