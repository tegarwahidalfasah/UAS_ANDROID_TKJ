package com.azhar.uiupgradeproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load // Pastikan dependensi Coil sudah ditambahkan di build.gradle (Module: app)
import com.azhar.uiupgradeproject.data.model.Recipe // Pastikan package ini benar
import com.azhar.uiupgradeproject.databinding.ListRecipeBinding
import com.azhar.uiupgradeproject.R // <-- PENTING: Pastikan ini diimport untuk mengakses drawable Anda

class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(private val binding: ListRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.tvRecipeName.text = recipe.name
            binding.tvRecipeDifficulty.text = "Difficulty: ${recipe.difficulty}"
            binding.tvRecipeCuisine.text = "Cuisine: ${recipe.cuisine}"
            binding.tvPrepCookTime.text = "Prep: ${recipe.prepTimeMinutes}min, Cook: ${recipe.cookTimeMinutes}min"
            binding.ivRecipeImage.load(recipe.image) {
                crossfade(true)
                placeholder(R.drawable.ic_menu_gallery_custom) // Opsional: Ganti placeholder juga jika mau
                error(R.drawable.ic_broken_image) // <-- UBAH KE DRAWABLE KUSTOM ANDA
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ListRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}