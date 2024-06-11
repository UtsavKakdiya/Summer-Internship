package com.example.summer_internship

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddNewCardActivity : AppCompatActivity() {

    val paymentActivity = PaymentActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addnewcard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val openCalendarButton: ImageButton = findViewById(R.id.calender)
//        val addButton = findViewById<ImageButton>(R.id.add_layout_btn)
//        val cardNumber = findViewById<EditText>(R.id.number_editText)
//
//        addButton.setOnClickListener {
//            paymentActivity.addLayout(cardNumber.text.toString())
//        }

        openCalendarButton.setOnClickListener {
            openDatePickerDialog()
        }
    }

    private fun openDatePickerDialog() {
        // Get current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val editTextDOB: EditText = findViewById(R.id.expiry_date_editText)
        var tmp : String

        // Create and show DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Handle date selection
                tmp = selectedYear.toString().substring(selectedYear.toString().length - 2)

                if (selectedMonth + 1 < 10) {

                    val selectedDate = "0${selectedMonth + 1}/$tmp"
                    editTextDOB.setText(selectedDate)
                }
                else {
                    val selectedDate = "${selectedMonth + 1}/$tmp"
                    editTextDOB.setText(selectedDate)
                }
//                Toast.makeText(this, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    fun goToPaymentActivity(view: View) {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
        finish()
    }
}