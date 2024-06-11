package com.example.summer_internship

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecentSearchActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var inputEditText: EditText
    private val prefsName = "DynamicLayoutsPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recentsearch)
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

        rootLayout = findViewById(R.id.root_layout)
        inputEditText = findViewById(R.id.editTextSearch)

        inputEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputText = inputEditText.text.toString().trim()
                if (inputText.isNotEmpty()) {

                    val intent = Intent(this, CourseSearchActivity::class.java)
                    startActivity(intent)
                    finish()

                    addLayout(inputText)
                    saveLayoutState(inputText)
                    inputEditText.text.clear()
                }
                true
            } else {
                false
            }
        }

        // Restore previously saved layouts
        restoreLayoutState()
    }

    private fun addLayout(text: String) {
        // Inflate the layout to be added
        val layoutInflater = LayoutInflater.from(this)
        val newLayout = layoutInflater.inflate(R.layout.layout_to_add, rootLayout, false)

        // Get references to elements in the newly inflated layout
        val dynamicTextView = newLayout.findViewById<TextView>(R.id.search_text)
        val removeButton = newLayout.findViewById<ImageButton>(R.id.clear_search)

        // Set the text for the TextView
        dynamicTextView.text = text

        // Set the remove button click listener to remove this layout
        removeButton.setOnClickListener {
            rootLayout.removeView(newLayout)
            removeLayoutState(text)
        }

        // Add the new layout to the root layout
        rootLayout.addView(newLayout)
    }

    private fun saveLayoutState(text: String) {
        val sharedPreferences = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val existingTexts = sharedPreferences.getStringSet("layouts", mutableSetOf()) ?: mutableSetOf()
        existingTexts.add(text)

        editor.putStringSet("layouts", existingTexts)
        editor.apply()
    }

    private fun restoreLayoutState() {
        val sharedPreferences = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val savedTexts = sharedPreferences.getStringSet("layouts", mutableSetOf())

        savedTexts?.forEach { text ->
            addLayout(text)
        }
    }

    private fun removeLayoutState(text: String) {
        val sharedPreferences = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val existingTexts = sharedPreferences.getStringSet("layouts", mutableSetOf()) ?: mutableSetOf()
        existingTexts.remove(text)

        editor.putStringSet("layouts", existingTexts)
        editor.apply()
    }

    fun goToHomeActivity(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}