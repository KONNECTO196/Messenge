/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.message.core.data.entity.MessageEntity
import com.message.core.data.model.MessageStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages WHERE thread_id = :threadId ORDER BY date_sent ASC")
    fun observeMessages(threadId: Long): Flow<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE is_read = 0 ORDER BY date_sent DESC")
    fun observeUnreadMessages(): Flow<List<MessageEntity>>

    @Query("SELECT COUNT(*) FROM messages WHERE is_read = 0")
    fun observeTotalUnreadCount(): Flow<Int>

    @Query("SELECT * FROM messages WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): MessageEntity?

    @Query("SELECT * FROM messages WHERE thread_id = :threadId ORDER BY date_sent DESC LIMIT 1")
    suspend fun getLatestInThread(threadId: Long): MessageEntity?

    @Query("SELECT * FROM messages WHERE is_starred = 1 ORDER BY date_sent DESC")
    suspend fun getStarredMessages(): List<MessageEntity>

    @Query("""
        SELECT * FROM messages
        WHERE body LIKE '%' || :query || '%'
        ORDER BY date_sent DESC
        LIMIT :limit
    """)
    suspend fun searchMessages(query: String, limit: Int = 200): List<MessageEntity>

    @Query("SELECT COUNT(*) FROM messages WHERE thread_id = :threadId AND is_read = 0")
    suspend fun getUnreadCount(threadId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<MessageEntity>)

    @Update
    suspend fun update(message: MessageEntity)

    @Delete
    suspend fun delete(message: MessageEntity)

    @Query("DELETE FROM messages WHERE thread_id = :threadId")
    suspend fun deleteByThread(threadId: Long)

    @Query("UPDATE messages SET is_read = 1 WHERE thread_id = :threadId")
    suspend fun markThreadRead(threadId: Long)

    @Query("UPDATE messages SET is_read = 0 WHERE thread_id = :threadId")
    suspend fun markThreadUnread(threadId: Long)

    @Query("UPDATE messages SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Long, status: MessageStatus)

    @Query("UPDATE messages SET is_starred = :starred WHERE id = :id")
    suspend fun setStarred(id: Long, starred: Boolean)

    @Transaction
    suspend fun insertReceived(message: MessageEntity) {
        insert(message)
        markThreadUnread(message.threadId)
    }
}