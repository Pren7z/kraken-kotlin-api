package com.renzler.shared.apis.kraken.domainREST



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*


interface GetCandleList{
    fun getError(): List<String>?
    fun getCandleList() : JsonArray
    fun getLastCandleTimestamp() : String

}

@Serializable
data class Candlesticks (

        @SerialName("error")
        private val error: List<String>? = null,

        @SerialName("result")
        private val candleSticks: JsonObject


    ):GetCandleList{
    override fun getError(): List<String>?{
        return error
    }


    override fun getCandleList(): JsonArray {

        return candleSticks.values.first().jsonArray
    }

    override fun getLastCandleTimestamp(): String {

        return candleSticks.values.last().toString()
    }

}


















