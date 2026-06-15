/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName    = "attachments",
    foreignKeys  = [
        ForeignKey(
            entity        = MessageEntity::class,
            parentColumns = ["id"],
            childColumns  = ["message_id"],
            onDelete      = ForeignKey.CASCADE,
        )
    ],
    indices      = [Index(value = ["message_id"])]
)
data class AttachmentEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "message_id")  val messageId:  Long,
    @ColumnInfo(name = "uri")         val uri:        String,
    @ColumnInfo(name = "mime_type")   val mimeType:   String,
    @ColumnInfo(name = "name")        val name:       String,
    @ColumnInfo(name = "size")        val size:       Long,
    @ColumnInfo(name = "width")       val width:      Int?    = null,
    @ColumnInfo(name = "height")      val height:     Int?    = null,
    @ColumnInfo(name = "duration_ms") val durationMs: Long?   = null,
)