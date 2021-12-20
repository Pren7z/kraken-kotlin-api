package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetPairs (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        val assets: Map<String, AssetPair>



)



@Serializable
data class AssetPair(
        @SerialName("altname")
        val altname: String,
        @SerialName("wsname")
        val wsname: String,
        @SerialName("aclass_base")
        val aclass_base: String,
        @SerialName("base")
        val base: String,
        @SerialName("aclass_quote")
        val aclass_quote: String,
        @SerialName("quote")
        val quote: String,
        @SerialName("lot")
        val lot: String,
        @SerialName("pair_decimals")
        val pair_decimals: Int,
        @SerialName("lot_decimals")
        val lot_decimals: Int,
        @SerialName("lot_multiplier")
        val lot_multiplier: Int,
        @SerialName("leverage_buy")
        val leverage_buy: List<Int>,
        @SerialName("leverage_sell")
        val leverage_sell: List<Int>,
        @SerialName("fees")
        val fees: List<List<Int>>,
        @SerialName("fees_maker")
        val fees_maker: List<List<Int>>,
        @SerialName("fee_volume_currency")
        val fee_volume_currency: String,
        @SerialName("margin_call")
        val margin_call: Int,
        @SerialName("margin_stop")
        val margin_stop: Int,
        @SerialName("ordermin")
        val ordermin: String,
)

