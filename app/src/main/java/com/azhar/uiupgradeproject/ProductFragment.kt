
package com.azhar.uiupgradeproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.uiupgradeproject.adapters.ProductAdapter
import com.azhar.uiupgradeproject.databinding.FragmentProductBinding
import com.azhar.uiupgradeproject.data.model.ProductModel
import com.azhar.uiupgradeproject.model.ProductResponse
import com.azhar.uiupgradeproject.network.RetrofitSetting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding

    companion object {
        val postService = RetrofitSetting.apiService
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        binding.rvProduct.layoutManager = LinearLayoutManager(context)
        binding.rvProduct.setHasFixedSize(true)
        getProduct()
        return binding.root
    }

    private fun getProduct() {
        val sharedPreference = activity?.getSharedPreferences("AUTH_LOGIN", Context.MODE_PRIVATE)
        val mToken = sharedPreference?.getString("token", null)

        postService.getProducts("Bearer $mToken")
            .enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    if (response.isSuccessful) {
                        val mData: List<ProductModel> = response.body()?.products ?: emptyList()
                        val adapter = context?.let { ProductAdapter(mData, it) }
                        binding.rvProduct.adapter = adapter
                    } else {
                        Toast.makeText(context, "Gagal memuat produk", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
