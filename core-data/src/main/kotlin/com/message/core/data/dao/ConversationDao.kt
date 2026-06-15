/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.message.core.data.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Query("""
        SELECT * FROM conversations
        WHERE is_archived = 0
        ORDER BY is_pinned DESC, date DESC
    """)
    fun observeConversations(): Flow<List<ConversationEntity>>

    @Query("""
        SELECT * FROM conversations
        WHERE is_archived = 1
        ORDER BY date DESC
    """)
    fun observeArchived(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE thread_id = :threadId LIMIT 1")
    fun observeConversation(threadId: Long): Flow<ConversationEntity?>

    @Query("SELECT SUM(unread_count) FROM conversations WHERE is_archived = 0")
    fun observeTotalUnread(): Flow<Int>

    @Query("SELECT * FROM conversations WHERE thread_id = :threadId LIMIT 1")
    suspend fun getByThreadId(threadId: Long): ConversationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: ConversationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversations: List<ConversationEntity>)

    @Update
    suspend fun update(conversation: ConversationEntity)

    @Query("DELETE FROM conversations WHERE thread_id = :threadId")
    suspend fun delete(threadId: Long)

    @Query("UPDATE conversations SET is_pinned = :pinned WHERE thread_id = :threadId")
    suspend fun setPinned(threadId: Long, pinned: Boolean)

    @Query("UPDATE conversations SET is_muted = :muted WHERE thread_id = :threadId")
    suspend fun setMuted(threadId: Long, muted: Boolean)

    @Query("UPDATE conversations SET is_archived = :archived WHERE thread_id = :threadId")
    suspend fun setArchived(threadId: Long, archived: Boolean)

    @Query("UPDATE conversations SET is_blocked = :blocked WHERE thread_id = :threadId")
    suspend fun setBlocked(threadId: Long, blocked: Boolean)

    @Query("UPDATE conversations SET draft = :draft WHERE thread_id = :threadId")
    suspend fun setDraft(threadId: Long, draft: String?)

    @Query("UPDATE conversations SET unread_count = 0 WHERE thread_id = :threadId")
    suspend fun clearUnread(threadId: Long)
}