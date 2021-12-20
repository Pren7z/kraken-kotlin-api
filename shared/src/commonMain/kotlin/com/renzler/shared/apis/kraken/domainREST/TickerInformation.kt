package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



interface GetTicker{
    fun getTicker() : Ticker
    fun getError() : List<String>?
}

@Serializable
data class TickerInformation (
    @SerialName("error")
    private val error: List<String>? = null,

    @SerialName("result")
    private val pair:Map<String,Ticker>

):GetTicker{
    override fun getTicker(): Ticker {
        return pair.entries.first().value
    }

    override fun getError(): List<String>? {
        return error
    }


}



@Serializable
data class Ticker(
    @SerialName("a")
    val a: List<String>,
    @SerialName("b")
    val b: List<String>,
    @SerialName("c")
    val c: List<String>,
    @SerialName("v")
    val v: List<String>,
    @SerialName("p")
    val p: List<String>,
    @SerialName("t")
    val t: List<Int>,
    @SerialName("l")
    val l: List<String>,
    @SerialName("h")
    val h: List<String>,
    @SerialName("o")
    val o: String

)