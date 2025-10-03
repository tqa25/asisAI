package com.google.ai.edge.gallery.ui.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import <your.package>.data.chat.ChatMessage
import <your.package>.data.chat.ChatRepository
import <your.package>.data.db.AppDatabase

/**
 * ViewModel giữ state thread hiện tại, cung cấp flow Paging.
 * Hàm send() sẽ thêm user msg, gọi LLM (sẽ ghép ở Bước 3), rồi ghi assistant msg.
 */
class ChatViewModel(app: Application) : AndroidViewModel(app) {
  private val repo = ChatRepository(AppDatabase.get(app))
  private val _currentThreadId = MutableStateFlow<Long?>(null)

  val threads = repo.threadsPaging().cachedIn(viewModelScope)

  val messages: Flow<PagingData<ChatMessage>> =
    _currentThreadId.filterNotNull()
      .flatMapLatest { repo.messagesPaging(it) }
      .cachedIn(viewModelScope)

  fun openOrCreateThread(threadId: Long? = null) = viewModelScope.launch {
    _currentThreadId.value = threadId ?: repo.newThread()
  }

  fun send(prompt: String) = viewModelScope.launch {
    val tid = _currentThreadId.value ?: repo.newThread().also { _currentThreadId.value = it }
    repo.addUserMessage(tid, prompt)

    // TODO(Bước 3): gọi LLM service của Gallery ở đây -> val answer = llm.generate(prompt)
    val answer = "(demo) Tin nhắn đã được lưu. Tích hợp LLM ở bước sau."
    repo.addAssistantMessage(tid, answer)
  }
}
