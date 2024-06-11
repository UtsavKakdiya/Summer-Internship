package com.example.summer_internship

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val openButton: ImageButton = findViewById(R.id.search_filter)
        openButton.setOnClickListener {
            val bottomSheet = MyBottomSheetDialogFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        val button1: ImageButton = findViewById(R.id.ProgressBarBtn1)
        val button2: ImageButton = findViewById(R.id.ProgressBarBtn2)
        val button3: ImageButton = findViewById(R.id.ProgressBarBtn3)
        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.ProgressBarBtn1 -> {
                    button1.setImageResource(R.drawable.rect_progressbar)
                    button2.setImageResource(R.drawable.circle_progressbar)
                    button3.setImageResource(R.drawable.circle_progressbar)
                }
                R.id.ProgressBarBtn2 -> {
                    button2.setImageResource(R.drawable.rect_progressbar)
                    button1.setImageResource(R.drawable.circle_progressbar)
                    button3.setImageResource(R.drawable.circle_progressbar)
                }
                R.id.ProgressBarBtn3 -> {
                    button3.setImageResource(R.drawable.rect_progressbar)
                    button2.setImageResource(R.drawable.circle_progressbar)
                    button1.setImageResource(R.drawable.circle_progressbar)
                }
            }
        }
        button1.setOnClickListener(clickListener)
        button2.setOnClickListener(clickListener)
        button3.setOnClickListener(clickListener)

        val btn1: Button = findViewById(R.id.tab_home)
        val btn2: Button = findViewById(R.id.tab_course)
        val btn3: Button = findViewById(R.id.tab_bookmark)
        val btn4: Button = findViewById(R.id.tab_notification)
        val btn5: Button = findViewById(R.id.tab_profile)
        val frameLayout: FrameLayout = findViewById(R.id.tabFrameLayout)

        val tabClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.tab_home -> {
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

    fun goToPopularCourseActivity(view: View) {
        val intent = Intent(this, PopularCourseActivity::class.java)
        startActivity(intent)
    }

    fun goToRecentSearchActivity(view: View) {
        val intent = Intent(this, RecentSearchActivity::class.java)
        startActivity(intent)
    }

    fun goToInboxActivity() {
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToMyCourseActivity() {
        val intent = Intent(this, MyCourses::class.java)
        startActivity(intent)
        finish()
    }

    fun goToBookMarkActivity() {
        val intent = Intent(this, BookMarkActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}