package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChangePasswordActivity : AppCompatActivity() {

    var isChecked = true
    var valid = false
    var empty = true
    var match = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_changepassword)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun goToSecurityActivity(view: View) {
        val intent = Intent(this, SecurityActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onShowPasswordClicked(view: View) {
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword1)
        if (isChecked) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            passwordEditText.setTransformationMethod(null); // To show the password characters
            passwordEditText.setTextAppearance(R.style.EditTextPasswordVisible);
            isChecked = false
        }
        else {
            passwordEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordEditText.setTextAppearance(R.style.EditTextPasswordHidden)
            isChecked = true
        }
        // Move cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.text.length)
    }

    fun goToProfileActivity(view: View) {

        checkPasswordRegEx()

        val password = findViewById<EditText>(R.id.editTextPassword1).text.toString()
        val confPassword = findViewById<EditText>(R.id.editTextPassword2).text.toString()
        if (password.length > 0 && confPassword.length > 0) {
            empty = false
        }
        else {
            empty = true
        }

        if (password == confPassword) {
            match = true
        }
        else {
            match = false
        }

        if(valid && !empty && match) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(!match) {
            Toast.makeText(this, "Both passwords must be same", Toast.LENGTH_SHORT).show()
        }

        if(empty) {
            Toast.makeText(this, "Enter every details", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkPasswordRegEx() {
        val passwordPattern = "^(?=.{6,}\$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*\$"
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword1)
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