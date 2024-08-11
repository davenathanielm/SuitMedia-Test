package com.example.suitmedia.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_NAME_USERS = "extra_name_users"
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, User::class.java)
            startActivity(intent)
        }
        binding.buttonback.setOnClickListener{
            onBackPressed()
        }

        setUpName()
        setUpUserName()
    }

    private fun setUpName() {
            val name = intent.getStringExtra(EXTRA_NAME)
            binding.username.text = name
    }

    private fun setUpUserName(){
        val usernames = intent.getStringExtra(EXTRA_NAME_USERS)
        if (usernames != null) {
            binding.selectedUsername.text = usernames
        }
    }
}