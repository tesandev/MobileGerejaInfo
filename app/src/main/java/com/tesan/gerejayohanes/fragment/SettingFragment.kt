package com.tesan.gerejayohanes.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.tesan.gerejayohanes.R
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.databinding.FragmentSettingBinding
import com.tesan.gerejayohanes.helper.Constant
import com.tesan.gerejayohanes.helper.SharedPref
import com.tesan.gerejayohanes.model.ResponseDetailProfile
import com.tesan.gerejayohanes.register.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment:Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var sharePref: SharedPref

    private var nama:String? = null
    private var email:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        sharePref = context?.let { SharedPref(it) }!!

        val idUsr = sharePref.getString(Constant.PREF_ID_USER)

        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        ApiConfig.instance.usermobileDetail(idUsr.toString()).enqueue(object :Callback<ResponseDetailProfile>{
            override fun onResponse(
                call: Call<ResponseDetailProfile>,
                response: Response<ResponseDetailProfile>
            ) {
                val rs = response.body()!!
                binding.profilname.text = rs.name
                binding.profilemail.text = rs.email
                binding.edtNamaProfile.editText?.setText(rs.name)
                binding.edtEmailProfile.editText?.setText(rs.email)
                nama = rs.name
                email = rs.email
                loading.hide()
            }

            override fun onFailure(call: Call<ResponseDetailProfile>, t: Throwable) {
                loading.hide()
                Log.e("ERR",t.message.toString())
                Toast.makeText(context,t.message, Toast.LENGTH_SHORT).show()
            }

        })

        val passwordlama = binding.edtPasswordLama.editText?.text
        val passwordbaru = binding.edtPasswordNewProfile.editText?.text

        //Log.e("profile",idUsr+", "+nama+", "+email+", "+passwordlama+", "+passwordbaru)

        binding.btnUpdateProfile.setOnClickListener {
            updateProfile(idUsr.toString(),nama.toString(),email.toString(),passwordlama.toString(),passwordbaru.toString())
        }

        binding.profilMore.setOnClickListener {
            if(binding.editProfileDiv.isVisible == false){
                TransitionManager.beginDelayedTransition(binding.headSetting, AutoTransition())
                binding.editProfileDiv.setVisibility(View.VISIBLE)
                binding.profilMore.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }else{
                TransitionManager.beginDelayedTransition(binding.headSetting, AutoTransition())
                binding.editProfileDiv.setVisibility(View.GONE)
                binding.profilMore.setImageResource(R.drawable.ic_baseline_expand_more_24)
            }
        }

        return binding.root

    }

    private fun updateProfile(iduser:String,nama:String,email:String,passwordsekarang:String,passwordbaru:String) {
        if (binding.edtPasswordLama.editText?.text.toString().isEmpty()){
            binding.edtPasswordLama.error = "Password Sekarang Kosong"
            binding.edtPasswordLama.requestFocus()
            return
        }

        if (binding.edtPasswordNewProfile.editText?.text.toString().isEmpty()){
            binding.edtPasswordNewProfile.error = "Password Baru Kosong"
            binding.edtPasswordNewProfile.requestFocus()
            return
        }

        val loading = ProgressDialog(context)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        Log.e("profile",iduser+", "+nama+", "+email+", "+passwordsekarang+", "+passwordbaru)

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