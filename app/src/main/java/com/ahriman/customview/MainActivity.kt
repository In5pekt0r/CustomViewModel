package com.ahriman.customview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import java.util.*


class MainActivity : AppCompatActivity() {
    val ATTB_COLOR_ONE: String = "COLOR_ONE_KEY"
    val ATTB_COLOR_TWO: String = "COLOR_TWO_KEY"
    lateinit var gradientButton: GradientView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gradientButton = findViewById(R.id.GradientButton)
        gradientButton.setOnClickListener(listener)

    }
    override fun onSaveInstanceState(state: Bundle) {
        state.putInt(ATTB_COLOR_ONE, gradientButton.getFirstColor())
        state.putInt(ATTB_COLOR_TWO, gradientButton.getSecondColor())
        super.onSaveInstanceState(state)
    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)

        if (state != null) {
            gradientButton.setFirstColor(state.getInt(ATTB_COLOR_ONE))
            gradientButton.setSecondColor(state.getInt(ATTB_COLOR_TWO))
        }
    }
    val listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.GradientButton -> {
                gradientButton.generateColor()
                gradientButton.invalidate()
            }
        }
    }



}
