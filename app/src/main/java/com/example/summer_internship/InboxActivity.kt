package com.example.summer_internship

import Inbox_ViewPageAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class InboxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inbox)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textChats = findViewById<TextView>(R.id.textViewChats)
        var textCalls = findViewById<TextView>(R.id.textViewCalls)
        var progressBar = findViewById<ImageView>(R.id.progressBar)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = Inbox_ViewPageAdapter(this)

        textChats.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 0
        }

        textCalls.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 1
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    textChats.setTextColor(resources.getColor(R.color.primary))
                    textCalls.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_ongoing)
                }
                else if (position == 1) {
                    textChats.setTextColor(resources.getColor(R.color.secondary))
                    textCalls.setTextColor(resources.getColor(R.color.primary))
                    progressBar.setImageResource(R.drawable.course_completed)
                }
            }
        })

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
                    goToBookMarkActivity()
                    frameLayout.setBackgroundResource(R.drawable.selected_bookmark_tab)
                }
                R.id.tab_notification -> {
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

    fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToBookMarkActivity() {
        val intent = Intent(this, BookMarkActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToChatActivity(view: View) {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }

    fun goToCallingActivity(view: View) {
        val intent = Intent(this, CallingActivity::class.java)
        startActivity(intent)
    }

    fun goToProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}