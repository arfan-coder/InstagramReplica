package com.satriaamrudito.appinstagram

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_search.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            val goToLogin = Intent(this,LoginActivity::class.java)
            startActivity(goToLogin)
        }

        btn_register.setOnClickListener {
            val goToLogin = Intent(this,LoginActivity::class.java)
            startActivity(goToLogin)
        }

        btn_register.setOnClickListener {
            createAkun()
        }
    }

    private fun createAkun() {
        val fullname = fullname_register.text.toString()
        val username = username_register.text.toString()
        val email = email_register.text.toString()
        val password = password_register.text.toString()

        when{
            TextUtils.isEmpty(fullname) -> Toast.makeText(this,"Fullname needs to be filled",Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(username) -> Toast.makeText(this,"Username needs to be filled",Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(email) -> Toast.makeText(this,"Email needs to be filled",Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(password) -> Toast.makeText(this,"Password needs to be filled",Toast.LENGTH_SHORT).show()
            else -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Register")
                progressDialog.setMessage("please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            saveUserInfo(fullname, username, email, password, progressDialog)
                        }
                    }
            }
        }
    }

    private fun saveUserInfo(fullname: String, username: String, email: String, password: String, progressDialog : ProgressDialog) {
        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef : DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
        val userMap = HashMap<String, Any>()

//        set default
        userMap["uid"] = currentUserID
        userMap["fullname"] = fullname
        userMap["username"] = username
        userMap["email"] = email
        userMap["bio"] = "Kawai です"
        userMap["image"] = "https://firebasestorage.googleapis.com/v0/b/app-insragram.appspot.com/o/papanana.jpeg?alt=media&token=ae7035da-92e0-40f9-a227-ac9cc4187040"

//        terjadi di RegisterActivity  masukin dan save user yang sudah meregister dan lansung ke Main
        usersRef.child(currentUserID).setValue(userMap)
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    progressDialog.dismiss()
                    Toast.makeText(this, "account has been made",Toast.LENGTH_SHORT).show()

                    val goToMain = Intent(this, MainActivity::class.java)
                    goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(goToMain)
                    finish()
                }else{
                    val message = task.exception!!.toString()
                    Toast.makeText(this,"Error $message", Toast.LENGTH_SHORT)
                    FirebaseAuth.getInstance().signOut()
                    progressDialog.dismiss()
                }
            }
    }
}