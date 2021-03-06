package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var signUpEmailEditText: EditText
    private lateinit var signUpPasswordEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        signUpEmailEditText = findViewById(R.id.signUpEmailEditText)
        signUpPasswordEditText = findViewById(R.id.signUpPasswordEditText)
        registerButton = findViewById(R.id.signUpButton)

        registerButton.setOnClickListener {
            val email = signUpEmailEditText.text.toString()
            val password = signUpPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "pusta-pusta", Toast.LENGTH_SHORT).show()
            }else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else {
                            Toast.makeText(this, "ragac nitoa", Toast.LENGTH_SHORT).show()
                        }

                }
            }
        }
    }
}