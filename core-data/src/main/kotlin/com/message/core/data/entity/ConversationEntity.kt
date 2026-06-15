/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.message.core.data.model.MessageStatus
import com.message.core.data.model.MessageType

@Entity(
    tableName = "conversations",
    indices   = [
        Index(value = ["date"],          name = "idx_conv_date"),
        Index(value = ["is_pinned"],     name = "idx_conv_pinned"),
        Index(value = ["is_archived"],   name = "idx_conv_archived"),
        Index(value = ["unread_count"],  name = "idx_conv_unread"),
        Index(value = ["is_blocked"],    name = "idx_conv_blocked"),
    ]
)
data class ConversationEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "thread_id")
    val threadId: Long,

    @ColumnInfo(name = "recipients")          val recipients:         String,
    @ColumnInfo(name = "snippet")             val snippet:            String,
    @ColumnInfo(name = "date")                val date:               Long,
    @ColumnInfo(name = "unread_count")        val unreadCount:        Int,
    @ColumnInfo(name = "is_pinned")           val isPinned:           Boolean        = false,
    @ColumnInfo(name = "is_muted")            val isMuted:            Boolean        = false,
    @ColumnInfo(name = "is_archived")         val isArchived:         Boolean        = false,
    @ColumnInfo(name = "is_blocked")          val isBlocked:          Boolean        = false,
    @ColumnInfo(name = "draft")               val draft:              String?        = null,
    @ColumnInfo(name = "snippet_type")        val snippetType:        MessageType    = MessageType.SMS,
    @ColumnInfo(name = "is_last_sent")        val isLastMessageSent:  Boolean        = false,
    @ColumnInfo(name = "last_status")         val lastMessageStatus:  MessageStatus  = MessageStatus.None,
    @ColumnInfo(name = "sub_id")              val simSubscriptionId:  Int            = -1,
    @ColumnInfo(name = "notification_uri")    val notificationUri:    String?        = null,
    @ColumnInfo(name = "led_color")           val ledColor:           Int?           = null,
)