package com.example.summer_internship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PopularCourseActivity : AppCompatActivity() {

    var btn1check = false
    var btn2check = false
    var btn3check = false
    var btn4check = false
    var btn5check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_popularcourse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1: ImageButton = findViewById(R.id.bookmark_1)
        val btn2: ImageButton = findViewById(R.id.bookmark_2)
        val btn3: ImageButton = findViewById(R.id.bookmark_3)
        val btn4: ImageButton = findViewById(R.id.bookmark_4)
        val btn5: ImageButton = findViewById(R.id.bookmark_5)
        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.bookmark_1 -> {
                    if (!btn1check) {
                        btn1.setImageResource(R.drawable.bookmarked)
                        btn1check = true
                    }
                    else {
                        btn1.setImageResource(R.drawable.bookmark)
                        btn1check = false
                    }
                }
                R.id.bookmark_2 -> {
                    if (!btn2check) {
                        btn2.setImageResource(R.drawable.bookmarked)
                        btn2check = true
                    }
                    else {
                        btn2.setImageResource(R.drawable.bookmark)
                        btn2check = false
                    }
                }
                R.id.bookmark_3 -> {
                    if (!btn3check) {
                        btn3.setImageResource(R.drawable.bookmarked)
                        btn3check = true
                    }
                    else {
                        btn3.setImageResource(R.drawable.bookmark)
                        btn3check = false
                    }
                }
                R.id.bookmark_4 -> {
                    if (!btn4check) {
                        btn4.setImageResource(R.drawable.bookmarked)
                        btn4check = true
                    }
                    else {
                        btn4.setImageResource(R.drawable.bookmark)
                        btn4check = false
                    }
                }
                R.id.bookmark_5 -> {
                    if (!btn5check) {
                        btn5.setImageResource(R.drawable.bookmarked)
                        btn5check = true
                    }
                    else {
                        btn5.setImageResource(R.drawable.bookmark)
                        btn5check = false
                    }
                }
            }
        }
        btn1.setOnClickListener(clickListener)
        btn2.setOnClickListener(clickListener)
        btn3.setOnClickListener(clickListener)
        btn4.setOnClickListener(clickListener)
        btn5.setOnClickListener(clickListener)

    }

    fun goToMainCourseActivity(view: View) {
        val intent = Intent(this, MainCourseActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToHomeActivity(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}