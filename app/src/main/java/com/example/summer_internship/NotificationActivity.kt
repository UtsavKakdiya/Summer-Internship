package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NotificationActivity : AppCompatActivity() {

    var btn1check = true
    var btn2check = false
    var btn3check = false
    var btn4check = true
    var btn5check = false
    var btn6check = true
    var btn7check = true
    var btn8check = false
    var btn9check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1: ImageButton = findViewById(R.id.toggleBtn1)
        val btn2: ImageButton = findViewById(R.id.toggleBtn2)
        val btn3: ImageButton = findViewById(R.id.toggleBtn3)
        val btn4: ImageButton = findViewById(R.id.toggleBtn4)
        val btn5: ImageButton = findViewById(R.id.toggleBtn5)
        val btn6: ImageButton = findViewById(R.id.toggleBtn6)
        val btn7: ImageButton = findViewById(R.id.toggleBtn7)
        val btn8: ImageButton = findViewById(R.id.toggleBtn8)
        val btn9: ImageButton = findViewById(R.id.toggleBtn9)

        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.toggleBtn1 -> {
                    if (btn1check) {
                        btn1check = false
                    }
                    else {
                        btn1check = true
                    }
                    handleToggleBtnClick(btn1,btn1check)
                }
                R.id.toggleBtn2 -> {
                    if (btn2check) {
                        btn2check = false
                    }
                    else {
                        btn2check = true
                    }
                    handleToggleBtnClick(btn2,btn2check)
                }
                R.id.toggleBtn3 -> {
                    if (btn3check) {
                        btn3check = false
                    }
                    else {
                        btn3check = true
                    }
                    handleToggleBtnClick(btn3,btn3check)
                }
                R.id.toggleBtn4 -> {
                    if (btn4check) {
                        btn4check = false
                    }
                    else {
                        btn4check = true
                    }
                    handleToggleBtnClick(btn4,btn4check)
                }
                R.id.toggleBtn5 -> {
                    if (btn5check) {
                        btn5check = false
                    }
                    else {
                        btn5check = true
                    }
                    handleToggleBtnClick(btn5,btn5check)
                }
                R.id.toggleBtn6 -> {
                    if (btn6check) {
                        btn6check = false
                    }
                    else {
                        btn6check = true
                    }
                    handleToggleBtnClick(btn6,btn6check)
                }
                R.id.toggleBtn7 -> {
                    if (btn7check) {
                        btn7check = false
                    }
                    else {
                        btn7check = true
                    }
                    handleToggleBtnClick(btn7,btn7check)
                }
                R.id.toggleBtn8 -> {
                    if (btn8check) {
                        btn8check = false
                    }
                    else {
                        btn8check = true
                    }
                    handleToggleBtnClick(btn8,btn8check)
                }
                R.id.toggleBtn9 -> {
                    if (btn9check) {
                        btn9check = false
                    }
                    else {
                        btn9check = true
                    }
                    handleToggleBtnClick(btn9,btn9check)
                }
            }
        }
        btn1.setOnClickListener(clickListener)
        btn2.setOnClickListener(clickListener)
        btn3.setOnClickListener(clickListener)
        btn4.setOnClickListener(clickListener)
        btn5.setOnClickListener(clickListener)
        btn6.setOnClickListener(clickListener)
        btn7.setOnClickListener(clickListener)
        btn8.setOnClickListener(clickListener)
        btn9.setOnClickListener(clickListener)
    }

    private fun handleToggleBtnClick(btn: ImageButton, check: Boolean) {

        btn.setImageResource(if (check) R.drawable.toggle_on else R.drawable.toggle_off)
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}