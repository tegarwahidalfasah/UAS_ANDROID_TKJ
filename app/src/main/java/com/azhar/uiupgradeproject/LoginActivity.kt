// app/src/main/java/com/azhar/uiupgradeproject/LoginActivity.kt
package com.azhar.uiupgradeproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.azhar.uiupgradeproject.databinding.ActivityLoginBinding
import com.azhar.uiupgradeproject.models.LoginRequest
import com.azhar.uiupgradeproject.models.LoginResponse
import com.azhar.uiupgradeproject.network.RetrofitClient // Pastikan ini
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val loginRequest = LoginRequest(username = username, password = password)

        lifecycleScope.launch {
            try {
                // Panggil loginUser dari RetrofitClient.apiService
                val response = RetrofitClient.apiService.loginUser(loginRequest)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        Log.d("LoginActivity", "Login berhasil! Token: ${it.token}")
                        Toast.makeText(this@LoginActivity, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } ?: run {
                        Log.e("LoginActivity", "Respons sukses tapi body kosong.")
                        Toast.makeText(this@LoginActivity, "Respons login kosong.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("LoginActivity", "Login gagal: Kode ${response.code()}, Pesan: ${response.message()}, Body Error: $errorBody")
                    Toast.makeText(this@LoginActivity, "Login Gagal: ${response.message()}\nDetail: $errorBody", Toast.LENGTH_LONG).show()
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("LoginActivity", "HTTP Error saat login: ${e.code()}, Pesan: ${e.message()}, Body: $errorBody", e)
                Toast.makeText(this@LoginActivity, "Error Login: ${e.message}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error koneksi atau tak terduga saat login: ${e.message}", e)
                Toast.makeText(this@LoginActivity, "Koneksi Gagal: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}