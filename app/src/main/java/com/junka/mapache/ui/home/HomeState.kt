package com.junka.mapache.ui.home

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.junka.mapache.data.model.Anime

fun Fragment.buildHomeState(
    navController: NavController = findNavController(),
) = HomeState(navController)

class HomeState(
    private val navController: NavController
) {
    fun onAnimeClicked(id : Int){
        val action = HomeFragmentDirections.actionHomeDestToAnimeDetailDest(id)
        navController.navigate(action)
    }
}