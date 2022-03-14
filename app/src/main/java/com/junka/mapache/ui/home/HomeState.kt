package com.junka.mapache.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.junka.mapache.R
import com.junka.mapache.common.Error

fun Fragment.buildHomeState(
    context : Context = requireContext(),
    navController: NavController = findNavController(),
) = HomeState(navController,context)

class HomeState(
    private val navController: NavController,
    private val context : Context
) {

    fun onAnimeClicked(id : Int){
        val action = HomeFragmentDirections.actionHomeDestToAnimeDetailDest(id)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}