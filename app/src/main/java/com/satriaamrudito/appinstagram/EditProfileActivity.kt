package com.satriaamrudito.appinstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_login.*

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        btn_logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val popo = Intent(this,LoginActivity::class.java)
            startActivity(popo)
        }
    }
}