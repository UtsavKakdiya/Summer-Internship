package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class VerificationActivity : AppCompatActivity() {

    var empty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        val otpEditTexts = listOf(
            findViewById<EditText>(R.id.pin_ip_field1),
            findViewById<EditText>(R.id.pin_ip_field2),
            findViewById<EditText>(R.id.pin_ip_field3),
            findViewById<EditText>(R.id.pin_ip_field4),
            findViewById<EditText>(R.id.otp_ip_field5),
            findViewById<EditText>(R.id.otp_ip_field6)
        )

        otpEditTexts.forEach { editText ->
            editText.filters = arrayOf(inputFilter)
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
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
            R.id.otp_ip_field5 -> findViewById<ImageView>(R.id.otp_icon5)
            R.id.otp_ip_field6 -> findViewById<ImageView>(R.id.otp_icon6)
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

    fun goToForgotPasswordActivity(view: View) {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    fun goToResetPasswordActivity(view: View) {

        val ipField1 = findViewById<EditText>(R.id.pin_ip_field1).text.toString()
        val ipField2 = findViewById<EditText>(R.id.pin_ip_field2).text.toString()
        val ipField3 = findViewById<EditText>(R.id.pin_ip_field3).text.toString()
        val ipField4 = findViewById<EditText>(R.id.pin_ip_field4).text.toString()
        val ipField5 = findViewById<EditText>(R.id.otp_ip_field5).text.toString()
        val ipField6 = findViewById<EditText>(R.id.otp_ip_field6).text.toString()
        if (ipField1.length > 0 && ipField2.length > 0 && ipField3.length > 0 && ipField4.length > 0 && ipField5.length > 0 && ipField6.length > 0) {
            empty = false
        }
        else {
            empty = true
        }

        if(!empty) {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        if(empty) {
            Toast.makeText(this, "Enter whole OTP", Toast.LENGTH_SHORT).show()
        }
    }

}