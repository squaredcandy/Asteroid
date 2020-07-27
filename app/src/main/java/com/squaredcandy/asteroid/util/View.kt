package com.squaredcandy.asteroid.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

inline fun <reified T : View> ViewGroup.inflate(
    @LayoutRes layout: Int,
    attach: Boolean = false
): T = LayoutInflater.from(context).inflate(layout, this, attach) as T