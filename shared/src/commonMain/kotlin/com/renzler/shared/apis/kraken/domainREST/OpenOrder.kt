package com.AKMM.shared.apis.kraken.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray


interface GetOrder{
        val openOrders: Map<String, Order>
        val openOrdersList: List<Order>
        fun getOpenOrderById(id : String): Order?
}


@Serializable
data class OpenOrder (
        @SerialName("error")
        val error: List<String>? = null,

        @SerialName("result")
        private val openOrder: Open

): GetOrder{
        override val openOrders: Map<String, Order>
                get()= openOrder.open

        override val openOrdersList: List<Order>
                get() = openOrder.open.values.toList();


        override fun getOpenOrderById(id: String): Order? {
                return openOrder.open.get(id)
        }

}



@Serializable
data class Open(
        @SerialName("open")
        val open: Map<String, Order>,

)

@Serializable
data class Order(

        @SerialName("refid")
        val refid: String?=null,
        @SerialName("userref")
        val userref: Int,
        @SerialName("status")
        val status: String,
        @SerialName("opentm")
        val opentm: String,
        @SerialName("starttm")
        val starttm: Int,
        @SerialName("expiretm")
        val expiretm: Int,
        @SerialName("descr")
        val descr: Descr,
        @SerialName("vol")
        val vol: String,
        @SerialName("vol_exec")
        val vol_exec: String,
        @SerialName("cost")
        val cost: String,
        @SerialName("fee")
        val fee: String,
        @SerialName("price")
        val price: String,
        @SerialName("stopprice")
        val stopprice: String,
        @SerialName("limitprice")
        val limitprice: String,
        @SerialName("misc")
        val misc: String,
        @SerialName("oflags")
        val oflags: String,
        @SerialName("trades")
        val trades: List<String>? = null

)

@Serializable
data class Descr(

        @SerialName("pair")
        val pair: String,
        @SerialName("type")
        val type: String,
        @SerialName("ordertype")
        val ordertype: String,
        @SerialName("price")
        val price: String,
        @SerialName("price2")
        val price2: String,
        @SerialName("leverage")
        val leverage: String,
        @SerialName("order")
        val order: String,
        @SerialName("close")
        val close: String


        )
