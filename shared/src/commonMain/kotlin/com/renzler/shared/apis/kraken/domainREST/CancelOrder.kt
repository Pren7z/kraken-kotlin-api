package com.renzler.shared.apis.kraken.domainREST

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface GetCount{
    val getCount : Int?

}



@Serializable
data class CancelOrder (

    @SerialName("error")
    val error: List<String>? = null,

    @SerialName("result")
    val count: Map<String,Int>,

):GetCount {
    override val getCount: Int? = count["count"]

}


