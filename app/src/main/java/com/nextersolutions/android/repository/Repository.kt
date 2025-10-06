package com.nextersolutions.android.repository

import com.nextersolutions.android.models.AllItemsViewData
import com.nextersolutions.android.models.FoodViewData

object Repository {
    private val foods = listOf(
        FoodViewData(
            id = "0",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Nutella_for_breakfast_-_Flickr_-_love.jsc.jpg/250px-Nutella_for_breakfast_-_Flickr_-_love.jsc.jpg",
            description = "Nutella – chocolate hazelnut spread"
        ),
        FoodViewData(
            id = "1",
            imageUrl = "https://fainemisto.com/media/products_iiko/140bc2d0-5596-474a-a600-c5cd8e9e1639.jpg",
            description = "Coca-Cola – soft drink"
        ),
        FoodViewData(
            id = "2",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6YgKW6NuZfanc76wrDWuRZZo0_14uNO3tD4nxNgrBFmPmTUoJ9QkX4ppiShr-JNegEqQ&usqp=CAU",
            description = "Campbell’s Tomato Soup – canned soup"
        ),
        FoodViewData(
            id = "3",
            imageUrl = "https://static.independent.co.uk/2023/09/27/00/26145330-9d584c36-7a49-4962-9050-cfc741366fcd.jpeg",
            description = "Heinz Tomato Ketchup – ketchup"
        ),
        FoodViewData(
            id = "4",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBW3bP0yCXO8zcGc1mVTDComHu0IY_tCEg1w&s",
            description = "Planters Peanuts – salted peanuts"
        ),
        FoodViewData(
            id = "5",
            imageUrl = "https://i5.walmartimages.com/asr/0c0a7e5a-1b55-4295-892e-8000c7e82091.768b59f3877d9bb2c413420259005289.jpeg",
            description = "Oreo – chocolate sandwich cookies"
        ),
        FoodViewData(
            id = "6",
            imageUrl = "https://www.allrecipes.com/thmb/LAfNFUjS5nZBIjOB53D1gS_GB3k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/ar-lays-taste-test-group-4x3-67f87a8f3c4c4710b50ab762b7f90257.jpg",
            description = "Lays – potato chips"
        ),
        FoodViewData(
            id = "7",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/ab/Sprite_lemon_lime_1.jpg",
            description = "Sprite – lemon lime soda"
        ),
        FoodViewData(
            id = "8",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/e5/Plain-M%26Ms-Pile.jpg",
            description = "M&M’s – chocolate candies"
        ),
        FoodViewData(
            id = "9",
            imageUrl = "https://cdnimg.webstaurantstore.com/images/products/large/688749/2383251.jpg",
            description = "Snickers – chocolate bar"
        )
    )

    fun getFirstPage(): List<FoodViewData> {
        return foods.subList(0, 4)
    }

    fun getAll(): AllItemsViewData {
        return AllItemsViewData(
            id = System.currentTimeMillis().toString(),
            items = foods
        )
    }
}
