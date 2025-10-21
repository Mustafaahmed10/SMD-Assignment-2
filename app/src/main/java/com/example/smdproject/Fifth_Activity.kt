package com.example.smdproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Fifth_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val userName = findViewById<TextView>(R.id.username_text)


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
                        val email = snapshot.child("email").getValue(String::class.java)
                        val age = snapshot.child("age").getValue(String::class.java)
                        val gender = snapshot.child("gender").getValue(String::class.java)


                        userName.text = "${name ?: "N/A"}"



                    } else {
                        Toast.makeText(this@Fifth_Activity, "User data not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Fifth_Activity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        val img: TextView = findViewById(R.id.username_text)

        img.setOnClickListener{

            //  Toast.makeText(applicationContext, "fludog", Toast.LENGTH_SHORT).show()
            //   layout.setBackgroundResource(R.color.yellow)
            intent=Intent(applicationContext, Eleventh_Activity::class.java )
            startActivity(intent)
        }



    }
}