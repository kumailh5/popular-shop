package com.kumail.popularshop.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import timber.log.Timber

/**
 * Created by kumailhussain on 15/10/2021.
 */
fun Fragment.navigateTo(@IdRes destination: Int) {
    try {
        NavHostFragment.findNavController(this).navigate(destination)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this fragment")
    }
}

fun Fragment.navigateTo(navDirections: NavDirections) {
    try {
        NavHostFragment.findNavController(this).navigate(navDirections)
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to navigate from this fragment")
    }
}