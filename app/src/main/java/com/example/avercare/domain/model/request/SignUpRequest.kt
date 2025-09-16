package com.example.avercare.domain.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
	@field:SerializedName("email")
	var email: String? = null,
	@field:SerializedName("password")
	var password: String? = null,

)
