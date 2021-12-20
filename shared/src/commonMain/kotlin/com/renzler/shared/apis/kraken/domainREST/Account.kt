package com.renzler.shared.apis.kraken.domainREST


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account (

    @SerialName("error")
    val error: List<String>? = null,

    @SerialName("result")
    val result: Map<String, String>?=null,

)