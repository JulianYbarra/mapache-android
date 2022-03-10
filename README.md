<h1 align="center">
  <img alt="AniApi" src="https://aniapi.com/img/aniapi_logo.png" width="250px" />
</h1>

It is a simple android application that allows the user to see the list of animes, as well as the details of each one of them.

## Tech stack 🛠

* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
        * [Flow](https://developer.android.com/kotlin/flow?hl=es-419) - notify views about change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
        * [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection
        * [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
        * [SavedStateHandle](https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle) A handle to saved state passed down to androidx.lifecycle.ViewModel. You should use SavedStateViewModelFactory if you want to receive this object in ViewModel's constructor.
    * [Glide](https://github.com/bumptech/glide) - image loading library
    
* Modern Architecture
    * Clean Architecture (at feature module level)
    * Single activity architecture using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Flow](https://developer.android.com/kotlin/flow?hl=es-419), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

* UI
    * [Material design](https://material.io/design)
    * Reactive UI
    
### Status: 🚧 In progress

## API
These sample use [ANIAPI](https://aniapi.com/).<br>
