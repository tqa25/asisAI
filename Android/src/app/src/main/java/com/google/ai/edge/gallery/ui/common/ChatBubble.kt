package com.google.ai.edge.gallery.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import <your.package>.data.chat.ChatMessage

/**
 * Bong bóng chat đơn giản: căn phải cho user, trái cho assistant.
 * Nguồn: Compose LazyColumn + item layout. 
 */
@Composable
fun ChatBubble(msg: ChatMessage) {
  val isUser = msg.role == "user"
  val bg = if (isUser) MaterialTheme.colorScheme.primaryContainer
           else MaterialTheme.colorScheme.secondaryContainer
  val align = if (isUser) Alignment.CenterEnd else Alignment.CenterStart

  Row(Modifier.fillMaxWidth(), horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start) {
    Box(
      modifier = Modifier
        .widthIn(max = 360.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(bg)
        .padding(12.dp),
      contentAlignment = align
    ) {
      Text(msg.content)
    }
  }
  Spacer(Modifier.height(6.dp))
}
