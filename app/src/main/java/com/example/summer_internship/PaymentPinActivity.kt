package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentPinActivity : AppCompatActivity() {

    var empty = true
    private lateinit var tintedBackground: View
    private lateinit var mainContent: ViewGroup
    private lateinit var popupView: View
    private lateinit var popupContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paymentpin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tintedBackground = findViewById(R.id.tinted_background)
        mainContent = findViewById(R.id.main)
        popupContainer = findViewById(R.id.popup_container)

        val pinEditTexts = listOf(
            findViewById<EditText>(R.id.pin_ip_field1),
            findViewById<EditText>(R.id.pin_ip_field2),
            findViewById<EditText>(R.id.pin_ip_field3),
            findViewById<EditText>(R.id.pin_ip_field4)
        )

        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            // Check if the current text and the new input form more than one number
            val currentText = dest.toString()
            val newText = currentText.substring(0, dstart) + source + currentText.substring(dend)
            if (newText.length > 1 || !newText.matches(Regex("^[0-9]$"))) {
                ""  // Disallow input if it results in more than one number
            } else {
                null  // Allow input
            }
        }

        pinEditTexts.forEach { editText ->
            editText.filters = arrayOf(inputFilter)
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateDrawableRight(editText, s)
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun updateDrawableRight(editText: EditText, s: CharSequence?) {
        val imageView = when (editText.id) {
            R.id.pin_ip_field1 -> findViewById<ImageView>(R.id.pin_icon1)
            R.id.pin_ip_field2 -> findViewById<ImageView>(R.id.pin_icon2)
            R.id.pin_ip_field3 -> findViewById<ImageView>(R.id.pin_icon3)
            R.id.pin_ip_field4 -> findViewById<ImageView>(R.id.pin_icon4)
            else -> null
        }

        imageView?.let {
            if (s.isNullOrEmpty()) {
                it.visibility = View.VISIBLE
                editText.setBackgroundResource(R.drawable.otp_empty_ip_field)
            } else {
                editText.setBackgroundResource(R.drawable.otp_filled_ip_field)
                it.visibility = View.GONE
            }
        }
    }

    private fun showPopup() {
        // Inflate popup layout
        popupView = LayoutInflater.from(this).inflate(R.layout.payment_success, mainContent, false)

        tintedBackground.translationZ = 1f
        popupContainer.translationZ = 2f
        // Add the popup to the main content
        popupContainer.addView(popupView)

        // Animate the tinted background
        tintedBackground.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null)
        }

        // Animate the popup
        popupView.apply {
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .setListener(null)
        }
        mainContent.clearFocus()
        popupContainer.visibility = View.VISIBLE
    }

//    private fun hidePopup() {
//        // Animate the tinted background
//        tintedBackground.animate()
//            .alpha(0f)
//            .setDuration(300)
//            .setListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    tintedBackground.visibility = View.GONE
//                }
//            })
//
//        // Animate the popup
//        popupView.animate()
//            .alpha(0f)
//            .scaleX(0.8f)
//            .scaleY(0.8f)
//            .setDuration(300)
//            .setListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    popupContainer.removeView(popupView)
//                    popupContainer.visibility = View.GONE
//                }
//            })
//    }

    fun goToPaymentSuccessActivity(view: View) {
        val ipField1 = findViewById<EditText>(R.id.pin_ip_field1).text.toString()
        val ipField2 = findViewById<EditText>(R.id.pin_ip_field2).text.toString()
        val ipField3 = findViewById<EditText>(R.id.pin_ip_field3).text.toString()
        val ipField4 = findViewById<EditText>(R.id.pin_ip_field4).text.toString()

        if (ipField1.length > 0 && ipField2.length > 0 && ipField3.length > 0 && ipField4.length > 0) {
            empty = false
        } else {
            empty = true
        }

        if (empty) {
            Toast.makeText(this, "Enter whole PIN", Toast.LENGTH_SHORT).show()
        }

        if (!empty) {
            showPopup()
//            mainBG.setBackgroundResource(R.drawable.tinted_bg)
        }
    }

    fun goToCoursePaymentActivity(view: View) {
        val intent = Intent(this, CoursePaymentActivity::class.java)
        startActivity(intent)
        finish()
    }
}