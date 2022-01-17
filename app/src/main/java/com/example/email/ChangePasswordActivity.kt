package com.example.email

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonChangePassword: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)


        init()
        registerListeners()
    }


    private fun init(){
        editTextNewPassword = findViewById(R.id. editTextNewPassword)
        buttonChangePassword = findViewById(R.id. buttonChangePassword)
    }

    private fun registerListeners(){
        buttonChangePassword.setOnClickListener {
                val newPassword = editTextNewPassword.toString()
            if(newPassword.isEmpty() || newPassword.length < 6) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "ERRORRR!", Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }

}