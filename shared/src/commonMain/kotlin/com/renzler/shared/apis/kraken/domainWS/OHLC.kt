package com.renzler.shared.apis.kraken.domainWS

import kotlinx.serialization.Serializable

@Serializable
data class OHLC(

    var channelID : String,

    var channelName : String,

    var pair : String,

    var candles : Candles,

)

@Serializable
data class Candles(

    var time : String,

    var etime : String,

    var open : String,

    var high : String,

    var low : String,

    var close : String,

    /*
       Volume weighted average price within interval
    */
    var vwap : String,

    var volume : String,

    /*
    	Number of trades within interval
     */
    var count : Int,

)

