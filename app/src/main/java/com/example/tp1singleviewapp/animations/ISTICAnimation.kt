package com.example.tp1singleviewapp.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.ScaleAnimation

object ISTICAnimation {

    const val DURATION_DEFAULT = 850L
    const val DURATION1 = 750L
    const val DURATION2 = 350L
    const val DURATION3 = 450L
    const val DURATION4 = 550L
    const val DURATION5 = 650L
    const val DURATION6 = 750L

    fun fadeIn(
        view: View,
        delay: Long = 0L,
        duration: Long = DURATION_DEFAULT
    ) {
        view.alpha = 0f
        view.scaleX = 0f
        view.scaleY = 0f

        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1f)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f)

        alpha.duration = duration
        scaleX.duration = duration
        scaleY.duration = duration

        alpha.startDelay = delay
        scaleX.startDelay = delay
        scaleY.startDelay = delay

        AnimatorSet().apply {
            playTogether(alpha, scaleX, scaleY)
            start()
        }
    }

}