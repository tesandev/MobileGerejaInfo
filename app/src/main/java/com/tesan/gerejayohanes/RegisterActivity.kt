package com.tesan.gerejayohanes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tesan.gerejayohanes.api.ApiConfig
import com.tesan.gerejayohanes.register.ResponseRegister
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtEmail
import kotlinx.android.synthetic.main.activity_register.edtPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        gotoLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            finish()
            startActivity(intent)
        }

        btnDaftar.setOnClickListener {
            SubmitDaftar()
        }
    }

    private fun SubmitDaftar() {
        var name = edtNama.editText?.text.toString()
        var email = edtEmail.editText?.text.toString()
        var password = edtPassword.editText?.text.toString()
        //Log.e("daftar", name+", "+email+", "+password)

        if (edtNama.editText?.text.toString().isEmpty()){
            edtNama.error = "Kolom Email Tidak boleh Kosong"
            edtNama.requestFocus()
            return
        }

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

        ApiConfig.instance.register(name,email,password).enqueue(object : Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                val rs = response.body()!!
                if (rs.success == 1){
                    loading.hide()
                    val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(applicationContext,rs.message,Toast.LENGTH_SHORT).show()
                }else{
                    loading.hide()
                    Toast.makeText(applicationContext,rs.message,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                loading.hide()
                Toast.makeText(applicationContext,"Error Gagal Sistem",Toast.LENGTH_LONG).show()
                Log.e("ERROR", t.message.toString())
            }

        })
    }
}