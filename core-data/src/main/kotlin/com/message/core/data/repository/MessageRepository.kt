/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.repository

import com.message.core.data.model.Message
import com.message.core.data.model.MessageStatus
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun observeMessages(threadId: Long):     Flow<List<Message>>
    fun observeUnreadCount():                Flow<Int>
    suspend fun getById(id: Long):           Message?
    suspend fun getStarred():                List<Message>
    suspend fun search(query: String):       List<Message>
    suspend fun insert(message: Message)
    suspend fun delete(message: Message)
    suspend fun deleteThread(threadId: Long)
    suspend fun markThreadRead(threadId: Long)
    suspend fun markThreadUnread(threadId: Long)
    suspend fun updateStatus(id: Long, status: MessageStatus)
    suspend fun setStarred(id: Long, starred: Boolean)
}