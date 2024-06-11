package com.example.summer_internship

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookMarkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bookmark)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1: Button = findViewById(R.id.tab_home)
        val btn2: Button = findViewById(R.id.tab_course)
        val btn3: Button = findViewById(R.id.tab_bookmark)
        val btn4: Button = findViewById(R.id.tab_notification)
        val btn5: Button = findViewById(R.id.tab_profile)
        val frameLayout: FrameLayout = findViewById(R.id.tabFrameLayout)

        val tabClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.tab_home -> {
                    goToHomeActivity()
                    frameLayout.setBackgroundResource(R.drawable.selected_home_tab)
                }
                R.id.tab_course -> {
                    goToMyCourseActivity()
                    frameLayout.setBackgroundResource(R.drawable.selected_course_tab)
                }
                R.id.tab_bookmark -> {
                    frameLayout.setBackgroundResource(R.drawable.selected_bookmark_tab)
                }
                R.id.tab_notification -> {
                    goToInboxActivity()
                    frameLayout.setBackgroundResource(R.drawable.selected_chat_tab)
                }
                R.id.tab_profile -> {
                    goToProfileActivity()
                    frameLayout.setBackgroundResource(R.drawable.selected_profile_tab)
                }
            }
        }
        btn1.setOnClickListener(tabClickListener)
        btn2.setOnClickListener(tabClickListener)
        btn3.setOnClickListener(tabClickListener)
        btn4.setOnClickListener(tabClickListener)
        btn5.setOnClickListener(tabClickListener)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                finishAffinity()
            }
            .setNegativeButton("No", null)
            .show()
    }

    fun goToMyCourseActivity() {
        val intent = Intent(this, MyCourses::class.java)
        startActivity(intent)
        finish()
    }

    fun goToInboxActivity() {
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}