package com.tesan.gerejayohanes.model

import com.google.gson.annotations.SerializedName

data class ResponseBacaan(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("featured_image")
	val featuredImage: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
