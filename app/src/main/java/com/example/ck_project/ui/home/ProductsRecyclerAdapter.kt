package com.example.ck_project.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ck_project.databinding.ProductItemBinding
import com.example.ck_project.domain.entity.FullItemEntity
import com.example.ck_project.ui.cart.CartViewModel

class ProductsRecyclerAdapter(val cartViewModel: CartViewModel) : RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>() {

    val data = mutableListOf<FullItemEntity>()


    inner class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FullItemEntity){
            binding.tvPrice.text = item.price
            binding.tvTitle.text = item.name
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(p0.context),
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.bind(data[p1])
        val addBtn = p0.binding.addToCart
        addBtn.setOnClickListener {
            cartViewModel.addItem(FullItemEntity(data[p1].id, data[p1].name, data[p1].image, data[p1].price))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<FullItemEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}