/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.model

enum class MessageStatus {
    None, Sending, Sent, Delivered, Read, Failed;

    companion object {
        fun from(status: Int, boxType: Int): MessageStatus = when {
            boxType == 5    -> Failed
            boxType == 4    -> Sending
            status == 0     -> Delivered
            status == 32    -> Sent
            status == 64    -> Failed
            else            -> Sent
        }
    }
}

enum class MessageType { SMS, MMS, RCS }

enum class MessageProtocol { SMS, MMS, RCS }

enum class ConversationSortOrder { ByDate, ByName, UnreadFirst }