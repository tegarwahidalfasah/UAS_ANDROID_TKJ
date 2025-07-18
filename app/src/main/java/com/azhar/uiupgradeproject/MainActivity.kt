package com.azhar.uiupgradeproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azhar.uiupgradeproject.adapters.ViewPagerAdapter
import com.azhar.uiupgradeproject.databinding.ActivityMainBinding // Pastikan ini mengarah ke binding yang benar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Menghubungkan ViewPager dengan ViewPagerAdapter yang sudah kita buat
        binding.viewPager.adapter = ViewPagerAdapter(this) // 'this' merujuk ke FragmentActivity yang diperlukan oleh adapter

        // 2. Menghubungkan TabLayout dengan ViewPager
        // Ini akan secara otomatis membuat tab dan mengaturnya sesuai dengan Fragment di ViewPager
        TabLayoutMediator(
            binding.tabLayout, // TabLayout yang ada di activity_main.xml
            binding.viewPager // ViewPager2 yang ada di activity_main.xml
        ) { tab, position ->
            // Mengatur teks untuk setiap tab berdasarkan posisinya
            if (position == 0) {
                tab.text = "Product" // Teks untuk tab pertama
            } else {
                tab.text = "Recipes" // Teks untuk tab kedua
            }
        }.attach() // Penting: Jangan lupa memanggil .attach() untuk menghubungkan keduanya
    }
}