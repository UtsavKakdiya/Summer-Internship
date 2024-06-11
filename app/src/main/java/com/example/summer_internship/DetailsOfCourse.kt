package com.example.summer_internship

import DetailsOfCourse_ViewPagerAdapter
import android.content.Intent
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

class DetailsOfCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailsofcourse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textLessons = findViewById<TextView>(R.id.textViewLessons)
        var textCertifications = findViewById<TextView>(R.id.textViewCertification)
        var progressBar = findViewById<ImageView>(R.id.progressBar)
        var floatingBtn = findViewById<ImageButton>(R.id.floatingBtn)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = DetailsOfCourse_ViewPagerAdapter(this)

        textLessons.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 0
        }

        textCertifications.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 1

        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    textLessons.setTextColor(resources.getColor(R.color.primary))
                    textCertifications.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_ongoing)
                    floatingBtn.setImageResource(R.drawable.start_course_again)
                }
                else if (position == 1) {
                    textLessons.setTextColor(resources.getColor(R.color.secondary))
                    textCertifications.setTextColor(resources.getColor(R.color.primary))
                    progressBar.setImageResource(R.drawable.course_completed)
                    floatingBtn.setImageResource(R.drawable.download_certificate)
                }
            }
        })

    }

    fun goToMyCourseActivity(view: View) {
        val intent = Intent(this, MyCourses::class.java)
        startActivity(intent)
        finish()
    }

    fun goToVideoPlayerActivity(view: View) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        startActivity(intent)
    }
}