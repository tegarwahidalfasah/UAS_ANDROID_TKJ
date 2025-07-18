package com.azhar.uiupgradeproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.uiupgradeproject.adapters.RecipeAdapter
import com.azhar.uiupgradeproject.databinding.FragmentRecipesBinding
import com.azhar.uiupgradeproject.data.model.RecipeResponse // Pastikan import ini benar untuk lokasi RecipeResponse Anda
import com.azhar.uiupgradeproject.network.RetrofitClient
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchRecipes()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(emptyList())
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter
        }
    }

    private fun fetchRecipes() {
        // Tampilkan loading, sembunyikan error dan RecyclerView
        binding.progressBarRecipes.visibility = View.VISIBLE
        binding.tvErrorRecipes.visibility = View.GONE
        binding.rvRecipes.visibility = View.GONE

        lifecycleScope.launch { // Jalankan di coroutine
            try {
                val response = RetrofitClient.apiService.getAllRecipes() // Panggil suspend fun dari RetrofitClient.apiService
                if (response.isSuccessful) {
                    val recipeResponse: RecipeResponse? = response.body()
                    recipeResponse?.let {
                        if (it.recipes.isNotEmpty()) {
                            recipeAdapter.updateRecipes(it.recipes) // Panggil updateRecipes dari RecipeAdapter
                            binding.rvRecipes.visibility = View.VISIBLE
                        } else {
                            binding.tvErrorRecipes.text = "Tidak ada resep yang ditemukan."
                            binding.tvErrorRecipes.visibility = View.VISIBLE
                        }
                    } ?: run {
                        binding.tvErrorRecipes.text = "Respons resep kosong."
                        binding.tvErrorRecipes.visibility = View.VISIBLE
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Kesalahan tidak diketahui"
                    binding.tvErrorRecipes.text = "Error: ${response.code()} - $errorMessage"
                    binding.tvErrorRecipes.visibility = View.VISIBLE
                    Toast.makeText(context, "Gagal memuat resep: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                binding.tvErrorRecipes.text = "Kesalahan jaringan: ${e.message}"
                binding.tvErrorRecipes.visibility = View.VISIBLE
                Toast.makeText(context, "Kesalahan jaringan: ${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace() // Cetak stack trace untuk debugging lebih lanjut
            } finally {
                binding.progressBarRecipes.visibility = View.GONE // Sembunyikan loading setelah selesai
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Penting untuk menghindari memory leak View Binding
    }
}