package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentSettingBinding
import com.tesan.gerejayohanes.helper.Constant
import com.tesan.gerejayohanes.helper.SharedPref
import com.tesan.gerejayohanes.register.ResponseRegister
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment:Fragment() {
    private lateinit var binding: FragmentSettingBinding
    lateinit var sharePref: SharedPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        val idUsr = sharePref.getString(Constant.PREF_ID_USER)
        val nama = sharePref.getString(Constant.PREF_NAMA)
        val email = sharePref.getString(Constant.PREF_EMAIL)
        val passwordsekarang = binding.edtPasswordCurrentProfile.editText?.text
        val passwordbaru = binding.edtPasswordNewProfile.editText?.text

        binding.profilname.text = nama
        binding.profilemail.text = email

        binding.edtNamaProfile.editText?.setText(nama)
        binding.edtEmailProfile.editText?.setText(email)

        binding.btnUpdateProfile.setOnClickListener {
            updateProfile(idUsr.toString(),nama.toString(),email.toString(),passwordsekarang.toString(),passwordbaru.toString())
        }
        return binding.root

    }

    private fun updateProfile(iduser:String,nama:String,email:String,passwordsekarang:String,passwordbaru:String) {
        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        ApiConfig.instance.usermobileUpdate(iduser,nama,email,passwordsekarang,passwordbaru).enqueue(object : Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                val rs = response.body()!!
                if(rs.success == 1){
                    loading.hide()
                    Toast.makeText(context,rs.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context,HomeActivity::class.java))
                }else{
                    loading.hide()
                    Toast.makeText(context,rs.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}