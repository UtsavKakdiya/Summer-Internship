package com.example.summer_internship

import MainCourse_ViewPagerAdapter
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainCourseActivity : AppCompatActivity() {

    var btn1check = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maincourse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textAbout = findViewById<TextView>(R.id.textViewAbout)
        var textLessons = findViewById<TextView>(R.id.textViewLessons)
        var textReview = findViewById<TextView>(R.id.textViewReview)
        var progressBar = findViewById<ImageView>(R.id.progressBar)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = MainCourse_ViewPagerAdapter(this)

        textAbout.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 0
        }

        textLessons.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 1
        }

        textReview.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 2
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    textAbout.setTextColor(resources.getColor(R.color.primary))
                    textLessons.setTextColor(resources.getColor(R.color.secondary))
                    textReview.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_progress1)
                }
                else if (position == 1) {
                    textAbout.setTextColor(resources.getColor(R.color.secondary))
                    textLessons.setTextColor(resources.getColor(R.color.primary))
                    textReview.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_progress2)
                }
                else if (position == 2) {
                    textAbout.setTextColor(resources.getColor(R.color.secondary))
                    textLessons.setTextColor(resources.getColor(R.color.secondary))
                    textReview.setTextColor(resources.getColor(R.color.primary))
                    progressBar.setImageResource(R.drawable.course_progress3)
                }
            }
        })

        val textViewStrikethrough: TextView = findViewById(R.id.textViewStrikethrough)
        textViewStrikethrough.paintFlags = textViewStrikethrough.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        val btn1: ImageButton = findViewById(R.id.bookmark_1)
        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.bookmark_1 -> {
                    if (!btn1check) {
                        btn1.setImageResource(R.drawable.bookmarked)
                        btn1check = true
                    } else {
                        btn1.setImageResource(R.drawable.bookmark)
                        btn1check = false
                    }
                }
            }
        }
        btn1.setOnClickListener(clickListener)
    }

    fun goToCourseSearchActivity(view: View) {
        val intent = Intent(this, CourseSearchActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToCoursePaymentActivity(view: View) {
        val intent = Intent(this, CoursePaymentActivity::class.java)
        startActivity(intent)
    }

}