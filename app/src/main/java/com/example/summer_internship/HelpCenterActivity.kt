package com.example.summer_internship

import HelpCenter_ViewPageAdapter
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class HelpCenterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_helpcenter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var textFAQ = findViewById<TextView>(R.id.textViewFAQ)
        var textContactUS = findViewById<TextView>(R.id.textViewContactUS)
        var progressBar = findViewById<ImageView>(R.id.progressBar)


        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = HelpCenter_ViewPageAdapter(this)

        textFAQ.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 0
        }

        textContactUS.setOnClickListener {
            // Navigate to the second fragment (index 1)
            viewPager.currentItem = 1
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    textFAQ.setTextColor(resources.getColor(R.color.primary))
                    textContactUS.setTextColor(resources.getColor(R.color.secondary))
                    progressBar.setImageResource(R.drawable.course_ongoing)
                }
                else if (position == 1) {
                    textFAQ.setTextColor(resources.getColor(R.color.secondary))
                    textContactUS.setTextColor(resources.getColor(R.color.primary))
                    progressBar.setImageResource(R.drawable.course_completed)
                }
            }
        })
    }

    fun clickListener(view: View) {
        val expandableLayout1 : LinearLayout = findViewById(R.id.expandableLayout1)
        val toggleButton1 : ImageButton = findViewById(R.id.show_answer1)

        val expandableLayout2 : LinearLayout = findViewById(R.id.expandableLayout2)
        val toggleButton2 : ImageButton = findViewById(R.id.show_answer2)

        val expandableLayout3 : LinearLayout = findViewById(R.id.expandableLayout3)
        val toggleButton3 : ImageButton = findViewById(R.id.show_answer3)

        val expandableLayout4 : LinearLayout = findViewById(R.id.expandableLayout4)
        val toggleButton4 : ImageButton = findViewById(R.id.show_answer4)

        val expandableLayout5 : LinearLayout = findViewById(R.id.expandableLayout5)
        val toggleButton5 : ImageButton = findViewById(R.id.show_answer5)

        when (view.id) {
            R.id.show_answer1 -> {
                if (expandableLayout1.visibility == View.GONE) {
                    expand(expandableLayout1)
                    toggleButton1.setImageResource(R.drawable.arrow_up)
                }
                else {
                    collapse(expandableLayout1)
                    toggleButton1.setImageResource(R.drawable.arrow_down)
                }
            }
            R.id.show_answer2 -> {
                if (expandableLayout2.visibility == View.GONE) {
                    expand(expandableLayout2)
                    toggleButton2.setImageResource(R.drawable.arrow_up)
                }
                else {
                    collapse(expandableLayout2)
                    toggleButton2.setImageResource(R.drawable.arrow_down)
                }
            }
            R.id.show_answer3 -> {
                if (expandableLayout3.visibility == View.GONE) {
                    expand(expandableLayout3)
                    toggleButton3.setImageResource(R.drawable.arrow_up)
                }
                else {
                    collapse(expandableLayout3)
                    toggleButton3.setImageResource(R.drawable.arrow_down)
                }
            }
            R.id.show_answer4 -> {
                if (expandableLayout4.visibility == View.GONE) {
                    expand(expandableLayout4)
                    toggleButton4.setImageResource(R.drawable.arrow_up)
                }
                else {
                    collapse(expandableLayout4)
                    toggleButton4.setImageResource(R.drawable.arrow_down)
                }
            }
            R.id.show_answer5 -> {
                if (expandableLayout5.visibility == View.GONE) {
                    expand(expandableLayout5)
                    toggleButton5.setImageResource(R.drawable.arrow_up)
                }
                else {
                    collapse(expandableLayout5)
                    toggleButton5.setImageResource(R.drawable.arrow_down)
                }
            }
        }
    }

    private fun expand(view: View) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight

        // Older versions of Android (pre API 21) cancel animations for views with a height of 0.
        view.layoutParams.height = 1
        view.visibility = View.VISIBLE
        view.animate()
            .translationY(0f)
            .setDuration(100)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setUpdateListener { animation ->
                val value = animation.animatedFraction
                view.layoutParams.height = if (value == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * value).toInt()
                view.requestLayout()
            }
            .start()
    }

    private fun collapse(view: View) {
        val initialHeight = view.measuredHeight

        view.animate()
            .translationY(view.height.toFloat())
            .setDuration(100)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setUpdateListener { animation ->
                val value = animation.animatedFraction
                if (value == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height = initialHeight - (initialHeight * value).toInt()
                    view.requestLayout()
                }
            }
            .start()
    }

    fun goToProfileActivity(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}