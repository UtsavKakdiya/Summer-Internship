package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecurityActivity : AppCompatActivity() {

    var btn1check = true
    var btn2check = false
    var btn3check = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_security)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1: ImageButton = findViewById(R.id.toggleBtn1)
        val btn2: ImageButton = findViewById(R.id.toggleBtn2)
        val btn3: ImageButton = findViewById(R.id.toggleBtn3)

        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.toggleBtn1 -> {
                    if (btn1check) {
                        btn1check = false
                    } else {
                        btn1check = true
                    }
                    handleToggleBtnClick(btn1, btn1check)
                }

                R.id.toggleBtn2 -> {
                    if (btn2check) {
                        btn2check = false
                    } else {
                        btn2check = true
                    }
                    handleToggleBtnClick(btn2, btn2check)
                }

                R.id.toggleBtn3 -> {
                    if (btn3check) {
                        btn3check = false
                    } else {
                        btn3check = true
                    }
                    handleToggleBtnClick(btn3, btn3check)
                }
            }
        }

        btn1.setOnClickListener(clickListener)
        btn2.setOnClickListener(clickListener)
        btn3.setOnClickListener(clickListener)
    }

    private fun handleToggleBtnClick(btn: ImageButton, check: Boolean) {

        btn.setImageResource(if (check) R.drawable.toggle_on else R.drawable.toggle_off)
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToChangePasswordActivity(view: View) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }
}