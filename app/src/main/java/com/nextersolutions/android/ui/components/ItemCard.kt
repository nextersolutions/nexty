package com.nextersolutions.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nextersolutions.android.models.FoodViewData

@Composable
fun ItemCard(
    item: FoodViewData,
    onClick: (FoodViewData) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        onClick = {
            onClick.invoke(item)
        }
    ) {
        LoadImageWidget(
            url = item.imageUrl,
            modifier = Modifier.fillMaxWidth()
        )

        ItemText(text = item.description)
    }
}
