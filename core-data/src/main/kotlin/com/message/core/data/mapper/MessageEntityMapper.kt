/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.mapper

import com.message.core.data.entity.MessageEntity
import com.message.core.data.model.Message
import com.message.core.data.model.MessageProtocol
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageEntityMapper @Inject constructor() {

    fun toDomain(entity: MessageEntity): Message = Message(
        id                = entity.id,
        threadId          = entity.threadId,
        conversationId    = entity.threadId,
        address           = entity.address,
        senderId          = if (!entity.isSent) entity.address else null,
        body              = entity.body,
        dateSent          = entity.dateSent,
        dateReceived      = entity.dateReceived,
        isSent            = entity.isSent,
        isRead            = entity.isRead,
        status            = entity.status,
        type              = entity.type,
        protocol          = MessageProtocol.SMS,
        participants      = if (entity.participants.isBlank()) emptyList()
                            else entity.participants.split(","),
        errorCode         = entity.errorCode,
        isStarred         = entity.isStarred,
        subject           = entity.subject,
        simSubscriptionId = entity.simSubscriptionId,
        serverMessageId   = entity.serverMessageId,
        replyToMessageId  = entity.replyToMessageId,
    )

    fun toEntity(domain: Message): MessageEntity = MessageEntity(
        id                = domain.id,
        threadId          = domain.threadId,
        address           = domain.address,
        body              = domain.body,
        dateSent          = domain.dateSent,
        dateReceived      = domain.dateReceived,
        isSent            = domain.isSent,
        isRead            = domain.isRead,
        status            = domain.status,
        type              = domain.type,
        errorCode         = domain.errorCode,
        isStarred         = domain.isStarred,
        subject           = domain.subject,
        simSubscriptionId = domain.simSubscriptionId,
        serverMessageId   = domain.serverMessageId,
        replyToMessageId  = domain.replyToMessageId,
        participants      = domain.participants.joinToString(","),
    )
}