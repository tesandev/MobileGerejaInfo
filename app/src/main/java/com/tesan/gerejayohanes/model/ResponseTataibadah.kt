package com.tesan.gerejayohanes.model

import com.google.gson.annotations.SerializedName

data class ResponseTataibadah(

	@field:SerializedName("namaibadah")
	val namaibadah: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("contentbody")
	val contentbody: String? = null
)
