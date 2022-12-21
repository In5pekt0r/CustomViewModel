package com.ahriman.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*


class GradientView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_BACKGROUND_COLOR_FIRST = Color.WHITE
        private const val DEFAULT_BACKGROUND_COLOR_SECOND = Color.BLACK
        private const val DEFAULT_CIRCLE_COLOR_FIRST = Color.RED
        private const val DEFAULT_CIRCLE_COLOR_SECOND = Color.BLUE

    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var backgroundColorFirst = DEFAULT_BACKGROUND_COLOR_FIRST
    private var backgroundColorSecond = DEFAULT_BACKGROUND_COLOR_SECOND
    private var CircleColorFirst = DEFAULT_CIRCLE_COLOR_FIRST
    private var CircleColorSecond = DEFAULT_CIRCLE_COLOR_SECOND
    private var size = 400

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
        isClickable = true
    }
    override fun performClick(): Boolean {
        if (super.performClick()) return true

        val rnd = Random()
        CircleColorFirst = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        CircleColorSecond = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        invalidate()
        return true
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.GradientView,
            0, 0)

        backgroundColorFirst = typedArray.getColor(R.styleable.GradientView_backgroundColorFirst, DEFAULT_BACKGROUND_COLOR_FIRST)
        backgroundColorSecond = typedArray.getColor(R.styleable.GradientView_backgroundColorSecond, DEFAULT_BACKGROUND_COLOR_SECOND)
        CircleColorFirst = typedArray.getColor(R.styleable.GradientView_circleGradientColorFirst, DEFAULT_CIRCLE_COLOR_FIRST)
        CircleColorSecond = typedArray.getColor(R.styleable.GradientView_circleGradientColorSecond, DEFAULT_CIRCLE_COLOR_SECOND)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSquare(canvas)
        drawGradient(canvas)

    }

    private fun drawSquare(canvas:Canvas){

        paint.style = Paint.Style.FILL
        paint.shader = LinearGradient(
            0f, 0f, 0f,height.toFloat(), backgroundColorFirst, backgroundColorSecond, Shader.TileMode.MIRROR
        )

        val radius = size / 2f

        canvas.drawRect(0f,0f,size.toFloat(),size.toFloat(), paint)

    }
    private fun drawGradient(canvas:Canvas){
        val radius = size /2f
        paint.shader = LinearGradient(
            0f, 0f, 0f,height.toFloat(), CircleColorFirst, CircleColorSecond, Shader.TileMode.MIRROR
        )
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(size / 2f, size / 2f, radius/(1.5F), paint)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }
}