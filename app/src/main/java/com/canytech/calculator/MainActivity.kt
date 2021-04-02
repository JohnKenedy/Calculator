package com.canytech.calculator

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tv_input.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onOne(view: View) {
        einstein_ready.visibility = View.INVISIBLE
        einstein_fight2.visibility = View.INVISIBLE
        einstein_fight3.visibility = View.INVISIBLE
        einstein_fight.visibility = View.VISIBLE
        Handler().postDelayed({
            einstein_ready.visibility = View.VISIBLE
            einstein_fight.visibility = View.INVISIBLE
        }, 2000)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onFour(view: View) {
        einstein_ready.visibility = View.INVISIBLE
        einstein_fight2.visibility = View.VISIBLE
        einstein_fight.visibility = View.INVISIBLE
        einstein_fight3.visibility = View.INVISIBLE
        Handler().postDelayed({
            einstein_ready.visibility = View.VISIBLE
            einstein_fight2.visibility = View.INVISIBLE
        }, 800)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onSeven(view: View) {
        einstein_ready.visibility = View.INVISIBLE
        einstein_fight3.visibility = View.VISIBLE
        einstein_fight2.visibility = View.INVISIBLE
        einstein_fight.visibility = View.INVISIBLE
        Handler().postDelayed({
            einstein_ready.visibility = View.VISIBLE
            einstein_fight3.visibility = View.INVISIBLE
        }, 1000)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onThree(view: View) {
        hawking_ready.visibility = View.INVISIBLE
        hawking_fight.visibility = View.VISIBLE
        hawking_fight2.visibility = View.INVISIBLE
        hawking_fight3.visibility = View.INVISIBLE
        einstein_ready.visibility = View.INVISIBLE
        einstein_fight3.visibility = View.VISIBLE

        Handler().postDelayed({
            hawking_ready.visibility = View.VISIBLE
            hawking_fight.visibility = View.INVISIBLE
            einstein_ready.visibility = View.VISIBLE
            einstein_fight3.visibility = View.INVISIBLE
        }, 3000)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onSix(view: View) {
        hawking_ready.visibility = View.INVISIBLE
        hawking_fight2.visibility = View.VISIBLE
        hawking_fight.visibility = View.INVISIBLE
        hawking_fight3.visibility = View.INVISIBLE
        einstein_ready.visibility = View.INVISIBLE
        einstein_fight3.visibility = View.VISIBLE
        Handler().postDelayed({
            hawking_ready.visibility = View.VISIBLE
            hawking_fight2.visibility = View.INVISIBLE
            einstein_ready.visibility = View.VISIBLE
            einstein_fight3.visibility = View.INVISIBLE
        }, 1500)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onNine(view: View) {
        hawking_ready.visibility = View.INVISIBLE
        hawking_fight3.visibility = View.VISIBLE
        hawking_fight.visibility = View.INVISIBLE
        hawking_fight2.visibility = View.INVISIBLE
        Handler().postDelayed({
            hawking_ready.visibility = View.VISIBLE
            hawking_fight3.visibility = View.INVISIBLE
        }, 1500)
        tv_input.append((view as Button).text)
        lastNumeric = true
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tv_input.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tv_input.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("-") -> {
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tv_input.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tv_input.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    tvValue.contains("/") -> {
                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tv_input.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tv_input.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tv_input.text.toString())) {
            tv_input.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }

}