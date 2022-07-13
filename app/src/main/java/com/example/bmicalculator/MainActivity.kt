package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString()
            val height = binding.etHeight.text.toString()
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / (height.toFloat() / 100).pow(2)
                val bmiResult = String.format("%.2f", bmi).toFloat()

                displayResult(bmiResult)

            }
        }
    }

    private fun displayResult(bmi: Float) {
        binding.tvIndex.text = bmi.toString()
        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "underweight"
                color = R.color.under_weighted
            }

            bmi in 18.50..29.99 -> {
                resultText = "Healthy"
                color = R.color.normal_weighted

            }

            bmi > 29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weighted
            }
        }

        binding.tvResult.setTextColor(ContextCompat.getColor(this, color))
        binding.tvResult.text = resultText
    }



    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> true

        }

    }
}