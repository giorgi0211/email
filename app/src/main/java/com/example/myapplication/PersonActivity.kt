package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class PersonActivity : AppCompatActivity() {

    private lateinit var personInfoTextView: TextView
    private lateinit var passwordChangeButton: Button
    private lateinit var logoutButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        mAuth = FirebaseAuth.getInstance()

        personInfoTextView = findViewById(R.id.personInfoTextView)
        passwordChangeButton = findViewById(R.id.goToPasswordChangeButton)
        logoutButton = findViewById(R.id.logoutButton)

        personInfoTextView.text = mAuth.currentUser?.uid

        passwordChangeButton.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }

        logoutButton.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}