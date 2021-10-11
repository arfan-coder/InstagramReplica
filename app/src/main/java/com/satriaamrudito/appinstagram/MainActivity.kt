package com.satriaamrudito.appinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.satriaamrudito.appinstagram.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bot_nav.setOnNavigationItemSelectedListener ( botNavListener )
    }
    private val botNavListener = BottomNavigationView.OnNavigationItemSelectedListener { a -> var bottomnav : Fragment = HomeFragment()
    when(a.itemId) {
        R.id.home ->{
            bottomnav = HomeFragment()
        }

        R.id.search ->{
            bottomnav = SearchFragment()
        }

        R.id.addimg ->{
            bottomnav = AddFragment()
        }

        R.id.notification ->{
            bottomnav = NotificationFragment()
        }

        R.id.account ->{
            bottomnav = ProfileFragment()
        }

    }
        var frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.frag_container,bottomnav)
        frag.commit()

        true
    }
}
