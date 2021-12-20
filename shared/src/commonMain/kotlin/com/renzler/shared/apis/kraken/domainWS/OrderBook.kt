package com.renzler.shared.apis.kraken.domainWS

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderBook(

    var channelID : String,

    var channelName : String,

    var pair : String,

    var orderBookUpdate: OrderBookUpdate? = null,

    var orderBookSnapshot: OrderBookSnapshot? = null


    )

@Serializable
data class OrderBookSnapshot(
    @SerialName("as")
    var asks : List<List<String>>,

    @SerialName("bs")
    var bids: List<List<String>>,

)



@Serializable
data class OrderBookUpdate(
    @SerialName("a")
    var asks : List<List<String>>? =null,

    @SerialName("b")
    var bids: List<List<String>>? = null,

    @SerialName("c")
    var checksum: String? = null,

)

