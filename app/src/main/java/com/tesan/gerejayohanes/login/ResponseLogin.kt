package com.tesan.gerejayohanes.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
