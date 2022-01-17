package com.example.email

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegistration: Button
    private lateinit var buttonResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (FirebaseAuth.getInstance().currentUser != null){
            gotoProfile()
        }
        setContentView(R.layout.activity_login)

        init()
        registerListeners()
    }

    private fun init() {
        editTextEmail = findViewById(R.id. editTextEmail)
        editTextPassword = findViewById(R.id. editTextPassword)
        buttonLogin = findViewById(R.id. buttonLogin)
        buttonRegistration = findViewById(R.id. buttonRegistration)
        buttonResetPassword = findViewById(R.id.  buttonResetPassword)


    }

    private fun registerListeners(){
        buttonRegistration.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        buttonResetPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "ცარიელია", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        gotoProfile()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                }


        }

    }

    private fun gotoProfile(){
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }



}