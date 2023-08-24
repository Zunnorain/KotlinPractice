package com.example.kotlinpractice.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.CompoundButton
import androidx.core.content.res.ResourcesCompat
import com.example.kotlinpractice.R
import kotlin.math.min
import kotlin.math.roundToInt

class CustomCompoundButtonView @JvmOverloads constructor(context: Context, attrs:AttributeSet? = null, defStyle: Int = 0,
                                                         defStyleRes: Int = 0) : CompoundButton(context, attrs, defStyle, defStyleRes) {
    var buzzColor = 0
    var buzzerStroke = 0
    var width1 = 0
    var height1 = 0
    var path:Path
    var paint:Paint

    private fun getStuffFromXML(attrs: AttributeSet?, context: Context){
        val data = context.obtainStyledAttributes(attrs, R.styleable.CustomCompoundButtonView, 0 ,0)
        buzzColor = data.getColor(R.styleable.CustomCompoundButtonView_buzz_color, Color.RED)
        buzzerStroke = data.getColor(R.styleable.CustomCompoundButtonView_stroke_buzzColor, Color.GRAY)
        data.recycle()
    }

    private fun getBuzzerColor(buzzerColor:Int): Int {
        return buzzerColor
    }
    private fun getStrokeBuzzerColor(buzzerStrokeColor:Int): Int {
        return buzzerStrokeColor
    }

    private fun setBuzzerColor(buzzerColor:Int) {
        this.buzzColor = buzzerColor
        invalidate()
        requestLayout()
    }
    private fun setStrokeBuzzerColor(buzzerStrokeColor:Int) {
        this.buzzerStroke = buzzerStrokeColor
        invalidate()
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width1 = this.getWidth()
        height1 = this.getHeight()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Grab canvas dimensions.
        width1 = getWidth()
        height1 = getHeight()
        // Calculate horizontal center.
        val space = min(width1,height1) /10

        val tl2x = (width1/10)+space
        val tl2y = (height1/10)-space

        val tlx = (width1/10)-space
        val tly = (height1/10)+space

        val blx = tlx
        val bly = (height1-(height1/10))-space

        val bl2x = tl2x
        val bl2y = (height1-(height1/10))+space


        val brx = (width1-(width1/10)-space)
        val bry = bl2y

        val br2x = (width1-(width1/10))+space
        val br2y = bly

        val tr1x = br2x
        val tr1y = tly

        val tr2x = brx
        val tr2y = tl2y

        path.moveTo(tl2x.toFloat(), tl2y.toFloat())
        path.lineTo(tlx.toFloat(), tly.toFloat())

        path.lineTo(((width1/2)-space).toFloat(), (height1/2).toFloat())

        path.lineTo(blx.toFloat(), bly.toFloat())
        path.lineTo(bl2x.toFloat(), bl2y.toFloat())

        path.lineTo(width1/2.toFloat(), (height1/2)+space.toFloat())

        path.lineTo(((width1/2)+space).toFloat(), ((height1/2).toFloat()))

        path.lineTo(tr1x.toFloat(), tr1y.toFloat())
        path.lineTo(tr2x.toFloat(), tr2y.toFloat())

        path.lineTo(width1/2.toFloat(), (height1/2)-space.toFloat())
        path.lineTo(tl2x.toFloat(), tl2y.toFloat())

        paint.color = buzzColor

        if(this.isChecked){
            paint.style = Paint.Style.FILL
            canvas?.drawPath(path,paint)
        }
        paint.color = buzzerStroke
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = (space/3).toFloat()
        canvas?.drawPath(path,paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            isChecked = false
        }
        invalidate()
        requestLayout()
        return true
    }

    init {
        getStuffFromXML(attrs,context)
        path = Path()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

}