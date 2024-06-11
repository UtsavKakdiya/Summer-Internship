package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChatActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var inputEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun goToInboxActivity(view: View) {
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToCallingActivity(view: View) {
        val intent = Intent(this, CallingActivity::class.java)
        startActivity(intent)
    }
}