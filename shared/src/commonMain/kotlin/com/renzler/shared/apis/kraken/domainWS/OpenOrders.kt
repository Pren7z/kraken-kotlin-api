package com.renzler.shared.apis.kraken.domainWS


import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import kotlinx.serialization.Serializable






@Serializable
data class OpenOrders(

     val channelName: String,

     val sequence: Int,

     val openOrders: List<Map<String,Order>>,

)


@Serializable
data class Order(

    val avg_price: String,
    val cost: String,
    val descr: Description,
    val expiretm: String,
    val fee: String,
    val limitprice: String,
    val misc: String,
    val oflags: String,
    val opentm: String,
    val refid: String,
    val starttm: String,
    val status: String,
    val stopprice: String,
    val userref: String,
    val vol: String,
    val vol_exec: String,
)

@Serializable
data class Description(

    val close: String,
    val leverage: String,
    val order: String,
    val ordertype: String,
    val pair: String,
    val price: String,
    val price2: String,
    val type: String,
)
