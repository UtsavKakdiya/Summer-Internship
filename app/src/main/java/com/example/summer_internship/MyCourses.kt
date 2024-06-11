package com.example.summer_internship

import MyCourse_ViewPageAdapter
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MyCourses : AppCompatActivity() {

    private lateinit var tintedBackground: View
    private lateinit var mainContent: ViewGroup
    private lateinit var popupView: View
    private lateinit var popupContainer: ViewGroup
    var star1Clicked = false
    var star2Clicked = false
    var star3Clicked = false
    var star4Clicked = false
    var star5Clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mycourses)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textOngoing = findViewById<TextView>(R.id.textViewOngoing)
        var textCompleted = findViewById<TextView>(R.id.textViewCompleted)
        var progressBar = findViewById<ImageView>(R.id.progressBar)
        tintedBackground = findViewById(R.id.tinted_background)
        mainContent = findViewById(R.id.main)
        popupContainer = findViewById(R.id.popup_container)
//        val icon_course1 = findViewById<ImageButton>(R.id.icon_course1)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = MyCourse_ViewPageAdapter(this)

        textOngoing.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 0
        }

        textCompleted.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 1
        }

//        icon_course1.setOnClickListener {
//            showPopup()
//        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    textOngoing.setTextColor(resources.getColor(R.color.primary))
                    textCompleted.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_ongoing)
                }
                else if (position == 1) {
                    textOngoing.setTextColor(resources.getColor(R.color.secondary))
                    textCompleted.setTextColor(resources.getColor(R.color.primary))
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

    fun showPopup(view: View) {
        // Inflate popup layout
        popupView = LayoutInflater.from(this).inflate(R.layout.course_completed, mainContent, false)

        tintedBackground.translationZ = 1f
        popupContainer.translationZ = 2f
        // Add the popup to the main content
        popupContainer.addView(popupView)

        // Animate the tinted background
        tintedBackground.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(null)
        }

        // Animate the popup
        popupView.apply {
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .setListener(null)
        }
        mainContent.clearFocus()
        popupContainer.visibility = View.VISIBLE
    }

    fun hidePopup(view: View) {
    // Animate the tinted background
        if (star1Clicked || star2Clicked || star3Clicked || star4Clicked || star5Clicked) {

            tintedBackground.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        tintedBackground.visibility = View.GONE
                    }
                })

            // Animate the popup
            popupView.animate()
                .alpha(0f)
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        popupContainer.removeView(popupView)
                        popupContainer.visibility = View.GONE
                    }
                })

        }
        else {
            Toast.makeText(this, "Enter your Review first", Toast.LENGTH_SHORT).show()
        }
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

    fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToDetailsOfCourseActivity(view: View) {
        val intent = Intent(this, DetailsOfCourse::class.java)
        startActivity(intent)
    }

    fun goToBookMarkActivity() {
        val intent = Intent(this, BookMarkActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToInboxActivity() {
        val intent = Intent(this, InboxActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun show1Star(view: View) {
        star1Clicked = true
        var review_star1 = findViewById<ImageButton>(R.id.review_star1)
        var review_star2 = findViewById<ImageButton>(R.id.review_star2)
        var review_star3 = findViewById<ImageButton>(R.id.review_star3)
        var review_star4 = findViewById<ImageButton>(R.id.review_star4)
        var review_star5 = findViewById<ImageButton>(R.id.review_star5)

        review_star1.setImageResource(R.drawable.star_selected)
        review_star2.setImageResource(R.drawable.star_unselected)
        review_star3.setImageResource(R.drawable.star_unselected)
        review_star4.setImageResource(R.drawable.star_unselected)
        review_star5.setImageResource(R.drawable.star_unselected)
    }

    fun show2Star(view: View) {
        star2Clicked = true
        var review_star1 = findViewById<ImageButton>(R.id.review_star1)
        var review_star2 = findViewById<ImageButton>(R.id.review_star2)
        var review_star3 = findViewById<ImageButton>(R.id.review_star3)
        var review_star4 = findViewById<ImageButton>(R.id.review_star4)
        var review_star5 = findViewById<ImageButton>(R.id.review_star5)

        review_star1.setImageResource(R.drawable.star_selected)
        review_star2.setImageResource(R.drawable.star_selected)
        review_star3.setImageResource(R.drawable.star_unselected)
        review_star4.setImageResource(R.drawable.star_unselected)
        review_star5.setImageResource(R.drawable.star_unselected)
    }

    fun show3Star(view: View) {
        star3Clicked = true
        var review_star1 = findViewById<ImageButton>(R.id.review_star1)
        var review_star2 = findViewById<ImageButton>(R.id.review_star2)
        var review_star3 = findViewById<ImageButton>(R.id.review_star3)
        var review_star4 = findViewById<ImageButton>(R.id.review_star4)
        var review_star5 = findViewById<ImageButton>(R.id.review_star5)

        review_star1.setImageResource(R.drawable.star_selected)
        review_star2.setImageResource(R.drawable.star_selected)
        review_star3.setImageResource(R.drawable.star_selected)
        review_star4.setImageResource(R.drawable.star_unselected)
        review_star5.setImageResource(R.drawable.star_unselected)
    }

    fun show4Star(view: View) {
        star4Clicked = true
        var review_star1 = findViewById<ImageButton>(R.id.review_star1)
        var review_star2 = findViewById<ImageButton>(R.id.review_star2)
        var review_star3 = findViewById<ImageButton>(R.id.review_star3)
        var review_star4 = findViewById<ImageButton>(R.id.review_star4)
        var review_star5 = findViewById<ImageButton>(R.id.review_star5)

        review_star1.setImageResource(R.drawable.star_selected)
        review_star2.setImageResource(R.drawable.star_selected)
        review_star3.setImageResource(R.drawable.star_selected)
        review_star4.setImageResource(R.drawable.star_selected)
        review_star5.setImageResource(R.drawable.star_unselected)
    }

    fun show5Star(view: View) {
        star5Clicked = true
        var review_star1 = findViewById<ImageButton>(R.id.review_star1)
        var review_star2 = findViewById<ImageButton>(R.id.review_star2)
        var review_star3 = findViewById<ImageButton>(R.id.review_star3)
        var review_star4 = findViewById<ImageButton>(R.id.review_star4)
        var review_star5 = findViewById<ImageButton>(R.id.review_star5)

        review_star1.setImageResource(R.drawable.star_selected)
        review_star2.setImageResource(R.drawable.star_selected)
        review_star3.setImageResource(R.drawable.star_selected)
        review_star4.setImageResource(R.drawable.star_selected)
        review_star5.setImageResource(R.drawable.star_selected)
    }
}