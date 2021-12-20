package com.renzler.shared.apis.kraken.domainWS

import kotlinx.serialization.Serializable

@Serializable
data class SpreadFeed(

    var channelID : String,

    var channelName : String,

    var pair : String,

    var spread : Spread,

    )


@Serializable
data class Spread(

    var bid : String,

    var ask: String,

    var timestamp : String,

    var bidVolume : String,

    var askVolume : String,


)