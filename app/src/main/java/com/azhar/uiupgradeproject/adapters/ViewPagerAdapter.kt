package com.azhar.uiupgradeproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azhar.uiupgradeproject.ProductFragment
import com.azhar.uiupgradeproject.RecipesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Jumlah total tab yang Anda miliki (Product dan Recipes)
    override fun getItemCount(): Int = 2

    // Mengembalikan Fragment yang sesuai untuk setiap posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductFragment() // Tab pertama (posisi 0) akan menampilkan ProductFragment
            1 -> RecipesFragment() // Tab kedua (posisi 1) akan menampilkan RecipesFragment
            else -> throw IllegalArgumentException("Posisi tab tidak valid") // Untuk posisi lain yang tidak diharapkan
        }
    }
}