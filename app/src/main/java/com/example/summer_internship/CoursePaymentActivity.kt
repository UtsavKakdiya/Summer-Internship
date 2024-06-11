package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CoursePaymentActivity : AppCompatActivity() {

    var btn1check = false
    var btn2check = false
    var btn3check = false
    var btn4check = false
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var newLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coursepayment)

        try {
            rootLayout = findViewById(R.id.main)
            val layoutInflater = LayoutInflater.from(this)
            newLayout = layoutInflater.inflate(R.layout.add_new_card, rootLayout, false)

            ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val btn1: ImageButton = findViewById(R.id.paypal)
            val btn2: ImageButton = findViewById(R.id.google_pay)
            val btn3: ImageButton = findViewById(R.id.apple_pay)
            val btn4: ImageButton = findViewById(R.id.master_card)

            val clickListener = View.OnClickListener { v ->
                when (v.id) {
                    R.id.paypal -> {
                        handlePaymentButtonClick(btn1, btn2, btn3, btn4, true, false, false, false)
                    }
                    R.id.google_pay -> {
                        handlePaymentButtonClick(btn1, btn2, btn3, btn4, false, true, false, false)
                    }
                    R.id.apple_pay -> {
                        handlePaymentButtonClick(btn1, btn2, btn3, btn4, false, false, true, false)
                    }
                    R.id.master_card -> {
                        handlePaymentButtonClick(btn1, btn2, btn3, btn4, false, false, false, true)
                        rootLayout.addView(newLayout)
                    }
                }
            }
            btn1.setOnClickListener(clickListener)
            btn2.setOnClickListener(clickListener)
            btn3.setOnClickListener(clickListener)
            btn4.setOnClickListener(clickListener)

        } catch (e: Exception) {
            Log.e("CoursePaymentActivity", "Error in onCreate: ", e)
        }
    }

    private fun handlePaymentButtonClick(btn1: ImageButton, btn2: ImageButton, btn3: ImageButton, btn4: ImageButton, check1: Boolean, check2: Boolean, check3: Boolean, check4: Boolean) {
        btn1check = check1
        btn2check = check2
        btn3check = check3
        btn4check = check4

        btn1.setImageResource(if (btn1check) R.drawable.round_selected else R.drawable.round_unselected)
        btn2.setImageResource(if (btn2check) R.drawable.round_selected else R.drawable.round_unselected)
        btn3.setImageResource(if (btn3check) R.drawable.round_selected else R.drawable.round_unselected)
        btn4.setImageResource(if (btn4check) R.drawable.round_selected else R.drawable.round_unselected)

        rootLayout.removeView(newLayout)
    }

    fun goToMainCourseActivity(view: View) {
        val intent = Intent(this, MainCourseActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToAddNewCardActivity(view: View) {
        val intent = Intent(this, AddNewCardActivity::class.java)
        startActivity(intent)
    }

    fun goToPaymentPinActivity(view: View) {
        if (!btn1check && !btn2check && !btn3check && !btn4check) {
            Toast.makeText(this, "Choose anyone payment method", Toast.LENGTH_SHORT).show()
        }
        else {
            val intent = Intent(this, PaymentPinActivity::class.java)
            startActivity(intent)
        }
    }
}
