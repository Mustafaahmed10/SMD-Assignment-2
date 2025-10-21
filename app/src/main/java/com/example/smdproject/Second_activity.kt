package com.example.smdproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class Second_activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val username = findViewById<EditText>(R.id.username)
        val dateofbirth = findViewById<EditText>(R.id.D_Of_B)
        val Fname = findViewById<EditText>(R.id.Firstname)
        val Lname = findViewById<EditText>(R.id.Lastname)
        val signUpBtn = findViewById<Button>(R.id.btn_create)

        signUpBtn.setOnClickListener {
            val emailText = email.text.toString()
            val passText = password.text.toString()
            val usernameText = username.text.toString()
            val DOB = dateofbirth.text.toString()
            val FnameTText = Fname.text.toString()
            val LnameTText = Lname.text.toString()


            if (emailText.isEmpty() || passText.isEmpty() || usernameText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(emailText, passText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                        // Create user data map
                        val userMap = mapOf(
                            "Username" to usernameText,
                            "email" to emailText,
                            "DateOfBirth" to DOB,
                            "First-Name" to FnameTText,
                            "Last-Name" to LnameTText,
                            "profileSet" to true
                        )

                        // Save user info to Realtime DB
                        database.child("users").child(userId).setValue(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, Fifth_Activity::class.java))
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Database Error", Toast.LENGTH_SHORT).show()
                            }

                    } else {
                        Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}