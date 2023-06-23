package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MEDICINE = "extra_medicine"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)

        val medicine = intent.getParcelableExtra<Medicine>(EXTRA_MEDICINE)

        if (medicine != null) {
            textViewName.text = medicine.name
            textViewDescription.text = medicine.description
        } else {
            textViewName.text = "No medicine found"
            textViewDescription.text = ""
        }
    }
}