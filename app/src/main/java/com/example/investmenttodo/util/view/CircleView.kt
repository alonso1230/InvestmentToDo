package com.example.investmenttodo.util.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.investmenttodo.R

class CircleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.shape_circle_blue_light)
        gravity = Gravity.CENTER
        isAllCaps = true
        setTextColor(ContextCompat.getColor(context, R.color.white))
        typeface = Typeface.DEFAULT_BOLD
    }

    fun setFirstLetter(text: CharSequence?) {
        setText(text?.first().toString())
    }

    fun setCircleColor(resColor: Int) {
        when (background) {
            is ShapeDrawable -> {
                val shapeDrawable = background as ShapeDrawable
                shapeDrawable.paint.color = resColor
            }
            is GradientDrawable -> {
                val gradientDrawable = background as GradientDrawable
                gradientDrawable.setColor(resColor)
            }
            is ColorDrawable -> {
                val colorDrawable = background as ColorDrawable
                colorDrawable.color = resColor
            }
        }
    }
}