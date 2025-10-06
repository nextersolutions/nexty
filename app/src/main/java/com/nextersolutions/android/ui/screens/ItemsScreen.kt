package com.nextersolutions.android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nextersolutions.android.models.FoodViewData
import com.nextersolutions.android.ui.components.ItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    items: List<FoodViewData>,
    onViewItem: (FoodViewData) -> Unit,
    onSeeMoreClick: () -> Unit,
    showSeeMore: Boolean
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Main screen")
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
                .verticalScroll(rememberScrollState())
        ) {
            items.forEach { item ->
                ItemCard(
                    item = item,
                    onClick = onViewItem
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (showSeeMore) {
                TextButton(
                    onClick = onSeeMoreClick
                ) {
                    Text(text = "See more...")
                }
            }
        }
    }
}
