package com.example.mobileappdevproj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileappdevproj.DBHelper.DBHelper
import com.example.mobileappdevproj.Model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var listUser: List<User> = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)

        db = DBHelper(this)

        val emailVal = intent.getStringExtra("email")
        val email = findViewById<EditText>(R.id.email).apply {
            setText(emailVal)
        }
        val pass = findViewById<EditText>(R.id.password)
        //User Login
        val loginBtn = findViewById<Button>(R.id.login_btn)

        loginBtn.setOnClickListener {
            if(db.loginUser(email.text.toString(), pass.text.toString())) {
                var intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("email", email.text.toString())
                intent.putExtra("pass", email.text.toString())
                startActivity(intent)
                Toast.makeText(applicationContext, "Successfully logged in", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Error Logging in!", Toast.LENGTH_SHORT).show()
            }
        }


        //google API
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        var signInGoogle = findViewById<com.google.android.gms.common.SignInButton>(R.id.signIn_google)

        fun signIn() {
            var RC_SIGN_IN = 0
            var signInIntent = Intent(mGoogleSignInClient.getSignInIntent());
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data);
            var RC_SIGN_IN = 0
            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                var task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        }

        signInGoogle.setOnClickListener {
            when (it.id) {
                R.id.signIn_google -> signIn()
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.statusCode)
        }
    }

}