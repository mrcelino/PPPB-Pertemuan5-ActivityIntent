package com.example.p5_tugas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p5_tugas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Inisialisasi binding secara global untuk memudahkan akses
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur binding dan layout
        setupBinding()

        // Mengatur event ketika tombol register ditekan
        setupRegisterButton()
    }

    // Fungsi untuk mengatur binding
    private fun setupBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    // Fungsi untuk mengatur event listener tombol register
    private fun setupRegisterButton() {
        viewBinding.regist.setOnClickListener {
            // Mengambil input dari user
            val userInfo = collectUserInput()

            // Validasi input dan pindah activity jika valid
            if (isInputValid(userInfo)) {
                navigateToLogin(userInfo)
            } else {
                showErrorMessage()
            }
        }
    }

    // Fungsi untuk mengumpulkan input dari user
    private fun collectUserInput(): Map<String, String> {
        val user = viewBinding.txtUser.text.toString()
        val email = viewBinding.txtEmail.text.toString()
        val phone = viewBinding.txtPhone.text.toString()
        val password = viewBinding.txtPassword.text.toString()
        return mapOf("USERNAME" to user, "EMAIL" to email, "PHONE" to phone, "PASSWORD" to password)
    }

    // Fungsi untuk mengecek validasi input
    private fun isInputValid(userInfo: Map<String, String>): Boolean {
        return userInfo.values.all { it.isNotEmpty() }
    }

    // Fungsi untuk pindah ke layar login
    private fun navigateToLogin(userInfo: Map<String, String>) {
        val loginIntent = Intent(this, LogIn::class.java).apply {
            putExtra("USERNAME", userInfo["USERNAME"])
            putExtra("EMAIL", userInfo["EMAIL"])
            putExtra("PHONE", userInfo["PHONE"])
            putExtra("PASSWORD", userInfo["PASSWORD"])
        }
        startActivity(loginIntent)
    }

    // Fungsi untuk menampilkan pesan error ketika input tidak valid
    private fun showErrorMessage() {
        Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show()
    }
}
