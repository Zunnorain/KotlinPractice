package com.example.kotlinpractice.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinpractice.R

class CustomImageView @JvmOverloads constructor(context: Context, attrs:AttributeSet? = null, defStyle: Int = 0,
defStyleRes: Int = 0) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var imgView:ImageView
    private var txtView:TextView

    var custom_img: Drawable? = null
        set(value) {
            field = value
            value.let {
                imgView.setImageDrawable(it)
            }
        }
    var random_text: String = ""
        set(value) {
            field = value
            value.let {
                txtView.text = value
            }
        }


    init {
//        _binding = CustomImageviewBinding.inflate(LayoutInflater.from(context), this, true)
        LayoutInflater.from(context).inflate(R.layout.custom_imageview, this,true)

        imgView = findViewById(R.id.customImg)
        txtView = findViewById(R.id.customTV)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomImageView)
            random_text = resources.getText(typedArray.getResourceId(R.styleable.CustomImageView_randomText,1)).toString()
            val imgRes = typedArray.getResourceId(R.styleable.CustomImageView_imgRef, -1)
            if(imgRes!=-1){
                custom_img = AppCompatResources.getDrawable(context,imgRes)
            }
            typedArray.recycle()
        }

    }

    fun setText(text:String){

    }
}