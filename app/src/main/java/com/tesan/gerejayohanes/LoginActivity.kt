package com.tesan.gerejayohanes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.fragment.HomeActivity
import com.tesan.gerejayohanes.helper.Constant
import com.tesan.gerejayohanes.helper.SharedPref
import com.tesan.gerejayohanes.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var sharePref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharePref = SharedPref(this)

        gotoRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        var email = edtEmail.editText?.text.toString()
        var password = edtPassword.editText?.text.toString()
        if (edtEmail.editText?.text.toString().isEmpty()){
            edtEmail.error = "Kolom Email Tidak boleh Kosong"
            edtEmail.requestFocus()
            return
        }
        if (edtPassword.editText?.text.toString().isEmpty()){
            edtPassword.error = "Kolom Password Tidak boleh Kosong"
            edtPassword.requestFocus()
            return
        }

        val loading = ProgressDialog(this)
        loading.setMessage("Loading...")
        loading.setCancelable(false)
        loading.show()

        ApiConfig.instance.login(email,password).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val res = response.body()!!
                if (res.success == 1){
                    sharePref.putString(Constant.PREF_ID_USER,res.idUser.toString())
                    sharePref.putString(Constant.PREF_NAMA,res.name.toString())
                    sharePref.putString(Constant.PREF_EMAIL,res.email.toString())
                    Toast.makeText(applicationContext,res.message,Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                    finish()
                    Log.e("sp", sharePref.getString(Constant.PREF_ID_USER).toString()+"," +
                            sharePref.getString(Constant.PREF_NAMA).toString()+", "+
                            sharePref.getString(Constant.PREF_EMAIL).toString())
                    loading.hide()
                }else{
                    Toast.makeText(applicationContext,res.message,Toast.LENGTH_SHORT).show()
                    loading.hide()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                loading.hide()
                Toast.makeText(applicationContext,"Error Gagal Sistem",Toast.LENGTH_LONG).show()
                Log.e("ERROR", t.message.toString())
            }

        })
    }
}