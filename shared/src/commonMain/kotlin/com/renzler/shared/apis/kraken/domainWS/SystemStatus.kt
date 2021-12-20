package com.renzler.shared.apis.kraken.domainWS


import kotlinx.serialization.Serializable






@Serializable
data class SystemStatus(

     val connectionID: String,

     val event: String,

     val status: String,

     val version: String,
)
