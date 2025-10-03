package com.google.ai.edge.gallery.data.journal

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Nhật ký: chỉ lưu text + thời gian. Hiển thị kiểu "history" như chat.
 */
@Entity(tableName = "journal_entries")
data class JournalEntry(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val content: String,
  val createdAt: Long = System.currentTimeMillis()
)
