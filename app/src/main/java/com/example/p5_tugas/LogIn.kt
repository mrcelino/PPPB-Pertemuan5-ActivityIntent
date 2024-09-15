package com.example.p5_tugas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p5_tugas.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {

    // Variabel untuk menyimpan data user yang terdaftar
    private lateinit var viewBinding: ActivityLogInBinding
    private var userCredentials: Map<String, String?> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup view binding
        setupBinding()

        // Ambil data dari halaman registrasi
        retrieveRegistrationData()

        // Setup event listener untuk tombol login
        setupLoginButton()
    }

    // Fungsi untuk setup binding
    private fun setupBinding() {
        viewBinding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    // Fungsi untuk mengambil data yang dikirim dari halaman register
    private fun retrieveRegistrationData() {
        userCredentials = mapOf(
            "USERNAME" to intent.getStringExtra("USERNAME"),
            "PASSWORD" to intent.getStringExtra("PASSWORD"),
            "EMAIL" to intent.getStringExtra("EMAIL"),
            "PHONE" to intent.getStringExtra("PHONE")
        )
    }

    // Fungsi untuk setup tombol login
    private fun setupLoginButton() {
        viewBinding.login.setOnClickListener {
            val inputUsername = viewBinding.txtUser.text.toString()
            val inputPassword = viewBinding.txtPassword.text.toString()

            // Cek apakah username dan password sesuai
            if (isCredentialsValid(inputUsername, inputPassword)) {
                navigateToResult()
            } else {
                showLoginError()
            }
        }
    }

    // Fungsi untuk mengecek apakah username dan password valid
    private fun isCredentialsValid(inputUsername: String, inputPassword: String): Boolean {
        return inputUsername == userCredentials["USERNAME"] && inputPassword == userCredentials["PASSWORD"]
    }

    // Fungsi untuk pindah ke activity result
    private fun navigateToResult() {
        val resultIntent = Intent(this, result::class.java).apply {
            putExtra("USERNAME", userCredentials["USERNAME"])
            putExtra("EMAIL", userCredentials["EMAIL"])
            putExtra("PHONE", userCredentials["PHONE"])
        }
        startActivity(resultIntent)
    }

    // Fungsi untuk menampilkan pesan error jika login gagal
    private fun showLoginError() {
        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
    }
}
