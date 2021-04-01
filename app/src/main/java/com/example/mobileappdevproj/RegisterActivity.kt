package com.example.mobileappdevproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        var signup_btn = findViewById<Button>(R.id.signInbtn)

        signup_btn.setOnClickListener {
            var full_name = findViewById<EditText>(R.id.fullName)
            var email = findViewById<EditText>(R.id.email)
            var address = findViewById<EditText>(R.id.address)
            var contact = findViewById<EditText>(R.id.number)
            var pass = findViewById<EditText>(R.id.password)
            var conf_pass = findViewById<EditText>(R.id.confirmPassword)
        }
    }
}