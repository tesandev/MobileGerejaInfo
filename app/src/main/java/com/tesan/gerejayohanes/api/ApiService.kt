package com.tesan.gerejayohanes.api

import com.tesan.gerejayohanes.login.ResponseLogin
import com.tesan.gerejayohanes.model.*
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

    @FormUrlEncoded
    @POST("usermobileUpdate")
    fun usermobileUpdate(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("passwordcurrent") passwordcurrent: String,
        @Field("passwordnew") passwordnew: String
    ): Call<ResponseRegister>

    @GET("usermobileDetail/{id}")
    fun usermobileDetail(
        @Path("id") id:String
    ):Call<ResponseDetailProfile>

    //pengumuman
    @GET("listpengumuman")
    fun listpengumuman(): Call<ArrayList<ResponsePengumuman>>

    //tataibadah
    @GET("listtataibadah")
    fun listtataibadah(): Call<ArrayList<ResponseTataibadah>>

    @GET("tataibadahdetail/{id}")
    fun tataibadahdetail(
        @Path("id") id:String
    ):Call<ResponseTataibadah>

    //jadwal kegiatan
    @GET("listjadwalkegiatan")
    fun listjadwalkegiatan(): Call<ArrayList<ResponseJadwalKegiatan>>

    //bacaan
    @GET("listbacaan")
    fun listbacaan(): Call<ArrayList<ResponseBacaan>>

    @GET("bacaandetail/{id}")
    fun bacaandetail(
        @Path("id") id:String
    ):Call<ResponseBacaan>

    //renungan
    @GET("listrenungan")
    fun listrenungan(): Call<ArrayList<ResponseRenungan>>

    @GET("renungandetail/{id}")
    fun renungandetail(
        @Path("id") id:String
    ):Call<ResponseRenungan>

    //petugas
    @GET("listpetugas")
    fun listpetugas(): Call<ArrayList<ResponsePetugas>>

    @GET("petugasdetail/{id}")
    fun petugasdetail(
        @Path("id") id:String
    ):Call<ResponsePetugas>
}