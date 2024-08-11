package com.example.suitmedia.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia.data.api.DataItem
import com.example.suitmedia.databinding.UsersBinding
import com.example.suitmedia.view.Home

class UserAdapter : PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: UsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.firstname.text = data.firstName
            binding.lastname.text = data.lastName
            binding.email.text = data.email
            Glide.with(binding.imgUser.context)
                .load(data.avatar)
                .into(binding.imgUser)

            itemView.setOnClickListener {
                // Create an intent to start the Home activity
                val context = itemView.context
                val intent = Intent(context, Home::class.java).apply {
                    putExtra(Home.EXTRA_NAME_USERS, data.firstName)
                }
                context.startActivity(intent) // Start the Home activity
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}