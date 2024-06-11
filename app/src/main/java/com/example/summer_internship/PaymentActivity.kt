package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var card1Layout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rootLayout = findViewById(R.id.root_layout)
        card1Layout = findViewById(R.id.layout_card1)
//        val addButton = addLayout.findViewById<ImageButton>(R.id.add_layout_btn)

        findViewById<ImageButton>(R.id.disconnectBtn1).setOnClickListener {
            rootLayout.removeView(card1Layout)
        }
    }

    public fun addLayout(text: String) {
        // Inflate the layout to be added
        val layoutInflater = LayoutInflater.from(this)
        val newLayout = layoutInflater.inflate(R.layout.card_to_add, rootLayout, false)

        // Get references to elements in the newly inflated layout
        val dynamicTextView = newLayout.findViewById<TextView>(R.id.number_card)
        val removeButton = newLayout.findViewById<ImageButton>(R.id.disconnectBtn)

        var temp = text.substring(text.length - 15)
        var tmp = "**** **** **** $temp"

        // Set the text for the TextView
        dynamicTextView.text = tmp

        // Set the remove button click listener to remove this layout
        removeButton.setOnClickListener {
            rootLayout.removeView(newLayout)
        }

        // Add the new layout to the root layout
        rootLayout.addView(newLayout)
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToAddNewCardActivity(view: View) {
        val intent = Intent(this, AddNewCardActivity::class.java)
        startActivity(intent)
    }
}