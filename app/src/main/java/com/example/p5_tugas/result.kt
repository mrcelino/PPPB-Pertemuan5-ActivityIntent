package com.example.p5_tugas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.p5_tugas.databinding.ActivityResultBinding

class result : AppCompatActivity() {

    // Inisialisasi binding untuk layout
    private lateinit var viewBinding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup binding dan layout
        setupBinding()

        // Menampilkan data yang diterima dari Login Page
        displayUserData()
    }

    // Fungsi untuk mengatur binding dan layout
    private fun setupBinding() {
        viewBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    // Fungsi untuk menampilkan data user yang diterima dari Login Page
    private fun displayUserData() {
        val userData = retrieveIntentData()

        with(viewBinding) {
            usernameText.text = userData["USERNAME"]
            emailText.text = userData["EMAIL"]
            phoneText.text = userData["PHONE"]
        }
    }

    // Fungsi untuk mengambil data dari intent
    private fun retrieveIntentData(): Map<String, String?> {
        return mapOf(
            "USERNAME" to intent.getStringExtra("USERNAME"),
            "EMAIL" to intent.getStringExtra("EMAIL"),
            "PHONE" to intent.getStringExtra("PHONE")
        )
    }
}
