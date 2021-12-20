package com.renzler.shared.apis.kraken.domainREST


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface GetOrderBook{
    fun getOrderBook() : Book

}



@Serializable
data class OrderBook(

    @SerialName("error")
    private val error: List<String>? = null,

    @SerialName("result")
    private val book:Map<String, Book>


): GetOrderBook{
    override fun getOrderBook(): Book {
        return book.entries.first().value
    }

}


@Serializable
data class Book(
    @SerialName("asks")
    val asks: List<List<String>>,
    @SerialName("bids")
    val bids: List<List<String>>
)