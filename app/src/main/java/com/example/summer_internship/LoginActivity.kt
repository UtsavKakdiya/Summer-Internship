package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    var isChecked = true
    var valid = false
    var empty = true

    fun goToMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun goToSignUpActivity(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun goToForgotPasswordActivity(view: View) {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    fun goToHomeActivity(view: View) {

        checkPasswordRegEx()

        val userName = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        if (userName.length > 0 && password.length > 0) {
            empty = false
        }
        else {
            empty = true
        }

        if(empty) {
            Toast.makeText(this, "Enter every details", Toast.LENGTH_SHORT).show()
        }

        if(valid && !empty) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun onShowPasswordClicked(view: View) {
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        if (isChecked) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            passwordEditText.setTransformationMethod(null); // To show the password characters
            passwordEditText.setTextAppearance(R.style.EditTextPasswordVisible);
            isChecked = false
        } else {
            passwordEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordEditText.setTextAppearance(R.style.EditTextPasswordHidden)
            isChecked = true
        }
        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.text.length)
    }

    fun checkPasswordRegEx() {
        val passwordPattern = "^(?=.{6,}\$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*\$"
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val password = passwordEditText.text.toString()
        if (!password.matches(passwordPattern.toRegex())) {
            passwordEditText.error = "Password must be more than 6 characters long and contains at least one digit, one uppercase letter, one lowercase, one special character,"
            valid = false
        }
        else {
            valid = true
        }
    }
}