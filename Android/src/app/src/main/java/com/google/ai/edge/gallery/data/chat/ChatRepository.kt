package com.google.ai.edge.gallery.data.chat

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import <your.package>.data.db.AppDatabase

/**
 * Repository đóng gói logic DB + tiện ích tạo title.
 * Nguồn: Pattern Repository phổ biến với Room/Paging.
 */
class ChatRepository(private val db: AppDatabase) {
  fun threadsPaging() = Pager(PagingConfig(pageSize = 20)) { db.chatDao().threadsPaging() }.flow
  fun messagesPaging(threadId: Long) = Pager(PagingConfig(pageSize = 50)) { db.chatDao().messagesPaging(threadId) }.flow

  suspend fun newThread(initialTitle: String = "Cuộc trò chuyện"): Long =
    withContext(Dispatchers.IO) { db.chatDao().insertThread(ChatThread(title = initialTitle)) }

  suspend fun addUserMessage(threadId: Long, text: String) =
    withContext(Dispatchers.IO) {
      db.chatDao().insertMessage(ChatMessage(threadId = threadId, role = "user", content = text))
      db.chatDao().updateThreadMeta(threadId, titleFrom(text), System.currentTimeMillis())
    }

  suspend fun addAssistantMessage(threadId: Long, text: String) =
    withContext(Dispatchers.IO) {
      db.chatDao().insertMessage(ChatMessage(threadId = threadId, role = "assistant", content = text))
      db.chatDao().updateThreadMeta(threadId, titleFrom(text), System.currentTimeMillis())
    }

  private fun titleFrom(text: String) = text.lineSequence().firstOrNull()?.take(40) ?: "Cuộc trò chuyện"
}
