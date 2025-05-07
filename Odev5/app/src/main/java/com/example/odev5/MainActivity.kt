package com.example.odev5

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var input: String = ""
    private var number: String? = null
    private var sign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val numberButtons = listOf(
            R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four,
            R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine
        )

        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val digit = (it as Button).text.toString()
                input += digit
                binding.display.text = input
            }
        }
        findViewById<Button>(R.id.add).setOnClickListener {
            number = input
            sign = "+"
            input = ""
        }
        findViewById<Button>(R.id.equal).setOnClickListener {
            if (number != null && input.isNotEmpty() && sign == "+") {
                val first = number!!.toIntOrNull()
                val second = input.toIntOrNull()

                if (first != null && second != null) {
                    val result = first + second
                    binding.display.text = result.toString()
                    input = result.toString()
                    number = null
                    sign = null
                } else {
                    binding.display.text = "HATA"
                }
            }
        }
        findViewById<Button>(R.id.ac).setOnClickListener {
            input = ""
            number = null
            sign = null
            binding.display.text = "0"
        }
    }
}
