package com.google.ai.edge.gallery.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

/**
 * Thanh nhập liệu dùng chung cho Chat và Nhật ký.
 * Nguồn: Compose TextField + Button; pattern chuẩn UI input. 
 */
@Composable
fun SendBar(
  modifier: Modifier = Modifier,
  placeholder: String = "Nhập nội dung...",
  onSend: (String) -> Unit
) {
  var text by remember { mutableStateOf(TextFieldValue("")) }

  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp),
  ) {
    TextField(
      value = text,
      onValueChange = { text = it },
      placeholder = { Text(placeholder) },
      modifier = Modifier.weight(1f)
    )
    Spacer(Modifier.width(8.dp))
    Button(
      onClick = {
        val msg = text.text.trim()
        if (msg.isNotEmpty()) {
          onSend(msg)
          text = TextFieldValue("")
        }
      }
    ) { Text("Gửi") }
  }
}
