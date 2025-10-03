package com.google.ai.edge.gallery.data.chat

import androidx.paging.PagingSource
import androidx.room.*

/**
 * DAO cung cấp truy vấn Room. 
 * Trả về PagingSource để dùng với Paging 3 (AndroidX). 
 * Nguồn: Room + Paging integration. 
 */
@Dao
interface ChatDao {

  @Insert
  suspend fun insertThread(thread: ChatThread): Long

  @Query("UPDATE chat_threads SET title=:title, updatedAt=:updatedAt WHERE id=:id")
  suspend fun updateThreadMeta(id: Long, title: String, updatedAt: Long)

  @Insert
  suspend fun insertMessage(msg: ChatMessage): Long

  @Transaction
  @Query("SELECT * FROM chat_threads ORDER BY updatedAt DESC")
  fun threadsPaging(): PagingSource<Int, ChatThread>

  @Query("SELECT * FROM chat_messages WHERE threadId = :threadId ORDER BY createdAt ASC")
  fun messagesPaging(threadId: Long): PagingSource<Int, ChatMessage>

  @Query("DELETE FROM chat_threads WHERE id=:threadId")
  suspend fun deleteThread(threadId: Long)
}
