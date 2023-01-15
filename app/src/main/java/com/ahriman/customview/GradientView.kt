package com.ahriman.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*


class GradientView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.gradientViewStyle, defStyleRs: Int = R.style.GradientViewStyle):View(context, attrs, defStyleAttr) {

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
    private var size = resources.displayMetrics.run { widthPixels / density }

    init {
        paint.isAntiAlias = true
        attrs?.let { setupAttributes(it, defStyleAttr, defStyleRs) }
        isClickable = true
    }




    fun generateColor(){
        val rnd = Random()
        CircleColorFirst = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        CircleColorSecond = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

    }

    fun getFirstColor():Int{
        return CircleColorFirst
    }
    fun getSecondColor():Int{
        return CircleColorSecond
    }
    fun setFirstColor(color:Int){
        CircleColorFirst = color
    }
    fun setSecondColor(color:Int){
        CircleColorSecond = color
    }

    private fun setupAttributes(attrs: AttributeSet, defStyleAttr: Int, defStyleRs: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.GradientView, defStyleAttr, defStyleRs)
            .apply {
                try {
                    backgroundColorFirst = getColor(R.styleable.GradientView_backgroundColorFirst, DEFAULT_BACKGROUND_COLOR_FIRST)
                    backgroundColorSecond = getColor(R.styleable.GradientView_backgroundColorSecond, DEFAULT_BACKGROUND_COLOR_SECOND)
                    CircleColorFirst = getColor(R.styleable.GradientView_circleGradientColorFirst, DEFAULT_CIRCLE_COLOR_FIRST)
                    CircleColorSecond = getColor(R.styleable.GradientView_circleGradientColorSecond, DEFAULT_CIRCLE_COLOR_SECOND)
                } finally {
                    recycle()
                }
        }



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
            0f, 0f, 0f,size.toFloat(), CircleColorFirst, CircleColorSecond, Shader.TileMode.MIRROR
        )
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(size / 2f, size / 2f, radius/(1.5F), paint)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight).toFloat()
        setMeasuredDimension(size.toInt(), size.toInt())
    }
}