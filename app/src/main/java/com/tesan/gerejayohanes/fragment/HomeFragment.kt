package com.tesan.gerejayohanes.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tesan.gerejayohanes.LoginActivity
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.databinding.FragmentHomeBinding
import com.tesan.gerejayohanes.helper.Constant
import com.tesan.gerejayohanes.helper.SharedPref
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment:Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var sharePref: SharedPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        sharePref = context?.let { SharedPref(it) }!!

        val formatter = SimpleDateFormat("dd MMMM yyyy hh:mm")
        val current = formatter.format(Date())

        binding.msgWelcome.text = "Selamat datang, "+sharePref.getString(Constant.PREF_NAMA)
        binding.datetoday.text = current



        binding.iconLogout.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
            activity?.finish()
        }

        binding.tataibadahCard.setOnClickListener {
            moveFragment(TataIbadahFragment())
        }

        binding.bacaanCard.setOnClickListener {
            moveFragment(BacaanFragment())
        }

        binding.jdkegiatanCard.setOnClickListener {
            moveFragment(JadwalKegiatanFragment())
        }

        binding.renunganCard.setOnClickListener {
            moveFragment(RenunganFragment())
        }

        binding.petugasCard.setOnClickListener {
            moveFragment(PetugasFragment())
        }

        return binding.root

    }

    private fun moveFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out,R.anim.fade_in,R.anim.slide_out)
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}