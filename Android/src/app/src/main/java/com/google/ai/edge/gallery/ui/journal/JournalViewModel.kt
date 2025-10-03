package com.google.ai.edge.gallery.ui.journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import <your.package>.data.db.AppDatabase
import <your.package>.data.journal.JournalRepository

/**
 * ViewModel cho tab "Nhật ký" - chỉ lưu text, không gọi LLM.
 */
class JournalViewModel(app: Application) : AndroidViewModel(app) {
  private val repo = JournalRepository(AppDatabase.get(app))
  val entries = repo.entriesPaging().cachedIn(viewModelScope)

  fun addEntry(text: String) = viewModelScope.launch {
    repo.add(text)
  }
}
