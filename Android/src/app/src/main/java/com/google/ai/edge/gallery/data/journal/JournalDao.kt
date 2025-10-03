package com.google.ai.edge.gallery.data.journal

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JournalDao {
  @Insert
  suspend fun insert(entry: JournalEntry): Long

  @Query("SELECT * FROM journal_entries ORDER BY createdAt DESC")
  fun entriesPaging(): PagingSource<Int, JournalEntry>

  @Query("DELETE FROM journal_entries WHERE id=:id")
  suspend fun delete(id: Long)
}
