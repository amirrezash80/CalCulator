package com.amirreza.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.text.isDigitsOnly
import com.amirreza.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val expressionParts = mutableListOf<String>()
    private var dots:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun DisplayDigits(view: View) {
        val button = view as Button
        val buttonText = button.tag.toString()
        val lastIndex = expressionParts.size - 1

      if(expressionParts.isNotEmpty())
        if("(".contains(buttonText) && (expressionParts[lastIndex].isDigitsOnly() || expressionParts[lastIndex]==")" ))
            return


        if (expressionParts.isEmpty() || "+-*/()".contains(buttonText) || "+-*/()".contains(expressionParts.last())) {
            expressionParts.add(buttonText)
        } else {
            expressionParts[lastIndex] = expressionParts[lastIndex] + buttonText
        }

        binding.textView2.text = expressionParts.joinToString(separator = " ")
    }

    fun DisplayDots(view: View){
        val lastIndex = expressionParts.size - 1
        val button = view as Button
        val buttonText = button.tag.toString()
        println(expressionParts)
        if (expressionParts.isEmpty() || expressionParts[lastIndex].contains(".")){
            return
        }
        else{
            expressionParts[lastIndex] = expressionParts[lastIndex] + buttonText
            binding.textView2.text = expressionParts.joinToString(separator = " ")
        }
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

