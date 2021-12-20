package com.renzler.shared.apis.kraken.impl

import com.renzler.shared.apis.kraken.domainWS.Heartbeat

sealed class KrakenAPIWebSocketResponse<out T> {

    data class ChannelData<out T>(val data: T) : KrakenAPIWebSocketResponse<T>()

    data class Heartbeat(val data: com.renzler.shared.apis.kraken.domainWS.Heartbeat?) : KrakenAPIWebSocketResponse<Nothing>()

    data class SystemStatus(val data: com.renzler.shared.apis.kraken.domainWS.SystemStatus?) : KrakenAPIWebSocketResponse<Nothing>()

    data class Subscription(val data: com.renzler.shared.apis.kraken.domainWS.SubscriptionStatus?) : KrakenAPIWebSocketResponse<Nothing>()

    data class Failure(val throwable: Throwable?) : KrakenAPIWebSocketResponse<Nothing>()


}