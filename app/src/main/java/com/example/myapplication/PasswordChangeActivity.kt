package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var passwordInput: EditText
    private lateinit var submitButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        mAuth = FirebaseAuth.getInstance()

        passwordInput = findViewById(R.id.newPasswordEditText)
        submitButton = findViewById(R.id.changePasswordButton)

        submitButton.setOnClickListener {
            val newPassword = passwordInput.text.toString()
            if (newPassword.isEmpty()) {
                Toast.makeText(this, "pusta-pusta", Toast.LENGTH_SHORT).show()
            }else {
                mAuth.currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, PersonActivity::class.java))
                            finish()
                        }else {
                            Toast.makeText(this, "ragac nitoa", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}