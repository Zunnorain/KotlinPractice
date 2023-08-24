package com.example.kotlinpractice.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.example.kotlinpractice.R


class CustomEditTextView : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private var mClearButtonImage: Drawable? = null

    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_clear_opaque, null
        )
        //If the clear (X) button is tapped, clear the text.
        setOnTouchListener { v, event ->
                // Check for actions if the button is tapped.
                // Check for ACTION_DOWN (always occurs before ACTION_UP).
                if (event.action == MotionEvent.ACTION_DOWN) {
                    // Switch to the black version of clear button.
                    mClearButtonImage =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_clear, null
                        )
                    showClearButton()
                }
                // Check for ACTION_UP.
                if (event.action == MotionEvent.ACTION_UP) {
                    // Switch to the opaque version of clear button.
                    mClearButtonImage =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_clear_opaque, null
                        )
                    // Clear the text and hide the clear button.
                    text?.clear()
                    hideClearButton()
                    true
                }
            false
        }

        //If the text changes, show or hide the clear (X) button.
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showClearButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,  // Start of text.
            null,  // Above text.
            mClearButtonImage,  // End of text.
            null
        ) // Below text.
    }

    /**
     * Hides the clear button.
     */
    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,  // Start of text.
            null,  // Above text.
            null,  // End of text.
            null
        ) // Below text.
    }

}