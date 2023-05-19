package com.tesan.gerejayohanes.model

import com.google.gson.annotations.SerializedName

data class ResponseJadwalKegiatan(

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("tanggalkegiatan")
	val tanggalkegiatan: String? = null,

	@field:SerializedName("jamkegiatan")
	val jamkegiatan: String? = null,

	@field:SerializedName("namakegiatan")
	val namakegiatan: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
