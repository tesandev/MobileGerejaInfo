package com.tesan.gerejayohanes.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ReplaceFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itm_home -> ReplaceFragment(HomeFragment())
                R.id.itm_setting -> ReplaceFragment(SettingFragment())
                R.id.itm_notif -> ReplaceFragment(PengumumanFragment())
                R.id.itm_none -> this
            }
            true
        }

    }

    fun ReplaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}