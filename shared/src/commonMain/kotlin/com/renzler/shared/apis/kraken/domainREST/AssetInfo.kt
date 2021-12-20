package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetInfo (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        val assets: Map<String, Asset>

)



@Serializable
data class Asset(
        @SerialName("altname")
        val altname: String,
        @SerialName("aclass")
        val aclass: String,
        @SerialName("decimals")
        val decimals: Int,
        @SerialName("display_decimals")
        val display_decimals: Int
)

