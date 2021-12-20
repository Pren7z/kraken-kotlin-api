package com.renzler.shared.apis.kraken.constant

import com.renzler.shared.apis.kraken.impl.KrakenAPIWebSocketResponse

class KrakenApiConstants {

    companion object {
        /*
        REST API constants
         */
        const val base_URL = "https://api.kraken.com"

        /*
        WebSocket API constants
         */
        const val ws_base_URL = "ws.kraken.com"
        const val ws_auth_URL = "ws-auth.kraken.com"

        const val SUBSCRIBE = "subscribe"
        const val UNSUBSCRIBE = "unsubscribe"
        const val OWNTRADES = "ownTrades"
        const val OPENORDERS = "openOrders"
        const val TICKER = "ticker"
        const val EVENT = "event"
        const val NAME = "name"
        const val OHLC = "ohlc"
        const val TRADE = "trade"
        const val SPREAD = "spread"
        const val BOOK = "book"
        const val SYSTEMSTATUS = "systemStatus"
        const val SUBSCRIPTIONSTATUS = "subscriptionStatus"
        const val HEARTBEAT = "heartbeat"

    }


}