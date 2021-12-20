package com.renzler.shared.apis.kraken.domainWS

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Ticker(

    var channelID : String,

    var channelName : String,

    var pair : String,

    var ticker : TickerData,


)
@Serializable
data class TickerData(

    @SerialName("a")
    val ask: Ask,

    @SerialName("b")
    val bid: Bid,

    @SerialName("c")
    val close: Close,

    @SerialName("h")
    val volume: Volume,

    @SerialName("l")
    val volumeWeightedAveragePrice: VolumeWeightedAveragePrice,

    @SerialName("o")
    val numberOfTrades: NumberOfTrades,

    @SerialName("p")
    val lowPrice: LowPrice,

    @SerialName("t")
    val highPrice: HighPrice,

    @SerialName("v")
    val openPrice: OpenPrice,
)

@Serializable
data class Ask(

    var price: String,

    var wholeLotVolume: Int,

    var lotVolume: String,
)

@Serializable
data class Bid(

    var price: String,

    var wholeLotVolume: Int,

    var lotVolume: String,
)

@Serializable
data class Close(

    var price: String,

    var lotVolume: String,
)

@Serializable
data class Volume(

    var today: String,

    var last24Hours: String,
)

@Serializable
data class VolumeWeightedAveragePrice(

    var today: String,

    var last24Hours: String,
)

@Serializable
data class NumberOfTrades(

    var today: String,

    var last24Hours: String,
)

@Serializable
data class LowPrice(

    var today: String,

    var last24Hours: String,
)

@Serializable
data class HighPrice(

    var today: String,

    var last24Hours: String,
)

@Serializable
data class OpenPrice(

    var today: String,

    var last24Hours: String,
)