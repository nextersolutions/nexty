package com.nextersolutions.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}