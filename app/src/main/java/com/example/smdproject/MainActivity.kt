package com.example.smdproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.os.Handler
import android.os.Looper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Delay for 2 seconds, then go to ThirdActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, splashscreen::class.java)
            startActivity(intent)
            finish() // close MainActivity so user can’t go back
        }, 5000) // 2000ms = 2 seconds
    }
}