package com.tesan.gerejayohanes.model

import com.google.gson.annotations.SerializedName

data class ResponsePetugas(

	@field:SerializedName("jadwal_id")
	val jadwalId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("tanggalkegiatan")
	val tanggalkegiatan: String? = null,

	@field:SerializedName("jamkegiatan")
	val jamkegiatan: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null
)
