package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var resetButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this, PersonActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_main)

        inputEmail = findViewById(R.id.signInEmailEditText)
        inputPassword = findViewById(R.id.signInPasswordEditText)
        loginButton = findViewById(R.id.signInButton)
        registerButton = findViewById(R.id.goToRegistrationButton)
        resetButton = findViewById(R.id.goToPasswordResetButton)

        loginButton.setOnClickListener {
            val email = inputEmail.text.toString()
            val passwoord = inputPassword.text.toString()

            if (email.isEmpty() || passwoord.isEmpty()) {
                Toast.makeText(this,"pusta-pusta", Toast.LENGTH_SHORT).show()
            } else {
                mAuth.signInWithEmailAndPassword(email, passwoord).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, PersonActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, "ragac nitoa", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        registerButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        resetButton.setOnClickListener {
            startActivity(Intent(this, ResetActivity::class.java))
        }

    }
}