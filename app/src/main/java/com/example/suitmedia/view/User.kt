package com.example.suitmedia.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.databinding.ActivityUserBinding
import com.example.suitmedia.di.ViewModelFactory
import com.example.suitmedia.view.adapter.LoadingStateAdapter
import com.example.suitmedia.view.adapter.UserAdapter


class User : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    private val userViewModel:UserViewModel by viewModels {
        ViewModelFactory(this)
    }

    private val TAG = "User"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonback.setOnClickListener{
            onBackPressed()
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerViewAllProducts called")
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter()

        binding.rvUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            })
        userViewModel.getAllUsers().observe(this,{
            adapter.submitData(lifecycle, it)
            Log.d(TAG, "Data Load")
            showLoading(false)
        })

    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        }
        else{
            binding.progressbar.visibility = View.GONE
        }
    }
}