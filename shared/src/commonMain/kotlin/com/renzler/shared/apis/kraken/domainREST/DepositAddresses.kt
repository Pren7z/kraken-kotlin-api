package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray




@Serializable
data class DepositAddresses (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        val addresses: List<Addresses>

)

@Serializable
data class Addresses (

        @SerialName("address")
        val address: String,

        @SerialName("expiretm")
        val expiretm: String,

        @SerialName("new")
        val new: Boolean? = false,

)




