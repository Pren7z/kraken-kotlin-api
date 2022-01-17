package com.renzler.shared.apis.kraken.domainREST



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*


interface GetCandleList{
    val getError: List<String>?
    val getCandleSticks :  List<Candlestick>
    val getLastCandleTimestamp : String

}

@Serializable
data class Candlesticks (

        @SerialName("error")
        private val error: List<String>? = null,

        @SerialName("result")
        private val candleSticks: JsonObject


    ):GetCandleList{
    
    override val getError: List<String>?
        get() = error

    override val getLastCandleTimestamp
        get() = candleSticks.values.last().toString()

    override val getCandleSticks: List<Candlestick>
        get() = getCandleList()


    fun getCandleList(): List<Candlestick> {

        val element: JsonArray = candleSticks.values.first().jsonArray

        return element.map {
            Candlestick(
                it.jsonArray[0].toString(),
                it.jsonArray[1].toString(),
                it.jsonArray[2].toString(),
                it.jsonArray[3].toString(),
                it.jsonArray[4].toString(),
                it.jsonArray[5].toString(),
                it.jsonArray[6].toString(),
                it.jsonArray[7].toString(),
            )
        }
    }


}

data class Candlestick (

    val timestamp : String,
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val vwap : String,
    val volume: String,
    val count: String,

)

















