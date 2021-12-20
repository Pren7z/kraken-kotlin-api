package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface GetTrades{
        fun getTrades(): Map<String, Trade>
        fun getTradesAsList(): List<Trade>
        fun getTradeById(id : String): Trade?

}




@Serializable
data class Trades (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        private val trades: HistoricTrades

): GetTrades{
        override fun getTrades(): Map<String, Trade> {
                return trades.historicTrades
        }

        override fun getTradesAsList(): List<Trade> {
                return trades.historicTrades.values.toList()
        }

        override fun getTradeById(id: String): Trade? {
                return trades.historicTrades[id]
        }


}



@Serializable
data class HistoricTrades(

        @SerialName("trades")
        val historicTrades: Map<String, Trade>

)

@Serializable
data class Trade(

        @SerialName("ordertxid")
        val ordertxid: String,
        @SerialName("postxid")
        val postxid: String,
        @SerialName("pair")
        val pair: String,
        @SerialName("time")
        val time: String,
        @SerialName("type")
        val type: String,
        @SerialName("ordertype")
        val ordertype: String,
        @SerialName("price")
        val price: String,
        @SerialName("cost")
        val cost: String,
        @SerialName("fee")
        val fee: String,
        @SerialName("vol")
        val vol: String,
        @SerialName("margin")
        val margin: String,
        @SerialName("misc")
        val misc: String,


)


