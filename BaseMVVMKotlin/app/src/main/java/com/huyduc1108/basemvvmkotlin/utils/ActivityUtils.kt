package com.huyduc1108.basemvvmkotlin.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.huyduc1108.basemvvmkotlin.R
import dagger.internal.Preconditions
import kotlin.math.cos
import kotlin.math.sin

object ActivityUtils {
    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun pushFragment(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
//                transaction?.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_exit, R.anim.pop_enter);
        transaction?.add(frameId, fragment)
        transaction?.addToBackStack(fragment.javaClass.name)
        transaction?.commit()
    }

    fun pushFragmentNoBackStack(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        if (fragmentManager?.findFragmentByTag(fragment.javaClass.name)?.javaClass?.name == fragment.javaClass.name) {
            for (fragment in fragmentManager.fragments) {
                if (fragment.isVisible)
                    transaction?.hide(fragment)
            }
            transaction?.show(fragmentManager.findFragmentByTag(fragment.javaClass.name)!!)
        } else {
            transaction?.add(frameId, fragment, fragment.javaClass.name)
        }
        transaction?.commit()
    }

    fun pushFragmentHasAnimation(
        fragmentManager: FragmentManager?,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_in_left,
            R.anim.slide_out_left,
            R.anim.slide_out_right
        )
        transaction?.add(frameId, fragment)
        transaction?.addToBackStack(fragment.javaClass.name)
        transaction?.commit()
    }

    fun replaceByFragment(
        fragmentManager: FragmentManager,
        frameId: Int,
        fragment: Fragment
    ) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        Preconditions.checkNotNull(fragment)
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        transaction.replace(frameId, fragment, fragment.tag)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }

    fun popFragment(fragmentManager: FragmentManager?) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        fragmentManager?.popBackStack()
//        if (fragmentManager.backStackEntryCount > 0) {
//            val fragmentTag =
//                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)
//                    .name
//        }
    }

    fun popAllFragment(fragmentManager: FragmentManager) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        fragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    fun popNFragment(fragmentManager: FragmentManager, n: Int) {
        Preconditions.checkNotNull(
            fragmentManager
        )
        for (i in 0 until n) {
            fragmentManager.popBackStack()
        }
        if (fragmentManager.backStackEntryCount > 0) {
            val fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)
                    .name
        }
    }

    fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        var theta = lon1 - lon2
        var dist =
            sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(
                deg2rad(theta)
            )
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist
    }

    fun deg2rad(deg: Double) = (deg * Math.PI / 180.0)

    fun rad2deg(rad: Double) = (rad * 180.0 / Math.PI)

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun setMargins(
        view: View,
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
    ) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

    fun dp(int: Int): Int = (int * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}