package com.google.ai.edge.gallery.data.chat

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Một "cuộc trò chuyện" (thread) để nhóm các tin nhắn.
 * Room yêu cầu @Entity để tạo bảng; @PrimaryKey(autoGenerate = true) tạo khóa tự tăng.
 * Nguồn: Android Room - Entities & DAO. 
 */
@Entity(tableName = "chat_threads")
data class ChatThread(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String = "Cuộc trò chuyện",
  val createdAt: Long = System.currentTimeMillis(),
  val updatedAt: Long = System.currentTimeMillis()
)
