package com.example.kotlinpractice

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.kotlinpractice.customviews.CustomButtonView
import com.example.kotlinpractice.customviews.CustomEditTextView
import com.example.kotlinpractice.customviews.CustomImageView

class TipCalculator : AppCompatActivity() {
    private lateinit var baseET: EditText
    private lateinit var totalTxtView: TextView
    private lateinit var skBar: SeekBar
    private lateinit var tipPerc : TextView
    private lateinit var tipAmount: TextView
    private lateinit var customImgText: CustomImageView
    private lateinit var customBtnProg: CustomButtonView
    private lateinit var customET: CustomEditTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)
        baseET = findViewById(R.id.baseEditText)
        totalTxtView = findViewById(R.id.totalTextView)
        skBar = findViewById(R.id.seekBar)
        tipPerc = findViewById(R.id.tipPercent)
        tipAmount = findViewById(R.id.tipTextView)
        customImgText = findViewById(R.id.imgWithText)
        customBtnProg = findViewById(R.id.custom_BtnProgress)

        //Custom View Button
        setButton()
        customBtnProg.setOnClickListener{
            customBtnProg.setBackgroundResource(R.drawable.btn_bg)
            customBtnProg.showLoader()
            Handler().postDelayed({
                customBtnProg.setText("Success")
                customBtnProg.hideLoader()
                customBtnProg.setBackgroundResource(R.drawable.btn_bg2)
            },5000)
        }


        //Custom View Img
        customImgText.random_text = "Zunnorain Custom View"
        customImgText.custom_img = AppCompatResources.getDrawable(this, R.drawable.ic_launcher_background)


        baseET.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
//                if (s != null) {
//                    if (s.isNotEmpty()) {
                        getTotalResult()
//                    } else {
//                        Toast.makeText(this@TipCalculator, "Enter something", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
            }
        })

        skBar.progress = 15
        tipPerc.text = 15.toString()
        skBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPerc.text = "$progress%"
                getTotalResult()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }

    //Custom View Button
    private fun setButton() {
        customBtnProg.setBackgroundResource(R.drawable.btn_bg2)
        customBtnProg.hideLoader()
    }

    private fun getTotalResult() {
        if(baseET.text.isEmpty()){
            tipAmount.text = ""
            totalTxtView.text = ""
            return
        }
        val progress = skBar.progress
        val base = baseET.text.toString().toDouble()
        val tip =  base * progress/100
        val total = base +tip

        tipAmount.text = tip.toString()
        totalTxtView.text = total.toString()
    }
}