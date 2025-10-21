package com.example.smdproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Thirteen_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirteen)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val Name = findViewById<EditText>(R.id.name)
        val userName = findViewById<EditText>(R.id.username)
        val Email = findViewById<EditText>(R.id.email)
        val logoutBtn = findViewById<TextView>(R.id.switch_account_text)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid

            // Reference to user's data
            val userRef = database.child("users").child(uid)

            // Read data once
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val name = snapshot.child("First-Name").getValue(String::class.java)
                        val username = snapshot.child("Username").getValue(String::class.java)
                        val email = snapshot.child("email").getValue(String::class.java)
                        val age = snapshot.child("age").getValue(String::class.java)
                        val gender = snapshot.child("gender").getValue(String::class.java)


                        Name.setText(name ?: "N/A")
                        userName.setText(username ?: "N/A")
                        Email.setText(email ?: "N/A")






                    } else {
                        Toast.makeText(this@Thirteen_Activity, "User data not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Thirteen_Activity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        //val img: MaterialButton = findViewById(R.id.editProfile)

       // img.setOnClickListener{

            //  Toast.makeText(applicationContext, "fludog", Toast.LENGTH_SHORT).show()
            //   layout.setBackgroundResource(R.color.yellow)
            //intent=Intent(applicationContext, Thirteen_Activity::class.java )
            //startActivity(intent)
        //}

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Fourth_Activity::class.java))
            finish()
        }

    }
}