package com.google.ai.edge.gallery.data.journal

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import <your.package>.data.db.AppDatabase

class JournalRepository(private val db: AppDatabase) {
  fun entriesPaging() = Pager(PagingConfig(pageSize = 30)) { db.journalDao().entriesPaging() }.flow

  suspend fun add(content: String) = withContext(Dispatchers.IO) {
    db.journalDao().insert(JournalEntry(content = content))
  }
}
