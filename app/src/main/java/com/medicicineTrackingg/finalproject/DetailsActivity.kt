package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MEDICINE = "extra_medicine"
    }

    private lateinit var textViewDescription: TextView
    private lateinit var textViewUsage: TextView
    private lateinit var textViewInstruction: TextView
    private lateinit var textViewsideEffects: TextView

    private lateinit var usageButton: Button
    private lateinit var instructionsButton: Button
    private lateinit var sideEffectsButton: Button
    private lateinit var descriptionButton: Button

    private var isDescriptionVisible = false
    private var isUsageVisible = false
    private var isInstructionsVisible = false
    private var isSideEffectsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val textViewName = findViewById<TextView>(R.id.textViewName)
        textViewDescription = findViewById(R.id.textViewDescription)
        textViewDescription.visibility = View.INVISIBLE

        textViewUsage = findViewById(R.id.textViewUsage)
        textViewUsage.visibility = View.INVISIBLE

        textViewInstruction = findViewById(R.id.textViewInstructions)
        textViewInstruction.visibility = View.INVISIBLE

        textViewsideEffects = findViewById(R.id.textViewSideEffects)
        textViewsideEffects.visibility = View.INVISIBLE

        val imageUrl = findViewById<ImageView>(R.id.detailImageView)

        descriptionButton = findViewById(R.id.button_plus)
        descriptionButton.setOnClickListener {
            toggleVisibility(textViewDescription, !isDescriptionVisible)
            isDescriptionVisible = !isDescriptionVisible
        }

        usageButton = findViewById(R.id.button_usage)
        usageButton.setOnClickListener {
            toggleVisibility(textViewUsage, !isUsageVisible)
            isUsageVisible = !isUsageVisible
        }

        instructionsButton = findViewById(R.id.button_instructions)
        instructionsButton.setOnClickListener {
            toggleVisibility(textViewInstruction, !isInstructionsVisible)
            isInstructionsVisible = !isInstructionsVisible
        }

        sideEffectsButton = findViewById(R.id.button_side_effects)
        sideEffectsButton.setOnClickListener {
            toggleVisibility(textViewsideEffects, !isSideEffectsVisible)
            isSideEffectsVisible = !isSideEffectsVisible
        }

        val medicine = intent.getParcelableExtra<Medicine>(EXTRA_MEDICINE)

        if (medicine != null) {
            textViewName.text = medicine.name

            Glide.with(this)
                .load(medicine.image_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageUrl)
        } else {
            textViewName.text = "No medicine found"
            textViewDescription.text = ""
        }
    }

    private fun toggleVisibility(textView: TextView, isVisible: Boolean) {
        if (isVisible) {
            val medicine = intent.getParcelableExtra<Medicine>(EXTRA_MEDICINE)
            textView.text = getTextByTextViewId(textView.id, medicine)
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

    private fun getTextByTextViewId(textViewId: Int, medicine: Medicine?): String? {
        return when (textViewId) {
            R.id.textViewDescription -> medicine?.description
            R.id.textViewUsage -> medicine?.usage
            R.id.textViewInstructions -> medicine?.instructions
            R.id.textViewSideEffects -> medicine?.side_effects
            else -> null
        }
    }
}

