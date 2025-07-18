package com.azhar.uiupgradeproject.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.azhar.uiupgradeproject.R
import com.azhar.uiupgradeproject.databinding.ListProductBinding
import com.azhar.uiupgradeproject.data.model.ProductModel // <--- BARIS INI YANG DIPERBAIKI!
import com.bumptech.glide.Glide


class ProductAdapter(data: List<ProductModel>?, context: Context) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var data: List<ProductModel>?
    var context: Context

    init {
        this.data = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.list_product, parent, false))

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val data = data?.get(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return if (data != null) data!!.size else 0
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListProductBinding.bind(itemView)

        fun bind(mData: ProductModel) {

            with(binding){
                tvTitle.text = mData.title
                tvPrice.text = mData.price.toString() // Pastikan mData.price bisa dikonversi ke String

                Glide.with(context)
                    .load(mData.thumbnail)
                    .into(imageView)
            }

            binding.root.setOnClickListener {
                // Kode untuk Intent ke DetailActivity (masih dikomentari)
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra("NAMA", mData.title.toString())
//                intent.putExtra("ID",mData.id.toString())
//                itemView.context.startActivity(intent)

                // Toast.makeText(context, mData.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}