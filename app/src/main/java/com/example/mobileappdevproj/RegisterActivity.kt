package com.example.mobileappdevproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.Model.User

class RegisterActivity : AppCompatActivity() {

    internal lateinit var dbUser:DBHelper_User
    internal var listUser: List<User> = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationlayout)

        var signup_btn = findViewById<Button>(R.id.loginbtn)

        dbUser = DBHelper_User(this)

        signup_btn.setOnClickListener {
            var full_name = findViewById<EditText>(R.id.fullName)
            var email = findViewById<EditText>(R.id.email)
            var address = findViewById<EditText>(R.id.address)
            var contact = findViewById<EditText>(R.id.number)
            var pass = findViewById<EditText>(R.id.password)
            var conf_pass = findViewById<EditText>(R.id.confirmPassword)

            if (full_name.text.toString().length == 0){
                Toast.makeText(applicationContext, "Name field empty!", Toast.LENGTH_LONG).show()
            } else if (address.text.toString().length == 0) {
                Toast.makeText(applicationContext, "Address field empty!", Toast.LENGTH_LONG).show()
            } else if(contact.text.toString().length == 0){
                Toast.makeText(applicationContext, "Contact field empty!", Toast.LENGTH_LONG).show()
            } else if (email.text.toString().length == 0) {
                Toast.makeText(applicationContext, "Email field empty!", Toast.LENGTH_LONG).show()
            }  else if (pass.text.toString().length == 0 || conf_pass.text.toString().length == 0){
                Toast.makeText(applicationContext, "Password field empty!", Toast.LENGTH_LONG).show()
            } else if (!(pass.text.toString().equals(conf_pass.text.toString()))) {
                Toast.makeText(applicationContext, "Password Mismatch!", Toast.LENGTH_LONG).show()
            } else if (dbUser.emailExists(email.text.toString())){
                Toast.makeText(applicationContext, "Email already exists!", Toast.LENGTH_LONG).show()
            } else {
                val user = User(
                    full_name.text.toString(),
                    email.text.toString(),
                    address.text.toString(),
                    contact.text.toString(),
                    pass.text.toString(),
                )
                dbUser.addUser(user)
                Toast.makeText(applicationContext, "Successfully Registered!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("email", email.text.toString())
                startActivity(intent)
            }
        }
    }
}