package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var tvInput: TextView?=null
    var lastNumeric=false
    var lastDecimal=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById<TextView>(R.id.tvInput)

        }
    fun onClear(view: View) {
        tvInput?.text = ""
    }


    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDecimal=false
    }
    fun onDecimalPoint(view: View) {
        if (tvInput?.text == "") {
            tvInput?.append("0.")
            lastDecimal = true
            lastNumeric = false
        }
        if (lastNumeric && !lastDecimal && !tvInput?.text?.contains(".")!!) {
            tvInput?.append(".")
            lastDecimal = true
            lastNumeric = false
        }
    }
    fun onOperator(view: View)
    {

        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric=false
            }
        }
    }
      fun isOperatorAdded(value:String):Boolean{
       return if(value.startsWith("-")){
           false
       }else{
           value.contains("/")||
                   value.contains("+")
                   ||value.contains("-")
                   ||value.contains("*")
       }
      }
    fun removeDotZero(value: String):String{
        return if(value.endsWith(".0")) {
            value.substring(0,value.length-2)
        } else
            value
    }
    fun onEquals(view: View)
    {
        if(lastNumeric)
        {
            var prefix=""
            var tvValue=tvInput?.text.toString()
            if(tvValue.startsWith("-"))
            {
            prefix="-"
                tvValue=tvValue.substring(1)
            }
            try {
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = prefix + splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeDotZero((one.toDouble() - two.toDouble()).toString())
                }
                if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var one = prefix + splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeDotZero((one.toDouble() + two.toDouble()).toString())
                }
                if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var one = prefix + splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeDotZero((one.toDouble() * two.toDouble()).toString())
                }
                if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var one = prefix + splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeDotZero((one.toDouble() / two.toDouble()).toString())
                    if(two.toDouble()==0.0)
                    {

                        tvInput?.text="CANNOT DIVIDE WITH ZERO"
                    }

                }
            }
            catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
                tvInput?.text="ERROR"
            }
        }

    }


}


