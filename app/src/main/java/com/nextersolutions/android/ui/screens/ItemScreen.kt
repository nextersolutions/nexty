package com.nextersolutions.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nextersolutions.android.models.FoodViewData
import com.nextersolutions.android.ui.components.ItemText
import com.nextersolutions.android.ui.components.LoadImageWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    item: FoodViewData,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Item screen")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text("<", color = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(horizontal = 16.dp)
        ) {
            LoadImageWidget(
                url = item.imageUrl,
                modifier = Modifier.clip(RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            ItemText(text = item.description)
        }
    }
}
