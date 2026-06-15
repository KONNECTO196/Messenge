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
    tableName = "messages",
    indices   = [
        Index(value = ["thread_id"]),
        Index(value = ["date_sent"]),
        Index(value = ["address"]),
        Index(value = ["is_read"]),
        Index(value = ["is_starred"]),
    ]
)
data class MessageEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "thread_id")       val threadId:          Long,
    @ColumnInfo(name = "address")         val address:           String,
    @ColumnInfo(name = "body")            val body:              String?,
    @ColumnInfo(name = "date_sent")       val dateSent:          Long,
    @ColumnInfo(name = "date_received")   val dateReceived:      Long,
    @ColumnInfo(name = "is_sent")         val isSent:            Boolean,
    @ColumnInfo(name = "is_read")         val isRead:            Boolean,
    @ColumnInfo(name = "status")          val status:            MessageStatus,
    @ColumnInfo(name = "type")            val type:              MessageType,
    @ColumnInfo(name = "error_code")      val errorCode:         Int       = 0,
    @ColumnInfo(name = "is_starred")      val isStarred:         Boolean   = false,
    @ColumnInfo(name = "subject")         val subject:           String?   = null,
    @ColumnInfo(name = "sub_id")          val simSubscriptionId: Int       = -1,
    @ColumnInfo(name = "server_msg_id")   val serverMessageId:   String?   = null,
    @ColumnInfo(name = "reply_to_id")     val replyToMessageId:  Long?     = null,
    @ColumnInfo(name = "participants")    val participants:       String    = "",
)