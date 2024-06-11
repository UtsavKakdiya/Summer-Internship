package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgotpassword)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    var valid = false
    var empty = true

    fun goToLoginActivity(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToVerificationActivity(view: View) {

        checkEmailRegEx()

        val email = findViewById<EditText>(R.id.editTextEmail).text.toString()

        if (email.length > 0) {
            empty = false
        }
        else {
            empty = true
        }

        if(valid && !empty) {
            val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)
        }

        if(empty) {
            Toast.makeText(this, "Enter every details", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkEmailRegEx() {
        val emailPattern = "[a-z0-9._-]+@[a-z]+\\.+[a-z]+"
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val email = emailEditText.text.toString()
        if (!email.matches(emailPattern.toRegex())) {
            emailEditText.error = "Invalid email address"
            valid = false
        }
        else {
            valid = true
        }
    }

}