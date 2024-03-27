package com.amirreza.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.amirreza.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val expressionParts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun DisplayDigits(view: View) {
        val button = view as Button
        val buttonText = button.tag.toString()

        if (expressionParts.isEmpty() || "+-*/()".contains(buttonText) || "+-*/()".contains(expressionParts.last())) {
            expressionParts.add(buttonText)
        } else {
            val lastIndex = expressionParts.size - 1
            expressionParts[lastIndex] = expressionParts[lastIndex] + buttonText
        }

        binding.textView2.text = expressionParts.joinToString(separator = " ")
    }

    fun ClearDigits(view: View) {
        expressionParts.clear()
        binding.textView2.text = ""
    }

    fun CalculateDigits(view: View) {
        val calculation = Expression(expressionParts)
        val result = calculation.EvaluateExpression()
        println(result)
        binding.textView2.text = result.toString()
        expressionParts.clear()
        expressionParts.add(result.toString())
    }
}

