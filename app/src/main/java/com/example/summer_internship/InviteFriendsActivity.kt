package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InviteFriendsActivity : AppCompatActivity() {

    var btn1check = false
    var btn2check = true
    var btn3check = false
    var btn4check = true
    var btn5check = false
    var btn6check = false
    var btn7check = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_invitefriends)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1: ImageButton = findViewById(R.id.inviteBtn1)
        val btn2: ImageButton = findViewById(R.id.inviteBtn2)
        val btn3: ImageButton = findViewById(R.id.inviteBtn3)
        val btn4: ImageButton = findViewById(R.id.inviteBtn4)
        val btn5: ImageButton = findViewById(R.id.inviteBtn5)
        val btn6: ImageButton = findViewById(R.id.inviteBtn6)
        val btn7: ImageButton = findViewById(R.id.inviteBtn7)

        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.inviteBtn1 -> {
                    if (btn1check) {
                        btn1check = false
                    } else {
                        btn1check = true
                    }
                    handleToggleBtnClick(btn1, btn1check)
                }

                R.id.inviteBtn2 -> {
                    if (btn2check) {
                        btn2check = false
                    } else {
                        btn2check = true
                    }
                    handleToggleBtnClick(btn2, btn2check)
                }

                R.id.inviteBtn3 -> {
                    if (btn3check) {
                        btn3check = false
                    } else {
                        btn3check = true
                    }
                    handleToggleBtnClick(btn3, btn3check)
                }

                R.id.inviteBtn4 -> {
                    if (btn4check) {
                        btn4check = false
                    } else {
                        btn4check = true
                    }
                    handleToggleBtnClick(btn4, btn4check)
                }

                R.id.inviteBtn5 -> {
                    if (btn5check) {
                        btn5check = false
                    } else {
                        btn5check = true
                    }
                    handleToggleBtnClick(btn5, btn5check)
                }

                R.id.inviteBtn6 -> {
                    if (btn6check) {
                        btn6check = false
                    } else {
                        btn6check = true
                    }
                    handleToggleBtnClick(btn6, btn6check)
                }

                R.id.inviteBtn7 -> {
                    if (btn7check) {
                        btn7check = false
                    } else {
                        btn7check = true
                    }
                    handleToggleBtnClick(btn7, btn7check)
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
    }

    private fun handleToggleBtnClick(btn: ImageButton, check: Boolean) {

        btn.setImageResource(if (check) R.drawable.icon_invited else R.drawable.icon_invite)
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}