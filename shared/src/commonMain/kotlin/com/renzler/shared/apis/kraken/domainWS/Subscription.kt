package com.renzler.shared.apis.kraken.domainWS

import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class Subscription(

    @Required
    val event: String = KrakenApiConstants.SUBSCRIBE,

    val pair: List<String>? = null,

    val subscription: SubscriptionData

)


@Serializable
data class SubscriptionData(

    val name: String,

    val depth: Int? = null,

    val interval: Int? = null,

    val token: String? = "",

)