package com.tesan.gerejayohanes.api

import com.tesan.gerejayohanes.login.ResponseLogin
import com.tesan.gerejayohanes.model.ResponseJadwalKegiatan
import com.tesan.gerejayohanes.model.ResponsePengumuman
import com.tesan.gerejayohanes.model.ResponseTataibadah
import com.tesan.gerejayohanes.register.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseRegister>

    @GET("listpengumuman")
    fun listpengumuman(): Call<ArrayList<ResponsePengumuman>>

    @GET("listtataibadah")
    fun listtataibadah(): Call<ArrayList<ResponseTataibadah>>

    @GET("listjadwalkegiatan")
    fun listjadwalkegiatan(): Call<ArrayList<ResponseJadwalKegiatan>>

    @GET("tataibadahdetail/{id}")
    fun tataibadahdetail(
        @Path("id") id:String
    ):Call<ResponseTataibadah>
}