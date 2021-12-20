
package com.renzler.shared.apis.kraken.impl


import com.renzler.shared.apis.kraken.KrakenApiWebSocketClient
import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import com.renzler.shared.apis.kraken.domainWS.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Transient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KrakenApiWebSocketClientImpl : KrakenApiWebSocketClient {

    private val json = Json { encodeDefaults = false; ignoreUnknownKeys = true; isLenient = true}

    val krakenApiWebSocketService = KrakenApiWebSocketService(json)
    val webSocketClient = krakenApiWebSocketService.webSocketClient




    @ExperimentalSerializationApi
    override suspend fun ticker(
        pair: List<String>,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<Ticker>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(pair = pair, subscription = SubscriptionData(name = KrakenApiConstants.TICKER)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_base_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getTicker)
    }


    @ExperimentalSerializationApi
    override suspend fun ohlc(
        pair: List<String>,
        interval: Int?,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<OHLC>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(pair = pair, subscription = SubscriptionData(name = KrakenApiConstants.OHLC, interval = interval)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_base_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getCandles)
    }

    @ExperimentalSerializationApi
    override suspend fun tradeFeed(
        pair: List<String>,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<TradeFeed>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(pair = pair, subscription = SubscriptionData(name = KrakenApiConstants.TRADE)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_base_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getTradeFeed)
    }

    @ExperimentalSerializationApi
    override suspend fun spreadFeed(
        pair: List<String>,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<SpreadFeed>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(pair = pair, subscription = SubscriptionData(name = KrakenApiConstants.SPREAD)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_base_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getSpreadFeed)
    }

    @ExperimentalSerializationApi
    override suspend fun orderBook(
        pair: List<String>,
        depth: Int?,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<OrderBook>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(pair = pair, subscription = SubscriptionData(name = KrakenApiConstants.BOOK, depth= depth)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_base_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getOrderBook)

    }


    @ExperimentalSerializationApi
    override suspend fun ownTrades (
        token: String,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<OwnTrades>) -> Unit
    ){
        val message =json.encodeToString(Subscription(subscription = SubscriptionData(name=KrakenApiConstants.OWNTRADES,token = token)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_auth_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getOwnTrades)
    }

    @ExperimentalSerializationApi
    override suspend fun openOrders(
        token: String,
        webSocketCallback: (result: KrakenAPIWebSocketResponse<OpenOrders>) -> Unit
    ) {
        val message =json.encodeToString(Subscription(subscription = SubscriptionData(name=KrakenApiConstants.OPENORDERS,token = token)))
        krakenApiWebSocketService.webSocketRequest(KrakenApiConstants.ws_auth_URL,message, webSocketCallback,KrakenApiWebSocketPayload(json)::getOpenOrders)
    }


    fun close(){
     webSocketClient.close()
    }





}


