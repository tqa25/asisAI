package com.google.ai.edge.gallery.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import <your.package>.data.chat.ChatDao
import <your.package>.data.chat.ChatMessage
import <your.package>.data.chat.ChatThread
import <your.package>.data.journal.JournalDao
import <your.package>.data.journal.JournalEntry

/**
 * RoomDatabase gom tất cả entity + dao. 
 * Tạo singleton DB theo hướng dẫn Room.
 * Chú ý: Room 2.6+ khuyến nghị export schema để hỗ trợ auto-migration; 
 * bạn đã bật plugin Room và schemaDirectory ở Bước 1C. 
 * Nguồn: Room docs (Database, Migrations). 
 */
@Database(
  entities = [
    ChatThread::class, ChatMessage::class,
    JournalEntry::class
  ],
  version = 1,
  exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun chatDao(): ChatDao
  abstract fun journalDao(): JournalDao

  companion object {
    @Volatile private var INSTANCE: AppDatabase? = null
    fun get(context: Context): AppDatabase =
      INSTANCE ?: synchronized(this) {
        Room.databaseBuilder(context, AppDatabase::class.java, "edge_ai.db")
          .fallbackToDestructiveMigration()
          .build()
          .also { INSTANCE = it }
      }
  }
}
