package com.renzler.shared.apis.kraken.domainWS


import kotlinx.serialization.Serializable



@Serializable
data class Heartbeat(

     val event: String,

)
