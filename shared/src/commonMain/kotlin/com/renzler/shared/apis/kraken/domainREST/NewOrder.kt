package com.renzler.shared.apis.kraken.domainREST

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewOrder (

    @SerialName("error")
    val error: List<String>? = null,

    @SerialName("result")
    val order: Order

)

@Serializable
data class Order(
    @SerialName("descr")
    val orderDescription: OrderDescription,

    @SerialName("txid")
    val txid: String
)

@Serializable
data class OrderDescription(
    @SerialName("descr")
    val order: String,
    @SerialName("txid")
    val close: String
)