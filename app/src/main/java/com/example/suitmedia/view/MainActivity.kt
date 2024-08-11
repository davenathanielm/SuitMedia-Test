package com.example.suitmedia.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

        binding.btnCheck.setOnClickListener {
            setUpPalindrome()
        }

        binding.btnNext.setOnClickListener{
            setUpName()
        }

    }

    private fun setUpName() {
        val name = binding.nameEditText.text.toString()
        if (name.isNotEmpty()){
            val intentName = Intent(this@MainActivity, Home::class.java)
            intentName.putExtra(Home.EXTRA_NAME, name)
            startActivity(intentName)
        }
    }

    private fun setUpPalindrome() {
       val palindrome = binding.palindromeEditText.text.toString().trim()
        if (palindrome.isNotEmpty()){
            val isPalindrome = palindrome.equals(palindrome.reversed(),ignoreCase = true)
            val message =
                if (isPalindrome) {
                    "This is Palindrome"
                }
                else{
                    "This is not Palindrome"
                }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        else{
             Toast.makeText(this, "Please Input Palindrome Text First !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpView() {
        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}