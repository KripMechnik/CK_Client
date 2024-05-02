package com.example.ck_project.ui.cart

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ck_project.databinding.CartItemBinding
import com.example.ck_project.domain.entity.FullItemEntity

class CartRecyclerAdapter(val viewModel: CartViewModel, val data: MutableList<MutableMap.MutableEntry<FullItemEntity, Int>>) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: FullItemEntity, amount: Int){
            binding.tvPrice.text = item.price
            binding.tvTitle.text = item.name
            binding.tvAmount.text = amount.toString()

            val addBtn = binding.addToCart
            val removeBtn = binding.removeFromCart

            addBtn.setOnClickListener {
                viewModel.addItem(item)
            }

            removeBtn.setOnClickListener {
                viewModel.removeItem(item)
                Log.i("RRR", amount.toString())
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(p0.context),
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(data[p1].key, data[p1].value)
    }
}