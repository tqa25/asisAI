package com.google.ai.edge.gallery.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import androidx.paging.LoadState
import <your.package>.ui.common.ChatBubble
import <your.package>.ui.common.SendBar
import <your.package>.data.chat.ChatMessage

/**
 * UI hiển thị danh sách tin nhắn theo thread hiện tại + thanh gửi.
 * Nguồn: Compose + Paging compose (collectAsLazyPagingItems, LazyColumn items). 
 */
@Composable
fun ChatScreen(
  vm: ChatViewModel = viewModel() // AndroidViewModel không cần factory khi có default constructor
) {
  val messages: LazyPagingItems<ChatMessage> =
    vm.messages.collectAsLazyPagingItems()

  Column(Modifier.fillMaxSize()) {
    // Danh sách tin nhắn
    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .padding(8.dp)
    ) {
      items(
        count = messages.itemCount,
        key = { index -> messages[index]?.id ?: index.toLong() } // key ổn định giúp giữ vị trí cuộn
      ) { index ->
        messages[index]?.let { ChatBubble(it) }
      }
    }

    // Trạng thái load/append lỗi/đang tải (tuỳ chọn)
    when (val state = messages.loadState.refresh) {
      is LoadState.Loading -> Row(Modifier.padding(8.dp)) { CircularProgressIndicator() }
      is LoadState.Error -> Text("Lỗi tải lịch sử: ${state.error.message}")
      else -> {}
    }

    // Thanh gửi
    SendBar(placeholder = "Nhập câu hỏi...") { prompt ->
      vm.send(prompt) // sẽ lưu user msg + (tạm) ghi assistant demo; Bước 3 (phần 2) nối LLM thật
    }
  }
}
