package com.nextersolutions.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nextersolutions.android.models.AllItemsViewData
import com.nextersolutions.android.models.FoodViewData
import com.nextersolutions.android.repository.Repository
import com.nextersolutions.android.ui.navigation.Route
import com.nextersolutions.android.ui.screens.ItemScreen
import com.nextersolutions.android.ui.screens.ItemsScreen
import com.nextersolutions.android.ui.theme.NextyTheme
import com.nextersolutions.nexty.Nexty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NextyTheme {
                Navigator()
            }
        }
    }
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Main
    ) {
        composable<Route.Main> {
            ItemsScreen(
                items = Repository.getFirstPage(),
                onViewItem = { item ->
                    Nexty.put(item.id, item)
                    navController.navigate(
                        Route.ViewFood(item.id)
                    )
                },
                onSeeMoreClick = {
                    val items = Repository.getAll()
                    Nexty.put(items.id, items)
                    navController.navigate(
                        Route.ViewAll(items.id)
                    )
                },
                showSeeMore = true
            )
        }

        composable<Route.ViewAll> { entry ->
            val route = entry.toRoute<Route.ViewAll>()
            val all = Nexty.get<AllItemsViewData>(route.id)

            if (all != null) {
                ItemsScreen(
                    items = all.items,
                    onViewItem = {

                    },
                    onSeeMoreClick = {},
                    showSeeMore = false
                )
            }
        }

        composable<Route.ViewFood> { entry ->
            val route = entry.toRoute<Route.ViewFood>()
            val item = Nexty.get<FoodViewData>(route.id)
            if (item != null) {
                ItemScreen(
                    item = item,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
