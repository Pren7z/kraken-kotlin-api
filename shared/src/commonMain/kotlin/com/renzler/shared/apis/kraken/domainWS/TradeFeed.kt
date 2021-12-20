package com.renzler.shared.apis.kraken.domainWS

import kotlinx.serialization.Serializable

@Serializable
data class TradeFeed(

    var channelID : String,

    var channelName : String,

    var pair : String,

    var trades : Feed,

    )


@Serializable
data class Feed(

    var price : String,

    var volume: String,

    var time : String,

    var side : String,

    var orderType : String,

    var misc : String

)