package com.google.ai.edge.gallery.data.chat

import androidx.room.*

/**
 * Tin nhắn thuộc về một thread (khóa ngoại -> onDelete CASCADE).
 * Nguồn: Quan hệ 1-n trong Room (ForeignKey, Index). 
 */
@Entity(
  tableName = "chat_messages",
  foreignKeys = [ForeignKey(
    entity = ChatThread::class,
    parentColumns = ["id"],
    childColumns = ["threadId"],
    onDelete = ForeignKey.CASCADE
  )],
  indices = [Index("threadId")]
)
data class ChatMessage(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val threadId: Long,
  val role: String,         // "user" | "assistant" | "system"
  val content: String,
  val createdAt: Long = System.currentTimeMillis()
)
