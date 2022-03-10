package com.junka.mapache.ui.home

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.junka.mapache.data.model.Document

fun Fragment.buildHomeState(
    navController: NavController = findNavController(),
) = HomeState(navController)

class HomeState(
    private val navController: NavController
) {
    fun onAnimeClicked(anime : Document){
        val action = HomeFragmentDirections.actionHomeDestToAnimeDetailDest(anime)
        navController.navigate(action)
    }
}