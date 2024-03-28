package com.amirreza.calculator

import java.util.Stack

class Expression(var list: MutableList<String>) {

    private fun InfixToPostfix():String{

        var result = ""
        val stack =Stack<String>()

        for (element in list) {

            if(element.all {
                it.isDigit()|| element.any({ it == '.' })
                }) {
                result += "$element "
            }
            else if(element == "("){
                stack.push(element.toString())
            }
            else if(element == ")"){
                while(stack.peek() != "(" && stack.isNotEmpty()){
                    result += "${stack.pop()} "
                }
                if(stack.isNotEmpty())
                    stack.pop()
            }
            else {
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(element)){
                    result += "${stack.pop()} "
                }
                stack.push(element)
            }
        }
            while (stack.isNotEmpty()){
                result += "${stack.pop()} "
            }
        return result
    }

    private fun precedence(element: String): Int {
        return when(element){
            "*", "/" -> 2
            "+","-" -> 1
            else -> -1
        }
    }

    fun EvaluateExpression(): Number {
        try {
            var postfix: String = InfixToPostfix()
            val stack = Stack<Double>()
            var i = 0
            while (i < postfix.length) {
                if (postfix[i].isWhitespace()) {
                    i++
                    continue
                }
                var number = ""
                if (Character.isDigit(postfix[i]) || postfix[i] == '.') {
                    while (i < postfix.length && (Character.isDigit(postfix[i]) || postfix[i] == '.')) {
                        number += postfix[i++]
                    }
                    stack.push(number.toDouble())
                    continue  // Continue to the next iteration after processing a number.
                }
                // For operators, pop two elements from stack, apply operation, and push the result back.
                val x = stack.pop()
                val y = stack.pop()
                when (postfix[i]) {
                    '*' -> stack.push(y * x)
                    '/' -> stack.push(y / x)
                    '+' -> stack.push(y + x)
                    '-' -> stack.push(y - x)
                }
                i++
            }
            return if (stack.peek() % 1.0 == 0.0) stack.peek().toInt() else stack.peek()
        }
        catch (error: Exception) {
            return -1.23456789
        }
    }

}