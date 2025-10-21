package com.example.smdproject

import android.os.Bundle
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class Third_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Finding views
        val loginBtn = findViewById<MaterialButton>(R.id.btn_login)
        val signUpText = findViewById<TextView>(R.id.signUp_navigate)

        // Login button → Fourth_Activity
        loginBtn.setOnClickListener {
            val intent = Intent(this, Fourth_Activity::class.java)
            startActivity(intent)
        }

        // SignUp text → Second_Activity
        signUpText.setOnClickListener {
            val intent = Intent(this, Second_activity::class.java)
            startActivity(intent)
        }







    }
}