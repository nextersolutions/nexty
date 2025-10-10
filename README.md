# Nexty - Real-time Android in-memory datastore [![](https://jitpack.io/v/nextersolutions/nexty.svg)](https://jitpack.io/#nextersolutions/nexty)

Nexty is a lightweight library that allows to pass data between screens without serialization.

It stores data in memory and they are accessible while app is alive.

Data is not stored into any local DB and are accessible in the runtime only.

Passing data between screens is thread-safe, because Nexty provides synchronized access to the data
pairs.

## Installation

Add the JitPack repository to your `settings.gradle.kts` file:

```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app's build.gradle.kts file:

```kotlin
dependencies {
    implementation("com.github.nextersolutions:nexty:1.0.0")
}
```

## Usage

Nexty allows to store static key-value pairs, as well as to store data pairs as flow. Example of
static data key-value pair usage:

Imagine you have model, that declares some data about food:

```kotlin
data class FoodViewData(
    val id: String,
    val imageUrl: String,
    val description: String
)
```

You have these graph of routes:

```kotlin
@Serializable
sealed class Route {
    @Serializable
    data object Main : Route()

    @Serializable
    data class ViewFood(val id: String) : Route()
}
```

You want to pass it as a param from one screen to another:

```kotlin
composable<Route.Main> {
    ItemsScreen(
        onViewItem = { item: FoodViewData ->
            // put into Nexty
            Nexty.put(item.id, item)

            // put id only to navigation route
            navController.navigate(
                Route.ViewFood(item.id)
            )
        },
        //...
    )
}
```

In the respective route:

```kotlin
composable<Route.ViewFood> { entry ->
    // convert the NavBackStackEntry into your route (according to docs)
    val route = entry.toRoute<Route.ViewFood>()

    // get the typed data from the Nexty
    val item = Nexty.get<FoodViewData>(route.id)

    if (item != null) {
        ItemScreen(
            item = item, // pass the data to the screen
            onBackClick = { navController.popBackStack() }
        )
    }
}
```

To use the Nexty for storing data as flow you can use `putMutable` method.

```kotlin
val item: FoodViewData = ...

Nexty.putMutable(item.id, item)
```

And to get the data as flow use the `getAsFlow` method:

```kotlin
viewModelScope.launch {
    Nexty
        .getAsFlow(id)
        .collectLatest {
            val item = it as? FoodViewData

            // do something with item 
        }

}
```

In case you put the new value to the existing mutable flow, flow won't be recreated but the new
value
will be emitted into it.

