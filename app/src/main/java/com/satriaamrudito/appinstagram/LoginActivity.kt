package com.satriaamrudito.appinstagram

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_signup.setOnClickListener {
            val goToSignUp = Intent(this, RegisterActivity::class.java)
            startActivity(goToSignUp)
        }

        btn_login.setOnClickListener {
            loginAkun()
        }
    }

    private fun loginAkun() {
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        when{
            TextUtils.isEmpty(email) -> Toast.makeText(this, "email must be filled", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(password) -> Toast.makeText(this, "password must be filled", Toast.LENGTH_SHORT).show()

            else ->{
                val progress = ProgressDialog(this)
                progress.setTitle("Signing in")
                progress.setMessage("please wait...")
                progress.setCanceledOnTouchOutside(false)
                progress.show()

                val mauth : FirebaseAuth = FirebaseAuth.getInstance()

                mauth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            val pergi = Intent(this,MainActivity::class.java)
                            startActivity(pergi)
                            finish()
                        }else{
                            val message = task.exception.toString()
                            Toast.makeText(this,"gagal $message",Toast.LENGTH_SHORT).show()
                            FirebaseAuth.getInstance().signOut()
                            progress.dismiss()
                        }
                    }
            }
        }
    }

    //ini biar kalau udah login langsung ke MainActivity
    override fun onStart() {
        super.onStart()

        if (FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }
    }
}