package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LanguageActivity : AppCompatActivity() {

    var btn1check = true
    var btn2check = false
    var btn3check = false
    var btn4check = false
    var btn5check = false
    var btn6check = false
    var btn7check = false
    var btn8check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_language)
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

        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.toggleBtn1 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, true, false, false, false, false, false, false, false)
                }
                R.id.toggleBtn2 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, true, false, false, false, false, false, false)
                }
                R.id.toggleBtn3 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, true, false, false, false, false, false)
                }
                R.id.toggleBtn4 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, false, true, false, false, false, false)
                }
                R.id.toggleBtn5 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, false, false, true, false, false, false)
                }
                R.id.toggleBtn6 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, false, false, false, true, false, false)
                }
                R.id.toggleBtn7 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, false, false, false, false, true, false)
                }
                R.id.toggleBtn8 -> {
                    handlePaymentButtonClick(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, false, false, false, false, false, false, false, true)
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
    }

    private fun handlePaymentButtonClick(btn1: ImageButton, btn2: ImageButton, btn3: ImageButton, btn4: ImageButton, btn5: ImageButton, btn6: ImageButton, btn7: ImageButton, btn8: ImageButton, check1: Boolean, check2: Boolean, check3: Boolean, check4: Boolean, check5: Boolean, check6: Boolean, check7: Boolean, check8: Boolean) {
        btn1check = check1
        btn2check = check2
        btn3check = check3
        btn4check = check4
        btn5check = check5
        btn6check = check6
        btn7check = check7
        btn8check = check8

        btn1.setImageResource(if (btn1check) R.drawable.round_selected else R.drawable.round_unselected)
        btn2.setImageResource(if (btn2check) R.drawable.round_selected else R.drawable.round_unselected)
        btn3.setImageResource(if (btn3check) R.drawable.round_selected else R.drawable.round_unselected)
        btn4.setImageResource(if (btn4check) R.drawable.round_selected else R.drawable.round_unselected)
        btn5.setImageResource(if (btn5check) R.drawable.round_selected else R.drawable.round_unselected)
        btn6.setImageResource(if (btn6check) R.drawable.round_selected else R.drawable.round_unselected)
        btn7.setImageResource(if (btn7check) R.drawable.round_selected else R.drawable.round_unselected)
        btn8.setImageResource(if (btn8check) R.drawable.round_selected else R.drawable.round_unselected)
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}