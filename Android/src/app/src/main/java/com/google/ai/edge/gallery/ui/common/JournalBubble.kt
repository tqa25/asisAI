package com.google.ai.edge.gallery.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import <your.package>.data.journal.JournalEntry

/**
 * Bong bóng "nhật ký": 1 chiều (giống tin nhắn nhận), đơn giản.
 */
@Composable
fun JournalBubble(entry: JournalEntry) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(end = 48.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(MaterialTheme.colorScheme.secondaryContainer)
      .padding(12.dp)
  ) {
    Text(entry.content)
  }
  Spacer(Modifier.height(6.dp))
}
