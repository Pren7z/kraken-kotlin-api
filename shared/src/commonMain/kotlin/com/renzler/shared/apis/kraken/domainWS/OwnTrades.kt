package com.renzler.shared.apis.kraken.domainWS


import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import kotlinx.serialization.Serializable






@Serializable
data class OwnTrades(

     val channelName: String,

     val sequence: Int,

     val trades: Map<String,Trades>,

)


@Serializable
data class Trades(

    val cost: String,
    val fee: String,
    val margin: String,
    val ordertxid: String,
    val ordertype: String,
    val pair: String,
    val postxid: String,
    val price: String,
    val time: String,
    val type: String,
    val vol: String,
)