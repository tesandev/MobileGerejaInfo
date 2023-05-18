package com.tesan.gerejayohanes.register

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
