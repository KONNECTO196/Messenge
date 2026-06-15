/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.repository

import com.message.core.data.model.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    fun observeConversations():                  Flow<List<Conversation>>
    fun observeArchived():                       Flow<List<Conversation>>
    fun observeConversation(threadId: Long):     Flow<Conversation?>
    fun observeTotalUnread():                    Flow<Int>
    suspend fun getByThreadId(id: Long):         Conversation?
    suspend fun delete(threadId: Long)
    suspend fun setPinned(threadId: Long,  v: Boolean)
    suspend fun setMuted(threadId: Long,   v: Boolean)
    suspend fun setArchived(threadId: Long, v: Boolean)
    suspend fun setBlocked(threadId: Long, v: Boolean)
    suspend fun setDraft(threadId: Long,   draft: String?)
    suspend fun clearUnread(threadId: Long)
}