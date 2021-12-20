package com.renzler.shared.apis.kraken.domainWS

import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class SubscriptionStatus(

    @Required
    val event: String ,

    val pair: String,

    val channelName: String,

    val reqid :Int? =0,

    val status : String,

    val subscription: ChannelName

)


@Serializable
data class ChannelName(

    val name: String,

    val depth: Int? = null,

    val interval: Int? = null,



)