package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray


interface GetToken{
        fun getToken(): String

}


@Serializable
data class WebSocketToken (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        val token: Token

):GetToken{
        override fun getToken(): String {
                return token.token
        }

}

@Serializable
data class Token(

        @SerialName("token")
        val token: String,

        @SerialName("expires")
        val expires: Int,

        )



