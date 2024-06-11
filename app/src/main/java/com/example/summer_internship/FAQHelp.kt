package com.example.summer_internship

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class FAQHelp : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.faq_help, container, false)

        searchClickListener(view, "")
        suggestionClicked(view)

        return view
    }

    fun suggestionClicked(view: View) {
        val sugg1 : TextView = view.findViewById(R.id.suggestion1)
        val sugg2 : TextView = view.findViewById(R.id.suggestion2)
        val sugg3 : TextView = view.findViewById(R.id.suggestion3)
        val sugg4 : TextView = view.findViewById(R.id.suggestion4)

        sugg1.setOnClickListener {
            searchClickListener(view, sugg1.text.toString())
        }
        sugg2.setOnClickListener {
            searchClickListener(view, sugg2.text.toString())
        }
        sugg3.setOnClickListener {
            searchClickListener(view, sugg3.text.toString())
        }
        sugg4.setOnClickListener {
            searchClickListener(view, sugg4.text.toString())
        }
    }

    fun searchClickListener(view: View, str: String) {
        val searchTab : EditText = view.findViewById(R.id.faq_editText)
        val tintedBG : View = view.findViewById(R.id.tinted_background)
        val sameSearchLayout : FrameLayout = view.findViewById(R.id.layout_same_search)

        searchTab.setText(str)
        searchTab.setSelection(searchTab.text.length)

        searchTab.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called to notify you that some part of the text has been changed
                sameSearchLayout.visibility = View.VISIBLE
                tintedBG.visibility = View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }
        })

        searchTab.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            // Check if the key event was the Enter key
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                // Consume the Enter key event
                sameSearchLayout.visibility = View.GONE
                tintedBG.visibility = View.GONE
                Log.d("FAQHelp", "Enter key pressed, hiding search layout and background")


                // Hide the keyboard
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchTab.windowToken, 0)

                return@OnKeyListener true
            }
            // Allow other key events to be handled normally
            false
        })

        tintedBG.setOnClickListener {
            sameSearchLayout.visibility = View.GONE
            tintedBG.visibility = View.GONE
        }
    }


}