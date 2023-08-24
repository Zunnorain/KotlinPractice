package com.example.kotlinpractice.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinpractice.R

class CustomButtonView @JvmOverloads constructor(context: Context, attrs:AttributeSet? = null, defStyle: Int = 0,
                                                 defStyleRes: Int = 0) :
    ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var progress:ProgressBar
    private var txtView:TextView
    private var text:String = ""
    private var isButtonEnabled = false
    private var bgColor:Int = 0
    private var showingLoader = false


    private fun getStuffFromXML(attrs: AttributeSet?, context: Context){
        val data = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonView)
        text = data.getString(R.styleable.CustomButtonView_btn_text).toString()
        isButtonEnabled = data.getBoolean(R.styleable.CustomButtonView_enabled, true)
        bgColor = data.getColor(
            R.styleable.CustomButtonView_backgroundColor, context.resources.getColor(
                R.color.teal_200
            ))
        data.recycle()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_buttonview, this,true)

        progress = findViewById(R.id.progressBar)
        txtView = findViewById(R.id.btnTV)

        getStuffFromXML(attrs, context)

    }

    fun showLoader(){
        showingLoader = true
        txtView.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }
    fun hideLoader(){
        showingLoader = true
        txtView.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }
    fun setText(text:String){
        txtView.text = text
    }
}