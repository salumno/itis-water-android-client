package ru.kpfu.itis.water.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}